package REST;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import Server.Controllers.LocationController;
import Server.Model.Flights.Location;
import Server.Model.Flights.POI.PointOfInterest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class LocationREST {

    private LocationController locationController;

    public LocationREST(LocationController locationController){
        this.locationController = locationController;
    }


    @PostMapping("locations")
    public ResponseEntity<Location> createLocation(@RequestBody Location location){
        if (location.getID() != -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(locationController.saveLocation(location));
    }

    @PutMapping("locations/{locationID}")
    public ResponseEntity<Location> updateLocation(@RequestBody Location newLocation, @PathVariable("locationID") Integer locationID){
        if(! (newLocation.getID() == locationID) ){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(locationController.saveLocation(newLocation));
    }

    @GetMapping("locations/{locationID}")
    public ResponseEntity<Location> getLocation(@PathVariable("locationID") Integer locationID){
        return ResponseEntity.ok(locationController.getLocation(locationID));
    }

    @GetMapping("locations/{locationID}")
    public ResponseEntity<List<PointOfInterest>> getPOISOfLocation(@PathVariable("locationID") Integer locationID){
        return ResponseEntity.ok(locationController.getLocation(locationID).getPointsOfInterest());
    }


    @DeleteMapping("locations/{locationID}")
    public ResponseEntity<Void> deleteLocation(@PathVariable("locationID") Integer locationID){
        locationController.removeLocation(locationID);
        return ResponseEntity.noContent().build();
    }


}
