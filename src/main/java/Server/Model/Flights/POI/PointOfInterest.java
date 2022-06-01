package Server.Model.Flights.POI;

public class PointOfInterest {

    private int id = -1; //defaults to nonexistent
    private String name;
    private PointOfInterestType pointOfInterestType;

    public PointOfInterest(String name, PointOfInterestType pointOfInterestType) {
        this.name = name;
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
}
