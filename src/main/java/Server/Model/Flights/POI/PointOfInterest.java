package Server.Model.Flights.POI;

import Server.Model.Flights.Location;

public class PointOfInterest {

    private String id;
    private String name;
    private String address;
    private String pointOfInterestType;
    private Location location;
    private double rating;

    public PointOfInterest(String id, String name, String address, String pointOfInterestType, Location location, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.pointOfInterestType = pointOfInterestType;
        this.location = location;
        this.rating = rating;
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

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nAddress: " + address + "\nPOI Type: " + pointOfInterestType + "\nLocation: " + location + "\nRating: " + rating;
    }
}
