package server.model.flights;

import server.networking.HTTP_GetRequest;
import server.parsing.AirportParser;
import server.parsing.FlightParser;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Flight factory is a class with static methods used to generate random dummy flights when it is necessary
 */
public class FlightFactory {
    private static final String[] IATAcodes = new String[]{"TU", "PP", "XA", "KR", "LS", "AH", "CA", "SC", "CR", "OC", "OF", "GA", "PW"};
    private static final String[] airlines = new String[]{"TUMAir", "PinguPinguWings", "Excellence Airways", "Krusche Airlines", "Lasser Schafways",
            "Air Hams", "Claudian Air", "Schosair", "Cremers Aviation", "OCamlFly", "OnlyFlights", "Garching Airlines", "Pretschnerwings"};
    private static final String[] cities = new String[]{"Berlin", "Rome", "Dubai", "Paris", "London", "Los Angeles", "Frankfurt", "Budapest", "Tehran",
            "Sydney", "Warsaw", "Vienna", "Madrid", "Mumbai"};
    private static final String[] planes = new String[]{"Boeing 737-800", "Boeing 737-700", "Airbus A320", "Airbus A321", "Bombardier CRJ200", "Boeing 757-200",
            "Embraer E175", "Airbus A319", "Boeing 737-900ER", "Bombardier CRJ900", "Boeing 737-800"};

    /**
     * A wrapper method used to retrieve flights from one location to another at a specific date.
     *
     * @param from The name of the departure location formatted in "name (IATA)"
     * @param to   The name of the arrival location formatted in "name (IATA)"
     * @param date The date at which the flights are to be retrieved
     * @return A list of 5 or more flights from "from" to "to" at "date", which (if the API does not find any real flights) are stocked up by dummy flights
     */
    public static List<Flight> fetchFlightsFromToAt(String from, String to, String date) {
        String fromIATA = from.substring(from.indexOf("(") + 1, from.indexOf(")"));
        String toIATA = to.substring(to.indexOf("(") + 1, to.indexOf(")"));
        String fromName = from.substring(0, from.indexOf("(") - 1);
        String toName = to.substring(0, to.indexOf("(") - 1);

        List<String> fromAirportIATAs = AirportParser.parseAirportJson(HTTP_GetRequest.httpRequest("https://airlabs.co/api/v9/airports?city_code=" + fromIATA + "&api_key=23c0135c-2b3c-4bc9-88f0-98aa15ac238c", new String[]{}));
        List<String> toAirportIATAs = AirportParser.parseAirportJson(HTTP_GetRequest.httpRequest("https://airlabs.co/api/v9/airports?city_code=" + toIATA + "&api_key=23c0135c-2b3c-4bc9-88f0-98aa15ac238c", new String[]{}));

        if (fromAirportIATAs != null) {
            fromIATA = fromAirportIATAs.get(0);
        }
        if (toAirportIATAs != null) {
            toIATA = toAirportIATAs.get(0);
        }

        List<Flight> list = new ArrayList<>();

        var currentList = FlightParser.parseFlightJson(
                HTTP_GetRequest.httpRequest("https://app.goflightlabs.com/flights", new String[]{
                        "?access_key=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiNzYwNzJlNGYyN2E0YTE3ZTM3ZjMzNDhmZDM1MDljNmRlMzM5MmViMTVkNjA4YjE1NDlmZjE1ZTE2OTA3YmY2M2U0OTk4MDQ4ODk2YTRmMWQiLCJpYXQiOjE2NTg1MTQ5MDAsIm5iZiI6MTY1ODUxNDkwMCwiZXhwIjoxNjkwMDUwODk5LCJzdWIiOiI5MDY0Iiwic2NvcGVzIjpbXX0.oXZM_CwLpe9gc5IlnQrTLf1jZ_Yv2wsjcG06kc_2NvSZiuk8Gs22QIScukCZrbuzTFI0Hr8vnzZJy3NmBsc81g",
                        "&arr_scheduled_time_dep=" + date, "&dep_iata=" + fromIATA, "&arr_iata=" + toIATA}));

        if (currentList != null && !currentList.isEmpty()) {
            list.addAll(currentList);
        }

        boolean first = true;
        if (fromAirportIATAs != null && toAirportIATAs != null && !fromAirportIATAs.isEmpty() && !toAirportIATAs.isEmpty()) {
            for (int i = 0; i < fromAirportIATAs.size(); i++) {
                if (toAirportIATAs.size() >= 2 && first) {
                    for (int j = 1; j < toAirportIATAs.size(); j++) {
                        List<Flight> current = FlightParser.parseFlightJson(
                                HTTP_GetRequest.httpRequest("https://app.goflightlabs.com/flights", new String[]{
                                        "?access_key=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiNzYwNzJlNGYyN2E0YTE3ZTM3ZjMzNDhmZDM1MDljNmRlMzM5MmViMTVkNjA4YjE1NDlmZjE1ZTE2OTA3YmY2M2U0OTk4MDQ4ODk2YTRmMWQiLCJpYXQiOjE2NTg1MTQ5MDAsIm5iZiI6MTY1ODUxNDkwMCwiZXhwIjoxNjkwMDUwODk5LCJzdWIiOiI5MDY0Iiwic2NvcGVzIjpbXX0.oXZM_CwLpe9gc5IlnQrTLf1jZ_Yv2wsjcG06kc_2NvSZiuk8Gs22QIScukCZrbuzTFI0Hr8vnzZJy3NmBsc81g",
                                        "&arr_scheduled_time_dep=" + date, "&dep_iata=" + fromAirportIATAs.get(i), "&arr_iata=" + toAirportIATAs.get(j)}));
                        if (current != null && !current.isEmpty()) {
                            list.addAll(current);
                        }
                    }
                } else if (!first) {
                    for (int j = 0; j < toAirportIATAs.size(); j++) {
                        List<Flight> current = FlightParser.parseFlightJson(
                                HTTP_GetRequest.httpRequest("https://app.goflightlabs.com/flights", new String[]{
                                        "?access_key=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiNzYwNzJlNGYyN2E0YTE3ZTM3ZjMzNDhmZDM1MDljNmRlMzM5MmViMTVkNjA4YjE1NDlmZjE1ZTE2OTA3YmY2M2U0OTk4MDQ4ODk2YTRmMWQiLCJpYXQiOjE2NTg1MTQ5MDAsIm5iZiI6MTY1ODUxNDkwMCwiZXhwIjoxNjkwMDUwODk5LCJzdWIiOiI5MDY0Iiwic2NvcGVzIjpbXX0.oXZM_CwLpe9gc5IlnQrTLf1jZ_Yv2wsjcG06kc_2NvSZiuk8Gs22QIScukCZrbuzTFI0Hr8vnzZJy3NmBsc81g",
                                        "&arr_scheduled_time_dep=" + date, "&dep_iata=" + fromAirportIATAs.get(i), "&arr_iata=" + toAirportIATAs.get(j)}));
                        if (current != null && !current.isEmpty()) {
                            list.addAll(current);
                        }
                    }
                }
                first = false;
            }
        }

        Random r = new Random();
        int amount = r.nextInt(5, 15);

        while (list.size() < amount) {
            list.add(FlightFactory.generateFlightWithoutLocation(fromName, toName, date));
        }

        double[] startCoords = FlightParser.fetchCoordsForGivenAddress(fromName);
        double[] endCoords = FlightParser.fetchCoordsForGivenAddress(toName);

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
            endLocation = new Location(toName, -1, -1);
        }


        for (Flight flight : list) {
            flight.setFullEndName(to);
            flight.setFullStartName(from);
            flight.setStartLocation(startLocation);
            flight.setEndLocation(endLocation);
        }

        return list;
    }

    /**
     * A wrapper method to generate a random dummy flight (used to top up the real flights, if there aren't enough) from a location to another at a specific date.
     *
     * @param from The name of the departure location
     * @param to   The name of the arrival location
     * @param date The date at which the dummy flight is to have its departure and arrival
     * @return A dummy flight from "from" to "to" at "date"
     */
    public static Flight generateFlight(String from, String to, String date) {
        Flight flight = generateFlightWithoutLocation(from, to, date);

        double[] startCoords = FlightParser.fetchCoordsForGivenAddress(from);
        double[] endCoords = FlightParser.fetchCoordsForGivenAddress(to);

        Location startLocation;
        Location endLocation;
        if (startCoords != null) {
            startLocation = new Location(from, startCoords[1], startCoords[0]);
        } else {
            startLocation = new Location(from, -1, -1);
        }
        if (endCoords != null) {
            endLocation = new Location(to, endCoords[1], endCoords[0]);
        } else {
            endLocation = new Location(to, -1, -1);
        }
        flight.setStartLocation(startLocation);
        flight.setEndLocation(endLocation);
        return flight;
    }


    public static Flight generateFlightWithoutLocation(String from, String to, String date) {
        Random r = new Random();
        int fromHour;
        int toHour;
        boolean firstHalf = r.nextBoolean();
        if (firstHalf) {
            fromHour = r.nextInt(0, 5);
            toHour = r.nextInt(6, 11);
        } else {
            fromHour = r.nextInt(7, 14);
            toHour = r.nextInt(15, 23);
        }
        int fromMinute = r.nextInt(0, 30);
        int toMinute = r.nextInt(0, 30);
        System.out.println(date);
        int day = date.length() > 10 ? Integer.parseInt(date.substring(8, 10)) : Integer.parseInt(date.substring(8));
        int month = Integer.parseInt(date.substring(5, 7));
        int year = Integer.parseInt(date.substring(0, 4));

        LocalDateTime startTime = LocalDateTime.of(year, FlightParser.parseToMonth(month), day, fromHour, fromMinute);
        LocalDateTime endTime = LocalDateTime.of(year, FlightParser.parseToMonth(month), day, toHour, toMinute);
        LocalDateTime delayedTime = null;
        LocalDateTime delayedArrivalTime = null;

        boolean delayed = r.nextBoolean();
        int dHours = 0;
        int dMinutes = 0;
        String delayHours = "";
        String delayMinutes = "";

        if (delayed) {
            dHours = r.nextInt(0, 3);
            dMinutes = r.nextInt(0, 59);
            delayedTime = startTime.plusHours(dHours);
            delayedTime = delayedTime.plusMinutes(dMinutes);
            delayedArrivalTime = endTime.plusHours(dHours);
            delayedArrivalTime = delayedArrivalTime.plusMinutes(dMinutes);
            int minutes = (int) ChronoUnit.MINUTES.between(startTime, delayedTime);
            dHours = minutes / 60;
            delayHours = String.valueOf(dHours);
            dMinutes = minutes % 60;
            delayMinutes = String.valueOf(dMinutes);
            if (dHours < 10) {
                delayHours = "0" + delayHours;
            }
            if (dMinutes < 10) {
                delayMinutes = "0" + delayMinutes;
            }
            System.out.println(delayMinutes);
        }

        String gate = String.valueOf(r.nextInt(1, 80));
        String terminal = String.valueOf(r.nextInt(1, 5));
        String seat = generateSeat();

        String id = String.format("%04d", r.nextInt(10000));

        int number = r.nextInt(0, 13);
        String airline = pickAirline(number);

        String flightID = pickIATA(number) + id;

        Flight flight = new Flight(flightID, startTime, endTime, gate, terminal, seat, airline, null, null, generateRandomAirplane());
        flight.setDelayed(delayed);
        flight.setDelayTime(delayedTime);
        flight.setDelayedArrivalTime(delayedArrivalTime);
        flight.setDelayMinutes(delayMinutes);
        flight.setDelayHours(delayHours);
        flight.setCancelled(false);
        return flight;
    }

    public static String generateRandomFlightNumber() {
        Random r = new Random();
        String id = String.format("%04d", r.nextInt(10000));

        int number = r.nextInt(0, 13);

        return pickIATA(number) + id;
    }


    public static String generateSeat() {
        Random r = new Random();
        int seatNum = r.nextInt(1, 34);
        int letterNum = r.nextInt(0, 6);
        String letter;
        switch (letterNum) {
            case 0 -> letter = "A";
            case 1 -> letter = "B";
            case 2 -> letter = "C";
            case 3 -> letter = "D";
            case 4 -> letter = "E";
            case 5 -> letter = "F";
            default -> letter = "C";
        }
        return seatNum + letter;
    }

    /**
     * Picks the IATA code of an airline in accordance to the airline used in the flight.
     *
     * @param index The index of the specified IATA code in the IATAcodes array
     * @return The IATA code of the airline, found at "index" in the IATAcodes array
     */
    private static String pickIATA(int index) {
        return IATAcodes[index];
    }

    /**
     * Picks the location at "index" in the cities array (used for the current flight of the user at the client landing page).
     *
     * @param index The index of the specified location in the cities array
     * @return The location found at "index" in the cities array
     */
    private static String pickLocationString(int index) {
        return cities[index];
    }

    /**
     * Generates a flight journey with one flight with the number "flightNum" (used in todo).
     *
     * @param flightNum he flight number of the generated flight
     * @return A flight journey consisting of one random flight with "flightNum" as the flight number
     */
    public static FlightJourney generateRandomJourney(String flightNum) {
        List<Flight> flights = new ArrayList<>();
        Flight flight = FlightFactory.generateRandomFlight(flightNum);
        flights.add(flight);
        FlightJourney journey = new FlightJourney();
        journey.buildJourney(flights);
        return journey;
    }

    /**
     * Generates a random flight which has the number "flightNum" (used as the current flight displayed on the landing page).
     *
     * @param flightNum The flight number of the generated flight
     * @return A random flight with the given flight number
     */
    private static Flight generateRandomFlight(String flightNum) {
        Random r = new Random();
        int first = r.nextInt(0, 12);
        int second;
        do {
            second = r.nextInt(0, 12);
        } while (first == second);

        LocalDateTime now = LocalDateTime.now();
        String date = now.toString();
        Flight flight = generateFlight(pickLocationString(first), pickLocationString(second), date);
        flight.setNumber(flightNum);
        String airline = pickAirline(11);
        for (int i = 0; i < IATAcodes.length; i++) {
            if (flightNum.substring(0, 2).equals(IATAcodes[i])) {
                airline = pickAirline(i);
            }
        }
        flight.setAirline(airline);
        return flight;
    }

    /**
     * Generates a random airplane type (displayed as airplane type on the client side).
     *
     * @return A random airplane type
     */
    public static String generateRandomAirplane() {
        Random r = new Random();
        return planes[r.nextInt(0, 10)];
    }

    /**
     * Picks the airline "index" from the airlines array.
     *
     * @param index The index of the airlines array at which the airline is returned
     * @return The airline name at "index" in the airlines array
     */
    public static String pickAirline(int index) {
        return airlines[index];
    }

    public static void main(String[] args) {
        Flight flight = generateRandomFlight("PW1234");
        System.out.println(flight);
        System.out.println(flight.getStartLocation().getPointsOfInterest());
        System.out.println(flight.getEndLocation().getPointsOfInterest());
    }
}