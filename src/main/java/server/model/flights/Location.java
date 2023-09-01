package server.model.flights;

import server.model.flights.poi.PointOfInterest;
import server.model.flights.poi.Position;
import server.model.flights.weather.Weather;
import server.networking.HTTP_GetRequest;
import server.parsing.CityParser;
import server.parsing.PointOfInterestParser;

import javax.persistence.*;
import java.util.List;

@Entity
public class Location {

    private final static String API_KEY = "&api_key=a519803c-a657-4b02-91e8-00b20a42c630";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long locationId;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<PointOfInterest> pointsOfInterest;
    private String name;

    @OneToOne(cascade = {CascadeType.ALL})
    private Weather weather;
    private double latitude;
    private double longitude;


    private String iata;

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.weather = Weather.fetchWeather(latitude, longitude);
        this.pointsOfInterest = PointOfInterest.fetchPOIs(latitude, longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Location() {

    }

    /**
     * Wrapper method to fetch the IATA code and (autocompleted) name of a location
     *
     * @param name The (not necessarily complete) name of the location
     * @return A string array that has the location name on index 0 and the IATA on index 1
     */
    public static String[] fetchCityIATACode(String name) {
        return CityParser.parseCityJson(HTTP_GetRequest.httpRequest("https://airlabs.co/api/v9/suggest", new String[]{"?q=" + name, API_KEY,}));
    }

    public static void main(String[] args) {
        Location location = new Location("Berlin", 52.517714, 13.394122);
        System.out.println(location);
        System.out.println("\n\n");
        System.out.println(location.getWeather());
        System.out.println("\n\n");
        System.out.println(PointOfInterestParser.toString(location.pointsOfInterest));
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }


    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    /**
     * Wrapper method to fetch the current location IATA
     */
    private void fetchCurrentCityIATACode() {
        try {
            this.iata = CityParser.parseCityJson(HTTP_GetRequest.httpRequest("https://airlabs.co/api/v9/suggest", new String[]{"?q=" + this.name, API_KEY,}))[1];
        } catch (NullPointerException e) {
            System.out.println("iata of " + this.name + " is null");
        }
    }


    /**
     * Returns ten flights from the current location to "to" at "date"
     *
     * @param to   Arrival location
     * @param date The date at which the ten flights are supposed to depart
     * @return A list of ten flights
     */

    public List<Flight> find10Flights(String to, String date) {
        return FlightFactory.fetchFlightsFromToAt(this.name, to, date);
    }

    public List<PointOfInterest> getPointsOfInterest() {
        return this.pointsOfInterest;
    }

    public void setPointsOfInterest(List<PointOfInterest> pointsOfInterest) {
        this.pointsOfInterest = pointsOfInterest;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        }
        return this.getLocationId() == ((Location) obj).getLocationId();
    }

    public Position getPosition() {
        return new Position(latitude, longitude);
    }

    public void setPosition(Position position) {
        if (position != null) {
            this.latitude = position.getLat();
            this.longitude = position.getLng();
        }
    }

}
