package server.model.flights;

import server.networking.HTTP_GetRequest;
import server.parsing.AirportParser;
import server.parsing.FlightParser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Flight {

    @Id
    private String number;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String gate;
    private String terminal;
    private String seat;
    private String airline;
    private boolean isCancelled;
    private boolean isDelayed;
    private LocalDateTime delayTime;
    private String delayHours;
    private String delayMinutes;
    @OneToOne
    private Location startLocation;
    private String startName;
    private String endName;
    @OneToOne
    private Location endLocation;
    private String airplane;
    private String weatherType;
    private String weatherDegrees;
    private LocalDateTime delayedArrivalTime;
    private String departureDate;
    private String departureTime;
    private String arrivalTime;


    private String fullEndName;

    private String fullStartName;

    private int _uid;

    public Flight(String number, LocalDateTime startTime, LocalDateTime endTime, String gate, String terminal, String seat,
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
        if (startLocation != null && endLocation != null) {
            this.startName = startLocation.getName();
            this.endName = endLocation.getName();
            this.weatherDegrees = String.valueOf(endLocation.getWeather().getDegrees());
            this.weatherType = endLocation.getWeather().getWeatherType();
        }
        this.departureDate = this.startTime.toString().substring(8, 10) + "/" + this.startTime.toString().substring(5, 7) + "/" + this.startTime.toString().substring(0, 4);
        this.departureTime = this.startTime.toString().substring(11, 16);
        this.arrivalTime = this.endTime.toString().substring(11, 16);
    }

    protected Flight() {

    }

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
            flight.fullEndName = to;
            flight.fullStartName = from;
            flight.setStartLocation(startLocation);
            flight.setEndLocation(endLocation);
        }

        return list;
    }

    /**
     * A method used for autocompletion of city names in the client
     *
     * @param city The name of a city (typed in by the user)
     * @return A string array of city suggestions (retrieved by the Airlabs API)
     */
    public static String[] getSuggestions(String city) {
        String[] suggestions = Location.fetchCityIATACode(city);
        int newSize = 3;
        if (suggestions[2] == null || (suggestions[2].equals(suggestions[1]) || suggestions[2].isBlank())) {
            newSize = 2;
        }
        if (suggestions[1] == null || (suggestions[1].equals(suggestions[0]) || suggestions[1].isBlank())) {
            newSize = 1;
        }
        String[] newSug = new String[newSize];
        System.arraycopy(suggestions, 0, newSug, 0, newSize);
        return newSug;
    }

    public static void main(String[] args) {
        List<Flight> flights = fetchFlightsFromToAt("Berlin (BER)", "New York City (CDG)", "28/06/2022");
        System.out.println(flights);
    }

    public String getFullEndName() {
        return fullEndName;
    }

    public void setFullEndName(String fullEndName) {
        this.fullEndName = fullEndName;
    }

    public String getFullStartName() {
        return fullStartName;
    }

    public void setFullStartName(String fullStartName) {
        this.fullStartName = fullStartName;
    }

    public int get_uid() {
        return _uid;
    }

    public void set_uid(int _uid) {
        this._uid = _uid;
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

    public String getDelayHours() {
        return delayHours;
    }

    public void setDelayHours(String delayHours) {
        this.delayHours = delayHours;
    }

    public String getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(String delayMinutes) {
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

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
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
        this.startName = this.startLocation.getName();
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
        this.endName = endLocation.getName();
        this.weatherDegrees = String.valueOf(this.endLocation.getWeather().getDegrees());
        this.weatherType = this.endLocation.getWeather().getWeatherType();
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getWeatherDegrees() {
        return weatherDegrees;
    }

    public void setWeatherDegrees(String weatherDegrees) {
        this.weatherDegrees = weatherDegrees;
    }

    public LocalDateTime getDelayedArrivalTime() {
        return delayedArrivalTime;
    }

    public void setDelayedArrivalTime(LocalDateTime delayedArrivalTime) {
        this.delayedArrivalTime = delayedArrivalTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String toString() {
        return "\n\nAirline: " + airline + "\nNumber: " + number + "\nDeparture Location: " + startLocation + "\nArrival Location: " + endLocation + "\nTerminal: " + terminal + "\nGate: " + gate
                + "\nDeparture time: " + startTime + "\nArrival time: " + endTime + "\nSeat: " + seat + "\nDelay time: " + delayTime + "\nDelay hours: " + delayHours + "\nDelay minutes: " + delayMinutes + "\nAirplane: " + airline + "\nis delayed: " + isDelayed + "\nis cancelled: " + isCancelled;
    }
}
