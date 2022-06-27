package server.model.parsing;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
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

    //todo manage edge cases

    public static List<Flight> parseFlightJson(String jsonText, String fromName, String toName) {
        try {
            JSONObject jsonObject = new JSONObject(jsonText);
            List<Flight> flights = new ArrayList<>();
            int counter = 0;
            while (jsonObject.has(String.valueOf(counter))) {

                JSONObject current = jsonObject.getJSONObject(String.valueOf(counter));

                String number = current.getJSONObject("flight").getString("iata");
                String startDate = current.getString("flight_date");
                int startYear = Integer.parseInt(startDate.substring(0,4));
                int startMonth = Integer.parseInt(startDate.substring(5,7));
                int startDay = Integer.parseInt(startDate.substring(8));

                int startHourTIndex = current.getJSONObject("departure").getString("scheduled").indexOf("T");
                int startHour = Integer.parseInt(current.getJSONObject("departure").getString("scheduled").substring(startHourTIndex + 1, startHourTIndex + 3));
                //System.out.println(current.getJSONObject("departure").getString("scheduled"));
                int startMinute = Integer.parseInt(current.getJSONObject("departure").getString("scheduled").substring(startHourTIndex + 4, startHourTIndex + 6));

                Month startM = parseToMonth(startMonth);
                LocalDateTime startTime = LocalDateTime.of(startYear, startM, startDay, startHour, startMinute);

                String endDate = current.getJSONObject("arrival").getString("scheduled");

                int endYear = Integer.parseInt(endDate.substring(0,4));
                int endMonth = Integer.parseInt(endDate.substring(5,7));
                int endDay = Integer.parseInt(endDate.substring(8,10));

                int endHour = Integer.parseInt(endDate.substring(startHourTIndex + 1, startHourTIndex + 3));
                int endMinute = Integer.parseInt(endDate.substring(startHourTIndex + 4, startHourTIndex + 6));

                Month endM = parseToMonth(endMonth);
                LocalDateTime endTime = LocalDateTime.of(endYear, endM, endDay, endHour, endMinute);

                String gate = current.getJSONObject("departure").getString("gate");
                if (gate.equals("null")) {
                    //filler
                    gate = "32";
                }
                String terminal = current.getJSONObject("departure").getString("terminal");
                if (terminal.equals("null")) {
                    //filler
                    terminal = "2";
                }
                int seat = ThreadLocalRandom.current().nextInt(1, 288);
                String airline = current.getJSONObject("airline").getString("name");
                if (airline.equals("empty")) {
                    //filler
                    airline = "Qatar Airways";
                }

                boolean cancelled = true;
                boolean delayed = false;
                if (current.getString("flight_status").equals("scheduled") || current.getString("flight_status").equals("active")) {
                    cancelled = false;
                }

                String delayedDate = current.getJSONObject("departure").getString("estimated");

                int delayedYear = Integer.parseInt(delayedDate.substring(0,4));
                int delayedMonth = Integer.parseInt(delayedDate.substring(5,7));
                int delayedDay = Integer.parseInt(delayedDate.substring(8,10));

                int delayedHour = Integer.parseInt(delayedDate.substring(startHourTIndex + 1, startHourTIndex + 3));
                int delayedMinute = Integer.parseInt(delayedDate.substring(startHourTIndex + 4, startHourTIndex + 6));

                Month delayedM = parseToMonth(delayedMonth);
                LocalDateTime delayedTime = LocalDateTime.of(delayedYear, delayedM, delayedDay, delayedHour, delayedMinute);

                if (!startTime.equals(delayedTime)) {
                    delayed = true;
                }

                //todo decide whether delayTime just as LocalDateTime or as number of hours minutes by which delayed (both works
                int minutes = (int) ChronoUnit.MINUTES.between(startTime, delayedTime);
                int hours = (int) ChronoUnit.HOURS.between(startTime, delayedTime);
                //System.out.println(minutes);
                //System.out.println(hours);

                int delayHours = minutes / 60;
                int delayMinutes = minutes % 60;

                //System.out.println("The flight is delayed by " + delayHours + " hour(s) and " + delayMinutes + " minute(s).");

                //todo add airport info to flight
                //todo fix when coords arent available for location: done
                //todo maybe optimize parser in order for coords to be taken from parameter right away (flight class)

                double[] startCoords = fetchCoordsForGivenAddress(fromName);
                double[] endCoords = fetchCoordsForGivenAddress(toName);

                //System.out.println(Arrays.toString(startCoords));
                //System.out.println(Arrays.toString(endCoords));
                Location startLocation;
                Location endLocation;
                if (startCoords != null) {
                    startLocation = new Location(fromName, startCoords[1], startCoords[0]);
                } else {
                    startLocation = new Location(fromName, -1, -1);
                }
                if (endCoords != null) {
                    endLocation = new Location(toName, endCoords[1], endCoords[0]);
                } else {
                    endLocation = new Location(fromName, -1, -1);
                }

                Flight currentFlight = new Flight(number, startTime, endTime, gate, terminal, seat, airline, startLocation, endLocation);
                currentFlight.setDelayed(delayed);
                currentFlight.setCancelled(cancelled);
                currentFlight.setDelayTime(delayedTime);
                currentFlight.setDelayMinutes(delayMinutes);
                currentFlight.setDelayHours(delayHours);
                flights.add(currentFlight);
                counter++;
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

    public static Month parseToMonth(int month) throws NoSuchElementException {
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
                "   \"0\":{\n" +
                "      \"flight_date\":\"2022-06-26\",\n" +
                "      \"flight_status\":\"active\",\n" +
                "      \"departure\":{\n" +
                "         \"airport\":\"Franz Josef Strauss\",\n" +
                "         \"timezone\":\"Europe\\/Berlin\",\n" +
                "         \"iata\":\"MUC\",\n" +
                "         \"icao\":\"EDDM\",\n" +
                "         \"terminal\":null,\n" +
                "         \"gate\":null,\n" +
                "         \"delay\":10,\n" +
                "         \"scheduled\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"arrival\":{\n" +
                "         \"airport\":\"Mc Carran International\",\n" +
                "         \"timezone\":\"America\\/Los_Angeles\",\n" +
                "         \"iata\":\"LAS\",\n" +
                "         \"icao\":\"KLAS\",\n" +
                "         \"terminal\":null,\n" +
                "         \"gate\":null,\n" +
                "         \"baggage\":null,\n" +
                "         \"delay\":null,\n" +
                "         \"scheduled\":\"2022-06-26T20:06:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T20:06:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"airline\":{\n" +
                "         \"name\":\"empty\",\n" +
                "         \"iata\":null,\n" +
                "         \"icao\":null\n" +
                "      },\n" +
                "      \"flight\":{\n" +
                "         \"number\":null,\n" +
                "         \"iata\":null,\n" +
                "         \"icao\":null,\n" +
                "         \"codeshared\":null\n" +
                "      },\n" +
                "      \"aircraft\":null,\n" +
                "      \"live\":null\n" +
                "   },\n" +
                "   \"1\":{\n" +
                "      \"flight_date\":\"2022-06-26\",\n" +
                "      \"flight_status\":\"scheduled\",\n" +
                "      \"departure\":{\n" +
                "         \"airport\":\"Franz Josef Strauss\",\n" +
                "         \"timezone\":\"Europe\\/Berlin\",\n" +
                "         \"iata\":\"MUC\",\n" +
                "         \"icao\":\"EDDM\",\n" +
                "         \"terminal\":\"1\",\n" +
                "         \"gate\":null,\n" +
                "         \"delay\":null,\n" +
                "         \"scheduled\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"arrival\":{\n" +
                "         \"airport\":\"Mc Carran International\",\n" +
                "         \"timezone\":\"America\\/Los_Angeles\",\n" +
                "         \"iata\":\"LAS\",\n" +
                "         \"icao\":\"KLAS\",\n" +
                "         \"terminal\":\"3\",\n" +
                "         \"gate\":null,\n" +
                "         \"baggage\":null,\n" +
                "         \"delay\":null,\n" +
                "         \"scheduled\":\"2022-06-26T21:05:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T21:05:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"airline\":{\n" +
                "         \"name\":\"Eurowings Discover\",\n" +
                "         \"iata\":\"4Y\",\n" +
                "         \"icao\":\"OCN\"\n" +
                "      },\n" +
                "      \"flight\":{\n" +
                "         \"number\":\"56\",\n" +
                "         \"iata\":\"4Y56\",\n" +
                "         \"icao\":\"OCN56\",\n" +
                "         \"codeshared\":null\n" +
                "      },\n" +
                "      \"aircraft\":null,\n" +
                "      \"live\":null\n" +
                "   },\n" +
                "   \"2\":{\n" +
                "      \"flight_date\":\"2022-06-26\",\n" +
                "      \"flight_status\":\"active\",\n" +
                "      \"departure\":{\n" +
                "         \"airport\":\"Franz Josef Strauss\",\n" +
                "         \"timezone\":\"Europe\\/Berlin\",\n" +
                "         \"iata\":\"MUC\",\n" +
                "         \"icao\":\"EDDM\",\n" +
                "         \"terminal\":\"1\",\n" +
                "         \"gate\":\"C26\",\n" +
                "         \"delay\":15,\n" +
                "         \"scheduled\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"arrival\":{\n" +
                "         \"airport\":\"Mc Carran International\",\n" +
                "         \"timezone\":\"America\\/Los_Angeles\",\n" +
                "         \"iata\":\"LAS\",\n" +
                "         \"icao\":\"KLAS\",\n" +
                "         \"terminal\":\"3\",\n" +
                "         \"gate\":null,\n" +
                "         \"baggage\":null,\n" +
                "         \"delay\":null,\n" +
                "         \"scheduled\":\"2022-06-26T21:05:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T21:05:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"airline\":{\n" +
                "         \"name\":\"Finnair\",\n" +
                "         \"iata\":\"AY\",\n" +
                "         \"icao\":\"FIN\"\n" +
                "      },\n" +
                "      \"flight\":{\n" +
                "         \"number\":\"56\",\n" +
                "         \"iata\":\"AY56\",\n" +
                "         \"icao\":\"FIN56\",\n" +
                "         \"codeshared\":null\n" +
                "      },\n" +
                "      \"aircraft\":null,\n" +
                "      \"live\":null\n" +
                "   },\n" +
                "   \"3\":{\n" +
                "      \"flight_date\":\"2022-06-26\",\n" +
                "      \"flight_status\":\"active\",\n" +
                "      \"departure\":{\n" +
                "         \"airport\":\"Franz Josef Strauss\",\n" +
                "         \"timezone\":\"Europe\\/Berlin\",\n" +
                "         \"iata\":\"MUC\",\n" +
                "         \"icao\":\"EDDM\",\n" +
                "         \"terminal\":\"1\",\n" +
                "         \"gate\":\"C26\",\n" +
                "         \"delay\":15,\n" +
                "         \"scheduled\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"arrival\":{\n" +
                "         \"airport\":\"Mc Carran International\",\n" +
                "         \"timezone\":\"America\\/Los_Angeles\",\n" +
                "         \"iata\":\"LAS\",\n" +
                "         \"icao\":\"KLAS\",\n" +
                "         \"terminal\":\"3\",\n" +
                "         \"gate\":null,\n" +
                "         \"baggage\":null,\n" +
                "         \"delay\":null,\n" +
                "         \"scheduled\":\"2022-06-26T21:05:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T21:05:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"airline\":{\n" +
                "         \"name\":\"Lufthansa\",\n" +
                "         \"iata\":\"LH\",\n" +
                "         \"icao\":\"DLH\"\n" +
                "      },\n" +
                "      \"flight\":{\n" +
                "         \"number\":\"4388\",\n" +
                "         \"iata\":\"LH4388\",\n" +
                "         \"icao\":\"DLH4388\",\n" +
                "         \"codeshared\":{\n" +
                "            \"airline_name\":\"finnair\",\n" +
                "            \"airline_iata\":\"ay\",\n" +
                "            \"airline_icao\":\"fin\",\n" +
                "            \"flight_number\":\"56\",\n" +
                "            \"flight_iata\":\"ay56\",\n" +
                "            \"flight_icao\":\"fin56\"\n" +
                "         }\n" +
                "      },\n" +
                "      \"aircraft\":null,\n" +
                "      \"live\":null\n" +
                "   },\n" +
                "   \"4\":{\n" +
                "      \"flight_date\":\"2022-06-26\",\n" +
                "      \"flight_status\":\"active\",\n" +
                "      \"departure\":{\n" +
                "         \"airport\":\"Franz Josef Strauss\",\n" +
                "         \"timezone\":\"Europe\\/Berlin\",\n" +
                "         \"iata\":\"MUC\",\n" +
                "         \"icao\":\"EDDM\",\n" +
                "         \"terminal\":\"1\",\n" +
                "         \"gate\":\"C26\",\n" +
                "         \"delay\":15,\n" +
                "         \"scheduled\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"arrival\":{\n" +
                "         \"airport\":\"Mc Carran International\",\n" +
                "         \"timezone\":\"America\\/Los_Angeles\",\n" +
                "         \"iata\":\"LAS\",\n" +
                "         \"icao\":\"KLAS\",\n" +
                "         \"terminal\":\"3\",\n" +
                "         \"gate\":null,\n" +
                "         \"baggage\":null,\n" +
                "         \"delay\":null,\n" +
                "         \"scheduled\":\"2022-06-26T21:05:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T21:05:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"airline\":{\n" +
                "         \"name\":\"SWISS\",\n" +
                "         \"iata\":\"LX\",\n" +
                "         \"icao\":\"SWR\"\n" +
                "      },\n" +
                "      \"flight\":{\n" +
                "         \"number\":\"9326\",\n" +
                "         \"iata\":\"LX9326\",\n" +
                "         \"icao\":\"SWR9326\",\n" +
                "         \"codeshared\":{\n" +
                "            \"airline_name\":\"finnair\",\n" +
                "            \"airline_iata\":\"ay\",\n" +
                "            \"airline_icao\":\"fin\",\n" +
                "            \"flight_number\":\"56\",\n" +
                "            \"flight_iata\":\"ay56\",\n" +
                "            \"flight_icao\":\"fin56\"\n" +
                "         }\n" +
                "      },\n" +
                "      \"aircraft\":null,\n" +
                "      \"live\":null\n" +
                "   },\n" +
                "   \"5\":{\n" +
                "      \"flight_date\":\"2022-06-26\",\n" +
                "      \"flight_status\":\"active\",\n" +
                "      \"departure\":{\n" +
                "         \"airport\":\"Franz Josef Strauss\",\n" +
                "         \"timezone\":\"Europe\\/Berlin\",\n" +
                "         \"iata\":\"MUC\",\n" +
                "         \"icao\":\"EDDM\",\n" +
                "         \"terminal\":\"1\",\n" +
                "         \"gate\":\"C26\",\n" +
                "         \"delay\":15,\n" +
                "         \"scheduled\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T18:25:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"arrival\":{\n" +
                "         \"airport\":\"Mc Carran International\",\n" +
                "         \"timezone\":\"America\\/Los_Angeles\",\n" +
                "         \"iata\":\"LAS\",\n" +
                "         \"icao\":\"KLAS\",\n" +
                "         \"terminal\":\"3\",\n" +
                "         \"gate\":null,\n" +
                "         \"baggage\":null,\n" +
                "         \"delay\":null,\n" +
                "         \"scheduled\":\"2022-06-26T21:05:00+00:00\",\n" +
                "         \"estimated\":\"2022-06-26T21:05:00+00:00\",\n" +
                "         \"actual\":null,\n" +
                "         \"estimated_runway\":null,\n" +
                "         \"actual_runway\":null\n" +
                "      },\n" +
                "      \"airline\":{\n" +
                "         \"name\":\"Austrian\",\n" +
                "         \"iata\":\"OS\",\n" +
                "         \"icao\":\"AUA\"\n" +
                "      },\n" +
                "      \"flight\":{\n" +
                "         \"number\":\"8477\",\n" +
                "         \"iata\":\"OS8477\",\n" +
                "         \"icao\":\"AUA8477\",\n" +
                "         \"codeshared\":{\n" +
                "            \"airline_name\":\"finnair\",\n" +
                "            \"airline_iata\":\"ay\",\n" +
                "            \"airline_icao\":\"fin\",\n" +
                "            \"flight_number\":\"56\",\n" +
                "            \"flight_iata\":\"ay56\",\n" +
                "            \"flight_icao\":\"fin56\"\n" +
                "         }\n" +
                "      },\n" +
                "      \"aircraft\":null,\n" +
                "      \"live\":null\n" +
                "   },\n" +
                "   \"success\":true\n" +
                "}", null, null);
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
