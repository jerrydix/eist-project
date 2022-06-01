package Server.Flights;

import java.util.List;

import Server.Flights.POI.PointOfInterest;
import Server.Flights.Weather.Weather;
import Server.Flights.Weather.WeatherType;

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
