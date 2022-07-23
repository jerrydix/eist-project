package server.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import server.model.flights.Flight;
import server.model.flights.FlightFactory;
import server.model.flights.Location;
import server.networking.HTTP_GetRequest;
import server.utility.KeyReader;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

//TODO DO NOT EXECUTE MAIN METHOD, WE GOTTA SAFE THEM API REQUESTS
public class FlightParser {

    /**
     * parseFlightJson() parses the flights contained in a given api response into a list of "Flight" objects
     *
     * @param jsonText The get request response of the Flightlabs API (Real-time flights) containing an array of flights from "fromName" to "toName" with their respective attributes
     * @param fromName The name of the departure location
     * @param toName   the name of the arrival location
     * @return A list of all found flights from "fromName" to "toName"
     */
    public static List<Flight> parseFlightJson(String jsonText) {
        try {
            List<Flight> flights = new ArrayList<>();
            JSONArray array = new JSONArray(jsonText);

            for (int i = 0; i < array.length(); i++) {

                JSONObject current = array.getJSONObject(i);

                String number = current.getJSONObject("flight").getString("iata");
                if (number == null || number.equals("null")) {
                    Random r = new Random();
                    String digits = String.format("%04d", r.nextInt(10000));

                    number = "NUL" + digits;
                }
                String startDate = current.getString("flight_date");
                int startYear = Integer.parseInt(startDate.substring(0, 4));
                int startMonth = Integer.parseInt(startDate.substring(5, 7));
                int startDay = Integer.parseInt(startDate.substring(8));

                int startHourTIndex = current.getJSONObject("departure").getString("scheduled").indexOf("T");
                int startHour = Integer.parseInt(current.getJSONObject("departure").getString("scheduled").substring(startHourTIndex + 1, startHourTIndex + 3));
                int startMinute = Integer.parseInt(current.getJSONObject("departure").getString("scheduled").substring(startHourTIndex + 4, startHourTIndex + 6));

                Month startM = parseToMonth(startMonth);
                LocalDateTime startTime = LocalDateTime.of(startYear, startM, startDay, startHour, startMinute);

                String endDate = current.getJSONObject("arrival").getString("scheduled");

                int endYear = Integer.parseInt(endDate.substring(0, 4));
                int endMonth = Integer.parseInt(endDate.substring(5, 7));
                int endDay = Integer.parseInt(endDate.substring(8, 10));

                int endHour = Integer.parseInt(endDate.substring(startHourTIndex + 1, startHourTIndex + 3));
                int endMinute = Integer.parseInt(endDate.substring(startHourTIndex + 4, startHourTIndex + 6));

                Month endM = parseToMonth(endMonth);
                LocalDateTime endTime = LocalDateTime.of(endYear, endM, endDay, endHour, endMinute);

                String gate = current.getJSONObject("departure").getString("gate");

                Random r = new Random();
                if (gate.equals("null")) {
                    gate = String.valueOf(r.nextInt(1, 89));
                }
                String terminal = current.getJSONObject("departure").getString("terminal");
                if (terminal.equals("null")) {
                    terminal = String.valueOf(r.nextInt(1, 5));
                }
                String seat = FlightFactory.generateSeat();
                String airline = current.getJSONObject("airline").getString("name");
                if (airline.equals("empty")) {
                    airline = FlightFactory.pickAirline(r.nextInt(0, 13));
                }

                boolean cancelled = false;

                Flight currentFlight = new Flight(number, startTime, endTime, gate, terminal, seat, airline, null, null, FlightFactory.generateRandomAirplane());
                currentFlight.setDelayed(false);
                currentFlight.setCancelled(cancelled);
                flights.add(currentFlight);
            }
            return flights;
        } catch (JSONException exception) {
            System.out.println("Couldn't parse flights");

        }
        return null;
    }

    public static double[] fetchCoordsForGivenAddress(String address) {
        address = address.replaceAll(" ", "");
        return GeocodingParser.parseGeocodingJson(HTTP_GetRequest.httpRequest("https://maps.googleapis.com/maps/api/geocode/json", new String[]{"?address=" + address, "&key=" + KeyReader.getAPIkey()}));
    }

    public static Month parseToMonth(int month) throws NoSuchElementException {
        switch (month) {
            case 1 -> {
                return Month.JANUARY;
            }
            case 2 -> {
                return Month.FEBRUARY;
            }
            case 3 -> {
                return Month.MARCH;
            }
            case 4 -> {
                return Month.APRIL;
            }
            case 5 -> {
                return Month.MAY;
            }
            case 6 -> {
                return Month.JUNE;
            }
            case 7 -> {
                return Month.JULY;
            }
            case 8 -> {
                return Month.AUGUST;
            }
            case 9 -> {
                return Month.SEPTEMBER;
            }
            case 10 -> {
                return Month.OCTOBER;
            }
            case 11 -> {
                return Month.NOVEMBER;
            }
            case 12 -> {
                return Month.DECEMBER;
            }
            default -> throw new NoSuchElementException("No such month");
        }
    }

    public static void main(String[] args) throws JSONException {
    }

    public String toString(List<Flight> flights) {
        StringBuilder builder = new StringBuilder();
        for (Flight flight : flights) {
            builder.append(flight.toString());
            builder.append("\n\n");
        }
        return builder.toString();
    }

    public String localDateTimeToDate(LocalDateTime localDateTime) {
        String date = localDateTime.toString();
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);

        return day + "/" + month + "/" + year;
    }
}
