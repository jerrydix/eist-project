package Server.Model.Flights;

import java.util.List;

import Server.Model.Flights.POI.PointOfInterest;
import Server.Model.Flights.Weather.Weather;

public class Location {

    static int currentID = 0;
    private int locationID = -1; //defaults to -1 if nonexistent
    private double latitude;
    private double longitude;
    private String name;
    private Weather weather;
    List<PointOfInterest> poiList;

    public Location(String name, Weather weather,double longitude, double latitude, List<PointOfInterest> poiList) {
        this.name = name;
        this.weather = weather;
        this.longitude = longitude;
        this.latitude = latitude;
        this.poiList = poiList;
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
}
