package Flights;

import Flights.POI.PointOfInterest;
import Flights.Weather.Weather;
import Flights.Weather.WeatherType;

import java.util.List;

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
