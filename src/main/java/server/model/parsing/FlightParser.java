package server.model.parsing;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import server.model.flights.Flight;
import server.model.flights.Location;
import server.model.networking.HTTP_GetRequest;

//TODO DO NOT EXECUTE MAIN METHOD, WE GOTTA SAFE THEM API REQUESTS
public class FlightParser {

    public static List<Flight> parseFlightJson(String jsonText) {
        try {
            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray dataArray = jsonObject.getJSONArray("data");
            List<Flight> flights = new ArrayList<>();
            for (int i = 0; i < dataArray.length(); i++) {

                String number = dataArray.getJSONObject(i).getJSONObject("flight").getString("iata");
                String startDate = dataArray.getJSONObject(i).getString("flight_date");
                int startYear = Integer.parseInt(startDate.substring(0,4));
                int startMonth = Integer.parseInt(startDate.substring(5,7));
                int startDay = Integer.parseInt(startDate.substring(8));

                int startHourTIndex = dataArray.getJSONObject(i).getJSONObject("departure").getString("scheduled").indexOf("T");
                int startHour = Integer.parseInt(dataArray.getJSONObject(i).getJSONObject("departure").getString("scheduled").substring(startHourTIndex + 1, startHourTIndex + 3));
                int startMinute = Integer.parseInt(dataArray.getJSONObject(i).getJSONObject("departure").getString("scheduled").substring(startHourTIndex + 4, startHourTIndex + 6));

                Month startM = parseToMonth(startMonth);
                LocalDateTime startTime = LocalDateTime.of(startYear, startM, startDay, startHour, startMinute);

                String endDate = dataArray.getJSONObject(i).getJSONObject("arrival").getString("scheduled");

                int endYear = Integer.parseInt(endDate.substring(0,4));
                int endMonth = Integer.parseInt(endDate.substring(5,7));
                int endDay = Integer.parseInt(endDate.substring(8,10));

                int endHour = Integer.parseInt(dataArray.getJSONObject(i).getJSONObject("arrival").getString("scheduled").substring(startHourTIndex + 1, startHourTIndex + 3));
                int endMinute = Integer.parseInt(dataArray.getJSONObject(i).getJSONObject("arrival").getString("scheduled").substring(startHourTIndex + 4, startHourTIndex + 6));

                Month endM = parseToMonth(endMonth);
                LocalDateTime endTime = LocalDateTime.of(endYear, endM, endDay, endHour, endMinute);

                String gate = dataArray.getJSONObject(i).getJSONObject("departure").getString("gate");
                String terminal = dataArray.getJSONObject(i).getJSONObject("departure").getString("terminal");
                int seat = ThreadLocalRandom.current().nextInt(1, 288);
                String airline = dataArray.getJSONObject(i).getJSONObject("airline").getString("name");
                boolean cancelled = true;
                boolean delayed = false;
                if (dataArray.getJSONObject(i).getString("flight_status").equals("scheduled")) {
                    cancelled = false;
                }
                if (!dataArray.getJSONObject(i).getJSONObject("departure").getString("scheduled").equals(dataArray.getJSONObject(i).getJSONObject("departure").getString("estimated"))) {
                    delayed = true;
                }
                //todo delayTime
                int delayTime = 0;

                //todo weather, poilist, latitude, longitude: done (check if working)
                //todo add airports
                //todo fix when coords arent available for location

                String startName = dataArray.getJSONObject(i).getJSONObject("departure").getString("timezone").substring(dataArray.getJSONObject(i).getJSONObject("departure").getString("timezone").indexOf("/") + 1);
                String endName = dataArray.getJSONObject(i).getJSONObject("arrival").getString("timezone").substring(dataArray.getJSONObject(i).getJSONObject("arrival").getString("timezone").indexOf("/") + 1);

                double[] startCoords = fetchCoordsForGivenAddress(startName);
                double[] endCoords = fetchCoordsForGivenAddress(endName);
                System.out.println(Arrays.toString(startCoords));
                System.out.println(Arrays.toString(endCoords));

                Location startLocation = new Location(startName, startCoords[1], startCoords[0]);
                Location endLocation = new Location(endName, endCoords[1], endCoords[0]);

                Flight current = new Flight(number, startTime, endTime, gate, terminal, seat, airline, startLocation, endLocation);
                current.setDelayed(delayed);
                current.setCancelled(cancelled);
                flights.add(current);
            }
            return flights;
        } catch (JSONException exception) {
            System.out.println("Couldn't parse flights");
        }
        return null;
    }

    public static double[] fetchCoordsForGivenAddress(String address) {
        return GeocodingParser.parseGeocodingJson(HTTP_GetRequest.httpRequest("https://maps.googleapis.com/maps/api/geocode/json", new String[]{"?address=" + address, "&key=AIzaSyBKiScI4WumTVipTbFuC6KPHic3dC66tvM"}));
    }

    public String toString(List<Flight> flights) {
        StringBuilder builder = new StringBuilder();
        for (Flight flight : flights) {
            builder.append(flight.toString());
            builder.append("\n\n");
        }
        return builder.toString();
    }

    private static Month parseToMonth(int month) throws NoSuchElementException {
        switch (month) {
            case 1 -> {return Month.JANUARY;}
            case 2 -> {return Month.FEBRUARY;}
            case 3 -> {return Month.MARCH;}
            case 4 -> {return Month.APRIL;}
            case 5 -> {return Month.MAY;}
            case 6 -> {return Month.JUNE;}
            case 7 -> {return Month.JULY;}
            case 8 -> {return Month.AUGUST;}
            case 9 -> {return Month.SEPTEMBER;}
            case 10 -> {return Month.OCTOBER;}
            case 11 -> {return Month.NOVEMBER;}
            case 12 -> {return Month.DECEMBER;}
            default -> throw new NoSuchElementException("No such month");
        }
    }

    public static void main(String[] args) throws JSONException {
        System.out.println("test");
        List<Flight> flightList = parseFlightJson("{\n" +
                "   \"pagination\":{\n" +
                "      \"limit\":10,\n" +
                "      \"offset\":0,\n" +
                "      \"count\":10,\n" +
                "      \"total\":435398\n" +
                "   },\n" +
                "   \"data\":[\n" +
                "      {\n" +
                "         \"flight_date\":\"2022-06-25\",\n" +
                "         \"flight_status\":\"cancelled\",\n" +
                "         \"departure\":{\n" +
                "            \"airport\":\"Dalian\",\n" +
                "            \"timezone\":\"Asia\\/Shanghai\",\n" +
                "            \"iata\":\"DLC\",\n" +
                "            \"icao\":\"ZYTL\",\n" +
                "            \"terminal\":null,\n" +
                "            \"gate\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T08:55:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T08:55:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"arrival\":{\n" +
                "            \"airport\":\"Chongqing Jiangbei International\",\n" +
                "            \"timezone\":\"Asia\\/Shanghai\",\n" +
                "            \"iata\":\"CKG\",\n" +
                "            \"icao\":\"ZUCK\",\n" +
                "            \"terminal\":\"T3\",\n" +
                "            \"gate\":null,\n" +
                "            \"baggage\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T12:15:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T12:15:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"airline\":{\n" +
                "            \"name\":\"China Southern Airlines\",\n" +
                "            \"iata\":\"CZ\",\n" +
                "            \"icao\":\"CSN\"\n" +
                "         },\n" +
                "         \"flight\":{\n" +
                "            \"number\":\"6579\",\n" +
                "            \"iata\":\"CZ6579\",\n" +
                "            \"icao\":\"CSN6579\",\n" +
                "            \"codeshared\":null\n" +
                "         },\n" +
                "         \"aircraft\":null,\n" +
                "         \"live\":null\n" +
                "      },\n" +
                "      {\n" +
                "         \"flight_date\":\"2022-06-25\",\n" +
                "         \"flight_status\":\"scheduled\",\n" +
                "         \"departure\":{\n" +
                "            \"airport\":\"Ngurah Rai International\",\n" +
                "            \"timezone\":\"Asia\\/Makassar\",\n" +
                "            \"iata\":\"DPS\",\n" +
                "            \"icao\":\"WADD\",\n" +
                "            \"terminal\":\"D\",\n" +
                "            \"gate\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T07:00:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T07:00:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"arrival\":{\n" +
                "            \"airport\":\"Soekarno-Hatta International\",\n" +
                "            \"timezone\":\"Asia\\/Jakarta\",\n" +
                "            \"iata\":\"CGK\",\n" +
                "            \"icao\":\"WIII\",\n" +
                "            \"terminal\":\"2\",\n" +
                "            \"gate\":null,\n" +
                "            \"baggage\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T08:00:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T08:00:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"airline\":{\n" +
                "            \"name\":\"Citilink\",\n" +
                "            \"iata\":\"QG\",\n" +
                "            \"icao\":\"CTV\"\n" +
                "         },\n" +
                "         \"flight\":{\n" +
                "            \"number\":\"663\",\n" +
                "            \"iata\":\"QG663\",\n" +
                "            \"icao\":\"CTV663\",\n" +
                "            \"codeshared\":null\n" +
                "         },\n" +
                "         \"aircraft\":null,\n" +
                "         \"live\":null\n" +
                "      },\n" +
                "      {\n" +
                "         \"flight_date\":\"2022-06-25\",\n" +
                "         \"flight_status\":\"scheduled\",\n" +
                "         \"departure\":{\n" +
                "            \"airport\":\"Jomo Kenyatta International\",\n" +
                "            \"timezone\":\"Africa\\/Nairobi\",\n" +
                "            \"iata\":\"NBO\",\n" +
                "            \"icao\":\"HKJK\",\n" +
                "            \"terminal\":\"2\",\n" +
                "            \"gate\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T01:25:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T01:25:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"arrival\":{\n" +
                "            \"airport\":\"Doha International\",\n" +
                "            \"timezone\":\"Asia\\/Qatar\",\n" +
                "            \"iata\":\"DOH\",\n" +
                "            \"icao\":\"OTHH\",\n" +
                "            \"terminal\":null,\n" +
                "            \"gate\":null,\n" +
                "            \"baggage\":\"6\",\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T06:40:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T06:40:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"airline\":{\n" +
                "            \"name\":\"Royal Jordanian\",\n" +
                "            \"iata\":\"RJ\",\n" +
                "            \"icao\":\"RJA\"\n" +
                "         },\n" +
                "         \"flight\":{\n" +
                "            \"number\":\"3825\",\n" +
                "            \"iata\":\"RJ3825\",\n" +
                "            \"icao\":\"RJA3825\",\n" +
                "            \"codeshared\":{\n" +
                "               \"airline_name\":\"qatar airways\",\n" +
                "               \"airline_iata\":\"qr\",\n" +
                "               \"airline_icao\":\"qtr\",\n" +
                "               \"flight_number\":\"1342\",\n" +
                "               \"flight_iata\":\"qr1342\",\n" +
                "               \"flight_icao\":\"qtr1342\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"aircraft\":null,\n" +
                "         \"live\":null\n" +
                "      },\n" +
                "      {\n" +
                "         \"flight_date\":\"2022-06-25\",\n" +
                "         \"flight_status\":\"scheduled\",\n" +
                "         \"departure\":{\n" +
                "            \"airport\":\"Urumqi\",\n" +
                "            \"timezone\":\"Asia\\/Shanghai\",\n" +
                "            \"iata\":\"URC\",\n" +
                "            \"icao\":\"ZWWW\",\n" +
                "            \"terminal\":\"3\",\n" +
                "            \"gate\":\"32\",\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T09:00:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T09:00:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"arrival\":{\n" +
                "            \"airport\":\"Shuangliu\",\n" +
                "            \"timezone\":\"Asia\\/Shanghai\",\n" +
                "            \"iata\":\"CTU\",\n" +
                "            \"icao\":\"ZUUU\",\n" +
                "            \"terminal\":\"2\",\n" +
                "            \"gate\":null,\n" +
                "            \"baggage\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T12:35:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T12:35:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"airline\":{\n" +
                "            \"name\":\"China Southern Airlines\",\n" +
                "            \"iata\":\"CZ\",\n" +
                "            \"icao\":\"CSN\"\n" +
                "         },\n" +
                "         \"flight\":{\n" +
                "            \"number\":\"6941\",\n" +
                "            \"iata\":\"CZ6941\",\n" +
                "            \"icao\":\"CSN6941\",\n" +
                "            \"codeshared\":null\n" +
                "         },\n" +
                "         \"aircraft\":null,\n" +
                "         \"live\":null\n" +
                "      },\n" +
                "      {\n" +
                "         \"flight_date\":\"2022-06-25\",\n" +
                "         \"flight_status\":\"scheduled\",\n" +
                "         \"departure\":{\n" +
                "            \"airport\":\"Da Nang\",\n" +
                "            \"timezone\":\"Asia\\/Ho_Chi_Minh\",\n" +
                "            \"iata\":\"DAD\",\n" +
                "            \"icao\":\"VVDN\",\n" +
                "            \"terminal\":\"1\",\n" +
                "            \"gate\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T07:40:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T07:40:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"arrival\":{\n" +
                "            \"airport\":\"Noibai International\",\n" +
                "            \"timezone\":\"Asia\\/Ho_Chi_Minh\",\n" +
                "            \"iata\":\"HAN\",\n" +
                "            \"icao\":\"VVNB\",\n" +
                "            \"terminal\":\"1\",\n" +
                "            \"gate\":null,\n" +
                "            \"baggage\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T09:05:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T09:05:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"airline\":{\n" +
                "            \"name\":null,\n" +
                "            \"iata\":\"A1\",\n" +
                "            \"icao\":\"A1\"\n" +
                "         },\n" +
                "         \"flight\":{\n" +
                "            \"number\":\"2504\",\n" +
                "            \"iata\":\"A12504\",\n" +
                "            \"icao\":\"A12504\",\n" +
                "            \"codeshared\":{\n" +
                "               \"airline_name\":\"vietjet air\",\n" +
                "               \"airline_iata\":\"vj\",\n" +
                "               \"airline_icao\":\"vjc\",\n" +
                "               \"flight_number\":\"504\",\n" +
                "               \"flight_iata\":\"vj504\",\n" +
                "               \"flight_icao\":\"vjc504\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"aircraft\":null,\n" +
                "         \"live\":null\n" +
                "      },\n" +
                "      {\n" +
                "         \"flight_date\":\"2022-06-25\",\n" +
                "         \"flight_status\":\"scheduled\",\n" +
                "         \"departure\":{\n" +
                "            \"airport\":\"Beijing Capital International\",\n" +
                "            \"timezone\":\"Asia\\/Shanghai\",\n" +
                "            \"iata\":\"PEK\",\n" +
                "            \"icao\":\"ZBAA\",\n" +
                "            \"terminal\":\"2\",\n" +
                "            \"gate\":\"H01\",\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T07:30:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T07:30:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"arrival\":{\n" +
                "            \"airport\":\"Gonggar\\/Lhasa\",\n" +
                "            \"timezone\":\"Asia\\/Shanghai\",\n" +
                "            \"iata\":\"LXA\",\n" +
                "            \"icao\":\"ZULS\",\n" +
                "            \"terminal\":\"T3\",\n" +
                "            \"gate\":null,\n" +
                "            \"baggage\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T12:00:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T12:00:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"airline\":{\n" +
                "            \"name\":\"Shenzhen Airlines\",\n" +
                "            \"iata\":\"ZH\",\n" +
                "            \"icao\":\"CSZ\"\n" +
                "         },\n" +
                "         \"flight\":{\n" +
                "            \"number\":\"4123\",\n" +
                "            \"iata\":\"ZH4123\",\n" +
                "            \"icao\":\"CSZ4123\",\n" +
                "            \"codeshared\":{\n" +
                "               \"airline_name\":\"air china ltd\",\n" +
                "               \"airline_iata\":\"ca\",\n" +
                "               \"airline_icao\":\"cca\",\n" +
                "               \"flight_number\":\"4123\",\n" +
                "               \"flight_iata\":\"ca4123\",\n" +
                "               \"flight_icao\":\"cca4123\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"aircraft\":null,\n" +
                "         \"live\":null\n" +
                "      },\n" +
                "      {\n" +
                "         \"flight_date\":\"2022-06-25\",\n" +
                "         \"flight_status\":\"scheduled\",\n" +
                "         \"departure\":{\n" +
                "            \"airport\":\"Thiruvananthapuram International\",\n" +
                "            \"timezone\":\"Asia\\/Kolkata\",\n" +
                "            \"iata\":\"TRV\",\n" +
                "            \"icao\":\"VOTV\",\n" +
                "            \"terminal\":\"2\",\n" +
                "            \"gate\":null,\n" +
                "            \"delay\":7,\n" +
                "            \"scheduled\":\"2022-06-25T01:20:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T01:20:00+00:00\",\n" +
                "            \"actual\":\"2022-06-25T01:26:00+00:00\",\n" +
                "            \"estimated_runway\":\"2022-06-25T01:26:00+00:00\",\n" +
                "            \"actual_runway\":\"2022-06-25T01:26:00+00:00\"\n" +
                "         },\n" +
                "         \"arrival\":{\n" +
                "            \"airport\":\"Sharjah\",\n" +
                "            \"timezone\":\"Asia\\/Dubai\",\n" +
                "            \"iata\":\"SHJ\",\n" +
                "            \"icao\":\"OMSJ\",\n" +
                "            \"terminal\":\"M\",\n" +
                "            \"gate\":null,\n" +
                "            \"baggage\":\"3\",\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T04:01:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T04:01:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"airline\":{\n" +
                "            \"name\":\"IndiGo\",\n" +
                "            \"iata\":\"6E\",\n" +
                "            \"icao\":\"IGO\"\n" +
                "         },\n" +
                "         \"flight\":{\n" +
                "            \"number\":\"1401\",\n" +
                "            \"iata\":\"6E1401\",\n" +
                "            \"icao\":\"IGO1401\",\n" +
                "            \"codeshared\":null\n" +
                "         },\n" +
                "         \"aircraft\":null,\n" +
                "         \"live\":null\n" +
                "      },\n" +
                "      {\n" +
                "         \"flight_date\":\"2022-06-25\",\n" +
                "         \"flight_status\":\"scheduled\",\n" +
                "         \"departure\":{\n" +
                "            \"airport\":\"Yantai\",\n" +
                "            \"timezone\":\"Asia\\/Shanghai\",\n" +
                "            \"iata\":\"YNT\",\n" +
                "            \"icao\":\"ZSYT\",\n" +
                "            \"terminal\":\"T1\",\n" +
                "            \"gate\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T07:15:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T07:15:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"arrival\":{\n" +
                "            \"airport\":\"Nanjing Lukou International Airport\",\n" +
                "            \"timezone\":\"Asia\\/Shanghai\",\n" +
                "            \"iata\":\"NKG\",\n" +
                "            \"icao\":\"ZSNJ\",\n" +
                "            \"terminal\":\"1\",\n" +
                "            \"gate\":null,\n" +
                "            \"baggage\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T08:50:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T08:50:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"airline\":{\n" +
                "            \"name\":\"Shenzhen Airlines\",\n" +
                "            \"iata\":\"ZH\",\n" +
                "            \"icao\":\"CSZ\"\n" +
                "         },\n" +
                "         \"flight\":{\n" +
                "            \"number\":\"2640\",\n" +
                "            \"iata\":\"ZH2640\",\n" +
                "            \"icao\":\"CSZ2640\",\n" +
                "            \"codeshared\":{\n" +
                "               \"airline_name\":\"shandong airlines\",\n" +
                "               \"airline_iata\":\"sc\",\n" +
                "               \"airline_icao\":\"cdg\",\n" +
                "               \"flight_number\":\"4727\",\n" +
                "               \"flight_iata\":\"sc4727\",\n" +
                "               \"flight_icao\":\"cdg4727\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"aircraft\":null,\n" +
                "         \"live\":null\n" +
                "      },\n" +
                "      {\n" +
                "         \"flight_date\":\"2022-06-25\",\n" +
                "         \"flight_status\":\"scheduled\",\n" +
                "         \"departure\":{\n" +
                "            \"airport\":\"Quaid-e-azam International\",\n" +
                "            \"timezone\":\"Asia\\/Karachi\",\n" +
                "            \"iata\":\"KHI\",\n" +
                "            \"icao\":\"OPKC\",\n" +
                "            \"terminal\":\"M\",\n" +
                "            \"gate\":null,\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T04:10:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T04:10:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"arrival\":{\n" +
                "            \"airport\":\"Doha International\",\n" +
                "            \"timezone\":\"Asia\\/Qatar\",\n" +
                "            \"iata\":\"DOH\",\n" +
                "            \"icao\":\"OTHH\",\n" +
                "            \"terminal\":null,\n" +
                "            \"gate\":null,\n" +
                "            \"baggage\":\"1\",\n" +
                "            \"delay\":null,\n" +
                "            \"scheduled\":\"2022-06-25T05:00:00+00:00\",\n" +
                "            \"estimated\":\"2022-06-25T05:00:00+00:00\",\n" +
                "            \"actual\":null,\n" +
                "            \"estimated_runway\":null,\n" +
                "            \"actual_runway\":null\n" +
                "         },\n" +
                "         \"airline\":{\n" +
                "            \"name\":\"British Airways\",\n" +
                "            \"iata\":\"BA\",\n" +
                "            \"icao\":\"BAW\"\n" +
                "         },\n" +
                "         \"flight\":{\n" +
                "            \"number\":\"6175\",\n" +
                "            \"iata\":\"BA6175\",\n" +
                "            \"icao\":\"BAW6175\",\n" +
                "            \"codeshared\":{\n" +
                "               \"airline_name\":\"qatar airways\",\n" +
                "               \"airline_iata\":\"qr\",\n" +
                "               \"airline_icao\":\"qtr\",\n" +
                "               \"flight_number\":\"605\",\n" +
                "               \"flight_iata\":\"qr605\",\n" +
                "               \"flight_icao\":\"qtr605\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"aircraft\":null,\n" +
                "         \"live\":null\n" +
                "      }\n" +
                "   ]\n" +
                "}");
        assert flightList != null;
        System.out.println(flightList.toString());
       /* try {
            //System.out.println(HTTP_GetRequest.httpRequest("http://api.aviationstack.com/v1/flights", new String[]{"?access_key=8df0ff3c6cd266e3219eed88b44cc2ee", "?limit=10"}));
            System.out.println(FlightParser.parseFlightJson(HTTP_GetRequest.httpRequest("http://api.aviationstack.com/v1/flights", new String[]{"?access_key=8df0ff3c6cd266e3219eed88b44cc2ee", "&limit=10"})));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
