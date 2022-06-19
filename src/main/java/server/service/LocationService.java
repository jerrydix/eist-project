package server.service;

import java.util.ArrayList;
import java.util.List;

import server.model.flights.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private List<Location> locations;

    public LocationService(){
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
