package Server.Model.Flights;

import java.util.List;

import Server.Model.Flights.POI.PointOfInterest;
import Server.Model.Flights.Weather.Weather;

public class Location {

    static int currentID = 0;
    private int locationID = -1; //defaults to -1 if nonexistent
    private String name;
    private Weather weather;
    private double longitude;
    private double latidute;

    List<PointOfInterest> poiList;

    public Location(String name, List<PointOfInterest> poiList, double longitude, double latitude) {
        this.name = name;
        this.weather = Weather.fetchWeather(latitude, longitude);
        this.poiList = poiList;
        this.longitude = longitude;
        this.latidute = latitude;
        locationID = currentID;
        currentID++;
        for (PointOfInterest pointOfInterest : poiList) {
            pointOfInterest.setLocation(this);
        }
    }

    public int getID(){
        return locationID;
    }

    public List<PointOfInterest> getPointsOfInterest(){
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
}
