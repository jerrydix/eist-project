package Server.Model.Flights;

import java.util.List;

import Server.Model.Flights.POI.PointOfInterest;
import Server.Model.Flights.Weather.Weather;

public class Destination {

    int destinationID = -1; //defaults to -1 if nonexistent
    String name;
    Weather weather;
    List<PointOfInterest> poiList;

    public Destination(String name, Weather weather, List<PointOfInterest> poiList) {
        this.name = name;
        this.weather = weather;
        this.poiList = poiList;
        for (PointOfInterest pointOfInterest : poiList) {
            pointOfInterest.setDestination(this);
        }
    }

    public int getID(){
        return destinationID;
    }

    public List<PointOfInterest> getPointsOfInterest(){
        return this.poiList;
    }
}
