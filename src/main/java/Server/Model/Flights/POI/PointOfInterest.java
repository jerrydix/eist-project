package Server.Model.Flights.POI;

import Server.Model.Flights.Location;
import Server.Model.Networking.HTTP_GetRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PointOfInterest {

    private String id;
    private String name;
    private String address;
    private String pointOfInterestType;
    private Location location;
    private double rating;
    private double longitude;
    private double latitude;

    public PointOfInterest(String id, String name, String address, String pointOfInterestType, Location location, double rating, double longitude, double latitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.pointOfInterestType = pointOfInterestType;
        this.location = location;
        this.rating = rating;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static List<PointOfInterest> fetchPOIs(double longitude,double latitude){
        return PointOfInterestParser.parsePOIJson(HTTP_GetRequest.httpRequest("https://maps.googleapis.com/maps/api/place/nearbysearch/json", new String[]{"?location=" + longitude + "%2C" + latitude, "&radius=30000" , "&rankby=prominence", "&type=tourist_attraction", "&key=AIzaSyBKiScI4WumTVipTbFuC6KPHic3dC66tvM"}));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID(){
        return id;
    }

    public String getPointOfInterestType() {
        return pointOfInterestType;
    }

    public void setPointOfInterestType(String pointOfInterestType) {
        this.pointOfInterestType = pointOfInterestType;
    }
    
    public String getLocation(){
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nAddress: " + address + "\nPOI Type: " + pointOfInterestType + "\nLocation: " + location + "\nRating: " + rating;
    }
}
