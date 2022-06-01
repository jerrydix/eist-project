package Server.Model.Flights;

import java.util.List;

import Server.Model.Flights.POI.PointOfInterest;
import Server.Model.Flights.Weather.Weather;
import Server.Model.Flights.Weather.WeatherType;

public class Destination {

    String name;
    Weather weather;
    List<PointOfInterest> poiList;

    public Destination(String name, Weather weather, List<PointOfInterest> poiList) {
        this.name = name;
        this.weather = weather;
        this.poiList = poiList;
    }
}
