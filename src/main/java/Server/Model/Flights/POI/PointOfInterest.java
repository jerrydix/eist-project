package Server.Model.Flights.POI;

import Server.Model.Flights.Location;

public class PointOfInterest {

    private int id = -1; //defaults to nonexistent
    private String name;
    private Location location;
    private PointOfInterestType pointOfInterestType;

    public PointOfInterest(String name, Location location, PointOfInterestType pointOfInterestType) {
        this.name = name;
        this.location = location;
        this.pointOfInterestType = pointOfInterestType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID(){
        return id;
    }

    

    public PointOfInterestType getPointOfInterestType() {
        return pointOfInterestType;
    }

    public void setPointOfInterestType(PointOfInterestType pointOfInterestType) {
        this.pointOfInterestType = pointOfInterestType;
    }
    
    public Location getLocation(){
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
