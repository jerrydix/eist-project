package Server.Model.Flights.POI;

import Server.Model.Flights.Destination;

public class PointOfInterest {

    private int id = -1; //defaults to nonexistent
    private String name;
    private Destination destination;
    private PointOfInterestType pointOfInterestType;

    public PointOfInterest(String name, Destination destination, PointOfInterestType pointOfInterestType) {
        this.name = name;
        this.destination = destination;
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
    
    public Destination getDestination(){
        return this.destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

}
