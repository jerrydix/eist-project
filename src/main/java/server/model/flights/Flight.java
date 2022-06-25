package server.model.flights;

import java.time.LocalDateTime;
import java.time.Month;
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

    public List<Flight> fetch10FlightsFromToAt(String fromAirport, Location toAirport, LocalDateTime localDateTime) {
        int month = localDateTime.getMonth().getValue();
        int day = localDateTime.getDayOfMonth();
        String monthStr = String.valueOf(localDateTime.getMonth().getValue());
        String dayStr = String.valueOf(localDateTime.getDayOfMonth());

        if (Integer.toString(month).length() == 1) {
            monthStr = "0" + localDateTime.getMonth().getValue();
        }
        if (Integer.toString(day).length() == 1) {
            dayStr = "0" + localDateTime.getDayOfMonth();
        }
        String flightDate = localDateTime.getYear() + "-" + monthStr + "-" + dayStr;
        //todo
        //return FlightParser.parseFlightJson(HTTP_GetRequest.httpRequest("http://api.aviationstack.com/v1/flights", new String[]{"?access_key=8df0ff3c6cd266e3219eed88b44cc2ee", "?limit=10", "?flight_date=" + flightDate, ""})
        return null;
    }

    public static void main(String[] args) {
        Flight flight = new Flight("1", LocalDateTime.of(1994, Month.APRIL, 15,11,30), LocalDateTime.of(1994, Month.APRIL, 15,11,30),"1","a", 1, "Lufthansa",
        new Location("test", -1, -1), new Location("test", -1, -1));
        System.out.println(flight);
    }
}
