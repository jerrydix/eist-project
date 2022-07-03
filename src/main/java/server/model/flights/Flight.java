package server.model.flights;

import server.model.networking.HTTP_GetRequest;
import server.model.parsing.FlightParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Flight {
    private String number;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String gate;
    private String terminal;
    private int seat;
    private String airline;
    private boolean isCancelled;
    private boolean isDelayed;
    private LocalDateTime delayTime;
    private int delayHours;
    private int delayMinutes;
    private Location startLocation;
    private Location endLocation;
    private String airplane;

    public Flight(String number, LocalDateTime startTime, LocalDateTime endTime, String gate, String terminal, int seat,
                  String airline, Location startLocation, Location endLocation, String airplane) {
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.gate = gate;
        this.terminal = terminal;
        this.seat = seat;
        this.airline = airline;
        this.isCancelled = false;
        this.isDelayed = false;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.airplane = airplane;
    }

    public static List<Flight> fetchFlightsFromToAt(String from, String to, String date) {
        String dayStr = date.substring(0, 2);
        String monthStr = date.substring(3, 5);
        String year = date.substring(6);
        String flightDate = year + "-" + monthStr + "-" + dayStr;

        // todo replace with parser for name and iata

        String fromIATA = from.substring(from.indexOf("(") + 1, from.indexOf(")"));
        String toIATA = to.substring(to.indexOf("(") + 1, to.indexOf(")"));
        String fromName = from.substring(0, from.indexOf("(") - 1);
        String toName = to.substring(0, to.indexOf("(") - 1);
        System.out.println(fromName);
        System.out.println(toName);
        System.out.println(fromIATA);
        System.out.println(toIATA);

        List<Flight> list = FlightParser.parseFlightJson(
                HTTP_GetRequest.httpRequest("https://app.goflightlabs.com/flights", new String[]{
                        "?access_key=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiNmM1ZjFjNDVmZGExNDNlODcwNDhkOGRmNzcyOTZhMThhNTMyNTNjNWUzYjIxMWUzNTA3OTAyMzlmMDVkYzk3ODAxNDQ5ZGM1MzI0MmY0N2QiLCJpYXQiOjE2NTYyMzY1MTIsIm5iZiI6MTY1NjIzNjUxMiwiZXhwIjoxNjg3NzcyNTEyLCJzdWIiOiI3MDg0Iiwic2NvcGVzIjpbXX0.jr7CLxzMAJETsHmt2YfH6OBb53pJvEcXNqDuTArRGCNX2AHxGPocVyax2RcaC0zL3u61qZe2g1NzEM0typORcQ",
                        "&arr_scheduled_time_dep=" + flightDate, "&dep_iata=" + fromIATA, "&arr_iata=" + toIATA}),
                fromName, toName);

        Random r = new Random();
        int amount = r.nextInt(5, 15);
        if (list != null) {
            while (list.size() < amount) {
                list.add(FlightFactory.generateFlight(fromName, toName, date));
            }
        } else {
            List<Flight> fakeFlights = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                fakeFlights.add(FlightFactory.generateFlight(fromName, toName, date));
            }
            return fakeFlights;
        }
        return list;
    }

    public static String[] getSuggestions(String city) {
        return Location.fetchCityIATACode(city);
    }

    public static void main(String[] args) {
        /*
         * Flight flight = new Flight("1", LocalDateTime.of(1994, Month.APRIL,
         * 15,11,30), LocalDateTime.of(1994, Month.APRIL, 15,11,30),"1","a", 1,
         * "Lufthansa",
         * new Location("test", -1, -1), new Location("test", -1, -1));
         * System.out.println(flight);
         */

        List<Flight> flights = fetchFlightsFromToAt("Amsterdam (MUC)", "New York City (CDG)", "28/06/2022");
        System.out.println(flights);
        //System.out.println(flights.get(0).getStartLocation().getPoiList().toString());
        //System.out.println(flights.get(0).getEndLocation().getPoiList().toString());

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getDelayHours() {
        return delayHours;
    }

    public void setDelayHours(int delayHours) {
        this.delayHours = delayHours;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(int delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    public void setDelayed(boolean delayed) {
        this.isDelayed = delayed;
    }

    public LocalDateTime getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(LocalDateTime delayTime) {
        this.delayTime = delayTime;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public String toString() {
        return "\n\nAirline: " + airline + "\nNumber: " + number + "\nTerminal: " + terminal + "\nGate: " + gate
                + "\nDeparture time: " + startTime + "\nArrival time: " + endTime;
    }
}
