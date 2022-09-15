package server.service;

import org.springframework.stereotype.Service;
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
        if (locationRepository.existsById(location.getLocationId())) {
            return null;
        }
        return locationRepository.save(location);
    }

    public Optional<Location> getLocationWithName(String locationName) {
        return locationRepository.findByName(locationName);
    }

    public Location getLocationWithId(long id) {
        return locationRepository.findById(id).orElse(null);
    }
}
