package REST;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import Server.Controllers.POIController;
import Server.Model.Flights.POI.PointOfInterest;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class PoiREST {

    private POIController poiController;

    public PoiREST(POIController poiController){
        this.poiController = poiController;
    }


    @PostMapping("pois")
    public ResponseEntity<PointOfInterest> createPointOfInterest(@RequestBody PointOfInterest poi){
        if (poi.getID() != -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(poiController.savePointOfInterest(poi));
    }

    @PutMapping("pois/{poiID}")
    public ResponseEntity<PointOfInterest> updatePointOfInterest(@RequestBody PointOfInterest newPointOfInterest, @PathVariable("poiID") Integer poiID){
        if(! (newPointOfInterest.getID() == poiID) ){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(poiController.savePointOfInterest(newPointOfInterest));
    }

    @GetMapping("pois/{poiID}")
    public ResponseEntity<PointOfInterest> getPointOfInterest(@PathVariable("poiID") Integer poiID){
        return ResponseEntity.ok(poiController.getPointOfInterest(poiID));
    }

    @DeleteMapping("pois/{poiID}")
    public ResponseEntity<Void> deletePointOfInterest(@PathVariable("poiID") Integer poiID){
        poiController.removePointOfInterest(poiID);
        return ResponseEntity.noContent().build();
    }


}