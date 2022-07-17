package server.model.flights;

import server.model.flights.poi.PointOfInterest;
import server.model.flights.poi.Position;
import server.model.flights.weather.Weather;
import server.networking.HTTP_GetRequest;
import server.parsing.AirportParser;
import server.parsing.CityParser;
import server.parsing.PointOfInterestParser;

import java.util.ArrayList;
import java.util.List;

public class Location {

    static int currentID = 0;
    private static List<Location> locationList = new ArrayList<>();
    List<PointOfInterest> poiList;
    private int locationID = -1; //defaults to -1 if nonexistent
    private String name;
    private Weather weather;

    private Position position;
    private List<String> airports;
    private String iata;

    public Location(String name, double longitude, double latitude) {
        this.name = name;
        this.weather = Weather.fetchWeather(longitude, latitude);
        this.airports = new ArrayList<>();
        locationID = currentID;
        currentID++;
        this.poiList = PointOfInterest.fetchPOIs(longitude, latitude);
        locationList.add(this);
        this.position = new Position(latitude, longitude);
    }

    public static List<Location> getLocationList() {
        return locationList;
    }

    public static void setLocationList(List<Location> locationList) {
        Location.locationList = locationList;
    }

    /**
     * Wrapper method to fetch the IATA code and (autocompleted) name of a location
     *
     * @param name The (not necessarily complete) name of the location
     * @return A string array that has the location name on index 0 and the IATA on index 1
     */
    public static String[] fetchCityIATACode(String name) {
        return CityParser.parseCityJson(HTTP_GetRequest.httpRequest("https://airlabs.co/api/v9/suggest", new String[]{"?q=" + name, "&api_key=18d0b081-fd7f-4c9e-a723-a05e8ff627cf",}));
    }

    public static void main(String[] args) {
        Location location = new Location("Berlin", 52.517714, 13.394122);
        System.out.println(location);
        System.out.println("\n\n");
        System.out.println(location.getWeather());
        System.out.println("\n\n");
        System.out.println(PointOfInterestParser.toString(location.poiList));
    }

    public static Location getLocationWithId(int id) {
        for (Location location : locationList) {
            if (location.getLocationID() == id) {
                return location;
            }
        }
        return null;
    }

    public static PointOfInterest getPOIWithId(String id) {
        for (Location location : locationList) {
            for (PointOfInterest pointOfInterest : location.getPointsOfInterest()) {
                if (pointOfInterest.getID().equals(id)) {
                    return pointOfInterest;
                }
            }
        }
        return null;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public List<PointOfInterest> getPoiList() {
        return poiList;
    }

    public void setPoiList(List<PointOfInterest> poiList) {
        this.poiList = poiList;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public List<String> getAirports() {
        return airports;
    }

    public void setAirports(List<String> airports) {
        this.airports = airports;
    }

    /**
     * Wrapper method to fetch the current location IATA
     */
    private void fetchCurrentCityIATACode() {
        try {
            this.iata = CityParser.parseCityJson(HTTP_GetRequest.httpRequest("https://airlabs.co/api/v9/suggest", new String[]{"?q=" + this.name, "&api_key=18d0b081-fd7f-4c9e-a723-a05e8ff627cf",}))[1];
        } catch (NullPointerException e) {
            System.out.println("iata of " + this.name + " is null");
        }
    }

    //todo keep / remove?
    public void fetchAirports() {
        this.fetchCurrentCityIATACode();
        this.airports = AirportParser.parseAirportJson(HTTP_GetRequest.httpRequest("https://airlabs.co/api/v9/airports", new String[]{}));
    }

    /**
     * Returns ten flights from the current location to "to" at "date"
     *
     * @param to   Arrival location
     * @param date The date at which the ten flights are supposed to depart
     * @return A list of ten flights
     */

    public List<Flight> find10Flights(String to, String date) {
        return Flight.fetchFlightsFromToAt(this.name, to, date);
    }

    public List<PointOfInterest> getPointsOfInterest() {
        return this.poiList;
    }

    public double getLongitude() {
        return position.getLng();
    }

    public double getLatitude() {
        return position.getLat();
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

    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        }
        return this.getLocationID() == ((Location) obj).getLocationID();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
