package server.service;

import org.springframework.stereotype.Service;
import server.model.flights.Location;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private List<Location> locations;

    public LocationService() {
        this.locations = new ArrayList<Location>();
    }

    private Location getLocationWithID(int locationID) {
        for (Location location : locations) {
            if (location.getLocationID() == locationID) {
                return location;
            }
        }
        return null;
    }


    public Location getLocation(Integer locationID) {
        return getLocationWithID(locationID);
    }


}
