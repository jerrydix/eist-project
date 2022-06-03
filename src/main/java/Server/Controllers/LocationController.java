package Server.Controllers;

import java.util.ArrayList;
import java.util.List;

import Server.Model.Flights.Location;

public class LocationController {

    private List<Location> locations;

    public LocationController(){
        this.locations = new ArrayList<Location>();
    }

    private Location getLocationWithID(int locationID){
        for(Location location : locations){
            if(location.getID() == locationID){
                return location;
            }
        }
        return null;
    }

    public Location saveLocation(Location location) {
        //TODO: //used in updating an existing and adding new surveys
        return null;
    }

    public Location getLocation(Integer locationID) {
        return getLocationWithID(locationID);
    }

    public void removeLocation(Integer locationID) {
        this.locations.removeIf(location -> location.getID() == locationID);
    }
    
}
