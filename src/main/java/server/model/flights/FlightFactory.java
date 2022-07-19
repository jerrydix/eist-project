package server.model.flights;

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
            "Air Hams", "Claudian Air", "Schosair", "Cremers", "OCamlFly", "OnlyFlights", "Garching Airlines", "Pretschnerwings"};
    private static final String[] cities = new String[]{"Berlin", "Rome", "Dubai", "Paris", "London", "Los Angeles", "Frankfurt", "Budapest", "Tehran",
            "Sydney", "Warsaw", "Vienna", "Madrid", "Mumbai"};
    private static final String[] planes = new String[]{"Boeing 737-800", "Boeing 737-700", "Airbus A320", "Airbus A321", "Bombardier CRJ200", "Boeing 757-200",
            "Embraer E175", "Airbus A319", "Boeing 737-900ER", "Bombardier CRJ900", "Boeing 737-800"};

    /**
     * A method to generate a random dummy flight (used to top up the real flights, if there aren't enough) from a location to another at a specific date.
     *
     * @param from The name of the departure location
     * @param to   The name of the arrival location
     * @param date The date at which the dummy flight is to have its departure and arrival
     * @return A dummy flight from "from" to "to" at "date"
     */
    public static Flight generateFlight(String from, String to, String date) {
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
        Flight flight = new Flight(flightID, startTime, endTime, gate, terminal, seat, airline, startLocation, endLocation, generateRandomAirplane());
        flight.setDelayed(delayed);
        flight.setDelayTime(delayedTime);
        flight.setDelayedArrivalTime(delayedArrivalTime);
        flight.setDelayMinutes(delayMinutes);
        flight.setDelayHours(delayHours);
        flight.setCancelled(false);
        return flight;
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