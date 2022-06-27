package server.model.flights;

import server.model.parsing.FlightParser;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class FlightFactory {

    public static Flight generateFlight(String from, String to, String date) {
        Random r = new Random();
        int fromHour;
        int toHour;
        boolean firstHalf = r.nextBoolean();
        if (firstHalf) {
            fromHour = r.nextInt(0, 5);
            toHour = r.nextInt(6, 11);
        } else {
            fromHour = r.nextInt(12, 18);
            toHour = r.nextInt(19, 23);
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

        int minutes = (int) ChronoUnit.MINUTES.between(startTime, delayedTime);
        int delayHours = 0;
        int delayMinutes = 0;

        boolean delayed = r.nextBoolean();
        int delay;
        if (delayed) {
            delayedTime = LocalDateTime.of(year, FlightParser.parseToMonth(month), day, fromHour, delayMinute);
            delayHours = minutes / 60;
            delayMinutes = minutes % 60;
        }

        String gate = String.valueOf(r.nextInt(1,80));
        String terminal = String.valueOf(r.nextInt(1,5));
        int seat = r.nextInt(1,288);

        String[] sampleAirlines = new String[]{"Lufthansa", "Qatar Airways", "American Airlines", "Air Berlin", "EasyJet", "British Airways", "Emirates", "Air France", "China Southern Airlines", "Ryanair", "Garching Airlines", "Condor", "Germanwings"};
        String id = String.format("%04d", r.nextInt(10000));

        int number = r.nextInt(0, 12);
        String airline = sampleAirlines[number];

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

        return new Flight(flightID, startTime, endTime, gate, terminal, seat, airline, startLocation, endLocation);
    }

    private static String pickIATA(int index) {
        String prefix;
        switch (index) {
            case 0 -> prefix = "LH";
            case 1 -> prefix = "QR";
            case 2 -> prefix = "AA";
            case 3 -> prefix = "AB";
            case 4 -> prefix = "U2";
            case 5 -> prefix = "BA";
            case 6 -> prefix = "EK";
            case 7 -> prefix = "AF";
            case 8 -> prefix = "CZ";
            case 9 -> prefix = "FR";
            case 10 -> prefix = "GA";
            case 11 -> prefix = "DE";
            case 12 -> prefix = "4U";
            default -> prefix = "GA";
        }
        return prefix;
    }
}
