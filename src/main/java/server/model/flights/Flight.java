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

    /**
     * A wrapper method used to retrieve flights from one location to another at a specific date.
     *
     * @param from The name of the departure location formatted in "name (IATA)"
     * @param to The name of the arrival location formatted in "name (IATA)"
     * @param date The date at which the flights are to be retrieved
     * @return A list of 5 or more flights from "from" to "to" at "date", which (if the API does not find any real flights) are stocked up by dummy flights
     */


    /**
     * A method used for autocompletion of city names in the client
     *
     * @param city The name of a city (typed in by the user)
     * @return A string array of city suggestions (retrieved by the Airlabs API)
     */
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
