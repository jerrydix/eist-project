package server.model.flights;

import server.model.parsing.FlightParser;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FlightFactory {
    private static final List<String> IATAcodes = Arrays.asList("TU", "PP", "XA", "KR", "LS", "AH", "CA", "SC", "CR", "OC", "OF", "GA", "PW");
    private static final List<String> airlines = Arrays.asList("TUMAir", "PinguPinguWings", "Excellence Airways", "Krusche Airlines", "Lasser Schafways", "Air Hams", "Claudian Air",
            "Schosair", "Cremers", "OCamlFly", "OnlyFlights", "Garching Airlines", "Pretschnerwings");

    private static final List<String> cities = Arrays.asList("Berlin", "Rome", "Dubai", "Paris", "London", "Los Angeles",
            "Frankfurt", "Budapest", "Tehran", "Sydney", "Warsaw", "Vienna", "Madrid", "Mumbai");

    private static final List<String> planes = Arrays.asList("Boeing 737-800", "Boeing 737-700", "Airbus A320", "Airbus A321", "Bombardier CRJ200",
            "Boeing 757-200", "Embraer E175", "Airbus A319", "Boeing 737-900ER", "Bombardier CRJ900", "Boeing 737-800");

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
        int fromMinute = r.nextInt(0, 31);
        int toMinute = r.nextInt(0, 59);

        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6));

        int delayMinute = r.nextInt(32, 59);

        LocalDateTime startTime = LocalDateTime.of(year, FlightParser.parseToMonth(month), day, fromHour, fromMinute);
        LocalDateTime endTime = LocalDateTime.of(year, FlightParser.parseToMonth(month), day, toHour, toMinute);
        LocalDateTime delayedTime = null;

        int delayHours = 0;
        int delayMinutes = 0;

        boolean delayed = r.nextBoolean();

        if (delayed) {
            delayedTime = LocalDateTime.of(year, FlightParser.parseToMonth(month), day, fromHour, delayMinute);
            int minutes = (int) ChronoUnit.MINUTES.between(startTime, delayedTime);
            delayHours = minutes / 60;
            delayMinutes = minutes % 60;
        }

        String gate = String.valueOf(r.nextInt(1, 80));
        String terminal = String.valueOf(r.nextInt(1, 5));
        int seat = r.nextInt(1, 288);

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
            endLocation = new Location(from, -1, -1);
        }
        Flight flight = new Flight(flightID, startTime, endTime, gate, terminal, seat, airline, startLocation, endLocation, generateRandomAirplane());
        flight.setDelayed(delayed);
        flight.setDelayHours(delayHours);
        flight.setDelayMinutes(delayMinutes);
        flight.setCancelled(false);
        return flight;
    }

    private static String pickIATA(int index) {
        return IATAcodes.get(index);
    }

    private static String pickLocationString(int index) {
        return cities.get(index);
    }

    public static FlightJourney generateRandomJourney(String flightNum) {
        List<Flight> flights = new ArrayList<>();
        Flight flight = FlightFactory.generateRandomFlight();
        flights.add(flight);
        flight.setNumber(flightNum);
        FlightJourney journey = new FlightJourney();
        journey.buildJourney(flights);
        return journey;
    }

    private static Flight generateRandomFlight() {
        Random r = new Random();
        int first = r.nextInt(0, 12);
        int second;
        do {
            second = r.nextInt(0, 12);
        } while (first == second);
        LocalDateTime now = LocalDateTime.now();
        String date = now.toString();
        String day = date.substring(8, 10);
        String month = date.substring(5, 7);
        String year = date.substring(0, 4);
        String finDate = day + "/" + month + "/" + year;
        return generateFlight(pickLocationString(first), pickLocationString(second), finDate);
    }

    public static String generateRandomAirplane() {
        Random r = new Random();
        return planes.get(r.nextInt(0, 10));
    }

    public static String pickAirline(int index) {
        return airlines.get(index);
    }

    public static void main(String[] args) {
        System.out.println(generateRandomFlight());
    }
}