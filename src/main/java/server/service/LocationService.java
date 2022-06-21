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
            if (location.getID() == locationID) {
                return location;
            }
        }
        return null;
    }

    public Location getLocationByName(String name) {
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return location;
            }
        }

        //TODO: if location not in list of already created locations, use outgoing API to fetch new location
        return null;
    }

    public Location getLocation(Integer locationID) {
        return getLocationWithID(locationID);
    }

    public void removeLocation(Integer locationID) {
        this.locations.removeIf(location -> location.getID() == locationID);
    }

}
