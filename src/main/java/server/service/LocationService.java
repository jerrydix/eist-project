package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.flights.Location;
import server.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location getLocationWithId(long id) {
        return locationRepository.findByLocationId(id);
    }
}
