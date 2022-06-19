package server.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import server.service.LocationService;
import server.model.flights.Location;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class LocationRest {

    private LocationService locationService;

    public LocationRest(LocationService locationService){
        this.locationService = locationService;
    }


    @PostMapping("locations")
    public ResponseEntity<Location> createLocation(@RequestBody Location location){
        if (location.getID() != -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(locationService.saveLocation(location));
    }

    @PutMapping("locations/{locationID}")
    public ResponseEntity<Location> updateLocation(@RequestBody Location newLocation, @PathVariable("locationID") Integer locationID){
        if(! (newLocation.getID() == locationID) ){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(locationService.saveLocation(newLocation));
    }

    @GetMapping("locations/{locationID}")
    public ResponseEntity<Location> getLocation(@PathVariable("locationID") Integer locationID){
        return ResponseEntity.ok(locationService.getLocation(locationID));
    }

    @DeleteMapping("locations/{locationID}")
    public ResponseEntity<Void> deleteLocation(@PathVariable("locationID") Integer locationID){
        locationService.removeLocation(locationID);
        return ResponseEntity.noContent().build();
    }
}
