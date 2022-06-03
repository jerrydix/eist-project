package Server.Model.Flights;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import Server.Model.Flights.Weather.Weather;
import Server.Model.Flights.Weather.WeatherType;

public class Flight {
    private int number;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int gate;
    private String terminal;
    private int seat;
    private String airplane;
    private boolean cancelled;
    private boolean delayed;
    private Time delayTime;
    private Destination startDestination;
    private Destination endDestination;

    public Flight(int number, LocalDateTime startTime, LocalDateTime endTime, int gate, String terminal, int seat,
                  String airplane, Destination startDestination, Destination endDestination) {
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.gate = gate;
        this.terminal = terminal;
        this.seat = seat;
        this.airplane = airplane;
        this.cancelled = false;
        this.delayed = false;
        this.delayTime = new Time(121212);
        this.startDestination = startDestination;
        this.endDestination = endDestination;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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

    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
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
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public Time getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Time delayTime) {
        this.delayTime = delayTime;
    }

    public Destination getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(Destination startDestination) {
        this.startDestination = startDestination;
    }

    public Destination getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(Destination endDestination) {
        this.endDestination = endDestination;
    }

    public String toString() {
        return "Number: " + number + "\nTerminal: " + terminal + "\nGate: " + gate + "\nStart Time: " + startTime + "\nEnd Time: " + endTime;
    }

    public static void main(String[] args) {
        Flight flight = new Flight(1, LocalDateTime.of(1994, Month.APRIL, 15,11,30), LocalDateTime.of(1994, Month.APRIL, 15,11,30),1,"a", 1, "Lufthansa", new Destination("test", new Weather(WeatherType.FOGGY, 23), new ArrayList<>()), new Destination("test", new Weather(WeatherType.FOGGY, 23), new ArrayList<>()));
        System.out.println(flight);
    }
}
