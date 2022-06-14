package Server.Model.Flights;

import Server.Model.Flights.POI.PointOfInterest;
import Server.Model.Flights.POI.PointOfInterestParser;
import Server.Model.Flights.Weather.Weather;
import Server.Model.Flights.Weather.WeatherParser;

import java.util.List;

public class Location {

    static int currentID = 0;
    List<PointOfInterest> poiList;
    private int locationID = -1; //defaults to -1 if nonexistent
    private String name;
    private Weather weather;
    private double longitude;
    private double latidute;

    public Location(String name, double longitude, double latitude) {
        this.name = name;
        this.weather = Weather.fetchWeather(latitude, longitude);
        this.longitude = longitude;
        this.latidute = latitude;
        locationID = currentID;
        currentID++;
        this.poiList = PointOfInterest.fetchPOIs(longitude, latitude, this);
        if (poiList != null) {
            for (PointOfInterest pointOfInterest : poiList) {
                pointOfInterest.setLocation(this);
            }
        }
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

    public double getLatidute() {
        return latidute;
    }

    public void setLatidute(double latitude) {
        this.latidute = latitude;
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
