package Server.Flights.POI;

public class PointOfInterest {

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

    public PointOfInterestType getPointOfInterestType() {
        return pointOfInterestType;
    }

    public void setPointOfInterestType(PointOfInterestType pointOfInterestType) {
        this.pointOfInterestType = pointOfInterestType;
    }
}
