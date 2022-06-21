package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.model.flights.Location;
import server.service.LocationService;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class LocationRest {

    private LocationService locationService;

    public LocationRest(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("locations")
    public ResponseEntity<Location> getLocation(@RequestParam("name") String locationName) {
        return ResponseEntity.ok(locationService.getLocationByName(locationName));
    }

}
