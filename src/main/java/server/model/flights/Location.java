package server.model.flights;

import server.model.flights.poi.PointOfInterest;
import server.model.parsing.AirportParser;
import server.model.parsing.CityParser;
import server.model.parsing.PointOfInterestParser;
import server.model.flights.weather.Weather;
import server.model.networking.HTTP_GetRequest;

import java.util.ArrayList;
import java.util.List;

public class Location {

    static int currentID = 0;
    List<PointOfInterest> poiList;
    private int locationID = -1; //defaults to -1 if nonexistent
    private String name;
    private Weather weather;
    private double longitude;
    private double latitude;
    private List<String> airports;
    private String iata;

    public Location(String name, double longitude, double latitude) {
        this.name = name;
        this.weather = Weather.fetchWeather(longitude, latitude);
        this.longitude = longitude;
        this.latitude = latitude;
        this.airports = new ArrayList<>();
        locationID = currentID;
        currentID++;
        this.poiList = PointOfInterest.fetchPOIs(longitude, latitude, this);
        if (poiList != null) {
            for (PointOfInterest pointOfInterest : poiList) {
                pointOfInterest.setLocation(this);
            }
        }
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public static int getCurrentID() {
        return currentID;
    }

    public static void setCurrentID(int currentID) {
        Location.currentID = currentID;
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

    private void fetchCurrentCityIATACode() {
        try {
            this.iata = CityParser.parseCityJson(HTTP_GetRequest.httpRequest("https://airlabs.co/api/v9/suggest", new String[]{"?q=" + this.name, "&api_key=18d0b081-fd7f-4c9e-a723-a05e8ff627cf",}))[1];
        } catch (NullPointerException e) {
            System.out.println("iata of " + this.name + " is null");
        }
    }

    public static String[] fetchCityIATACode(String name) {
        return CityParser.parseCityJson(HTTP_GetRequest.httpRequest("https://airlabs.co/api/v9/suggest", new String[]{"?q=" + name, "&api_key=18d0b081-fd7f-4c9e-a723-a05e8ff627cf",}));
    }

    //todo keep / remove?
    public void fetchAirports() {
        this.fetchCurrentCityIATACode();
        this.airports = AirportParser.parseAirportJson(HTTP_GetRequest.httpRequest("https://airlabs.co/api/v9/airports", new String[]{}));
    }

    public List<Flight> find10Flights(String to, String date) {
        return Flight.fetchFlightsFromToAt(this.name, to, date);
    }

    public int getID() {
        return locationID;
    }

    public List<PointOfInterest> getPointsOfInterest() {
        return this.poiList;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    public static void main(String[] args) {
        Location location = new Location("Berlin", 52.517714, 13.394122);
        System.out.println(location);
        System.out.println("\n\n");
        System.out.println(location.getWeather());
        System.out.println("\n\n");
        System.out.println(PointOfInterestParser.toString(location.poiList));
    }
}
