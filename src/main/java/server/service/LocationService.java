package server.service;

import org.springframework.stereotype.Service;
import server.model.flights.FlightFactory;
import server.model.flights.Location;
import server.repository.LocationRepository;

import java.util.Optional;

@Service
public class LocationService {


    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location saveLocation(Location location) {
        Optional<Location> optLoc = locationRepository.findByName(location.getName());
        return optLoc.orElseGet(() -> locationRepository.save(location));
    }

    public Optional<Location> findByName(String locationName) {
        return locationRepository.findByName(locationName);
    }

    public Location getLocationWithName(String locationName) {
        Optional<Location> optLoc = this.findByName(locationName);
        Location location;
        if (optLoc.isEmpty()) {
            location = this.saveLocation(FlightFactory.fetchLocation(locationName));
        } else {
            location = optLoc.get();
        }
        return location;
    }

    public Location getLocationWithIATA(String locationNameWithIATA) {
        String locationName = FlightFactory.getName(locationNameWithIATA);
        return this.getLocationWithName(locationName);
    }

    public Location getLocationWithId(long id) {
        return locationRepository.findById(id).orElse(null);
    }
}
