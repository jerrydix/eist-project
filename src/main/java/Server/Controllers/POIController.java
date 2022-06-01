package Server.Controllers;

import java.util.ArrayList;
import java.util.List;

import Server.Model.Flights.POI.PointOfInterest;

public class POIController {

    private List<PointOfInterest> pois;

    public POIController(){
        this.pois = new ArrayList<PointOfInterest>();
    }

    private PointOfInterest getPointOfInterestWithID(int surveyID){
        for(PointOfInterest survey : pois){
            if(survey.getID() == surveyID){
                return survey;
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

    public void removePointOfInterest(Integer surveyID) {
        this.pois.removeIf(poi -> poi.getID() == surveyID);
    }
    
}
