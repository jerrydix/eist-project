package server.model.flights;

import server.model.networking.HTTP_GetRequest;
import server.model.parsing.FlightParser;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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

    public Flight(String number, LocalDateTime startTime, LocalDateTime endTime, String gate, String terminal, int seat,
                  String airline, Location startLocation, Location endLocation) {
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
        return airline;
    }

    public void setAirplane(String airplane) {
        this.airline = airplane;
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
        return "Number: " + number + "\nTerminal: " + terminal + "\nGate: " + gate + "\nStart Time: " + startTime + "\nEnd Time: " + endTime;
    }

    //todo make a parser for localdatetime

    public static List<Flight> fetch10FlightsFromToAt(String from, String to, String date) {
        String dayStr = date.substring(0,2);
        String monthStr = date.substring(3,5);
        String year = date.substring(6);
        String flightDate = year + "-" + monthStr + "-" + dayStr;

        System.out.println(flightDate);

        String[] fromData = Location.fetchCityIATACode(from);
        String[] toData = Location.fetchCityIATACode(to);

        String fromName = fromData[0];
        String fromIATA = fromData[1];
        String toName = toData[0];
        String toIATA = toData[1];

        List<Flight> list = FlightParser.parseFlightJson(HTTP_GetRequest.httpRequest("https://app.goflightlabs.com/flights", new String[]{"?access_key=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiNmM1ZjFjNDVmZGExNDNlODcwNDhkOGRmNzcyOTZhMThhNTMyNTNjNWUzYjIxMWUzNTA3OTAyMzlmMDVkYzk3ODAxNDQ5ZGM1MzI0MmY0N2QiLCJpYXQiOjE2NTYyMzY1MTIsIm5iZiI6MTY1NjIzNjUxMiwiZXhwIjoxNjg3NzcyNTEyLCJzdWIiOiI3MDg0Iiwic2NvcGVzIjpbXX0.jr7CLxzMAJETsHmt2YfH6OBb53pJvEcXNqDuTArRGCNX2AHxGPocVyax2RcaC0zL3u61qZe2g1NzEM0typORcQ", "arr_scheduled_time_dep=" + flightDate, "&dep_iata=" + fromIATA, "&arr_iata=" + toIATA}), fromName, toName);
        if (list != null) {
            while (list.size() < 10) {
                list.add(FlightFactory.generateFlight(from, to, date));
            }
        } else {
            List<Flight> fakeFlights = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                fakeFlights.add(FlightFactory.generateFlight(from, to, date));
            }
            return fakeFlights;
        }
        return list;
    }

    public static void main(String[] args) {
        /*Flight flight = new Flight("1", LocalDateTime.of(1994, Month.APRIL, 15,11,30), LocalDateTime.of(1994, Month.APRIL, 15,11,30),"1","a", 1, "Lufthansa",
        new Location("test", -1, -1), new Location("test", -1, -1));
        System.out.println(flight);*/

        System.out.println(fetch10FlightsFromToAt("Munich", "Las Vegas", "26/06/2022"));
    }
}
