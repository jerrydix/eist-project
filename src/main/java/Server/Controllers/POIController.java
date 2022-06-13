package Server.Controllers;

import java.util.ArrayList;
import java.util.List;

import Server.Model.Flights.POI.PointOfInterest;

public class POIController {

    private List<PointOfInterest> pois;

    public POIController(){
        this.pois = new ArrayList<PointOfInterest>();
    }

    private PointOfInterest getPointOfInterestWithID(int poiID){
        for(PointOfInterest poi : pois){
            if(poi.getID().equals(poiID)){ // todo fix
                return poi;
            }
        }
        return null;
    }

    public PointOfInterest savePointOfInterest(PointOfInterest poi) {
        //TODO: //used in updating an existing and adding new surveys
        return null;
    }

    public PointOfInterest getPointOfInterest(Integer poiID) {
        return getPointOfInterestWithID(poiID);
    }

    public void removePointOfInterest(Integer poiID) {
        this.pois.removeIf(poi -> poi.getID().equals(poiID)); // todo fix
    }
    
}
