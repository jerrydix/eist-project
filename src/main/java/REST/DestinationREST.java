package REST;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import Server.Controllers.DestinationController;
import Server.Model.Flights.Destination;
import Server.Model.Flights.POI.PointOfInterest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class DestinationREST {

    private DestinationController destinationController;

    public DestinationREST(DestinationController destinationController){
        this.destinationController = destinationController;
    }


    @PostMapping("destinations")
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination){
        if (destination.getID() != -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(destinationController.saveDestination(destination));
    }

    @PutMapping("destinations/{destinationID}")
    public ResponseEntity<Destination> updateDestination(@RequestBody Destination newDestination, @PathVariable("destinationID") Integer destinationID){
        if(! (newDestination.getID() == destinationID) ){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(destinationController.saveDestination(newDestination));
    }

    @GetMapping("destinations/{destinationID}")
    public ResponseEntity<Destination> getDestination(@PathVariable("destinationID") Integer destinationID){
        return ResponseEntity.ok(destinationController.getDestination(destinationID));
    }

    @GetMapping("destinations/{destinationID}")
    public ResponseEntity<List<PointOfInterest>> getPOISOfDestination(@PathVariable("destinationID") Integer destinationID){
        return ResponseEntity.ok(destinationController.getDestination(destinationID).getPointsOfInterest());
    }


    @DeleteMapping("destinations/{destinationID}")
    public ResponseEntity<Void> deleteDestination(@PathVariable("destinationID") Integer destinationID){
        destinationController.removeDestination(destinationID);
        return ResponseEntity.noContent().build();
    }


}
