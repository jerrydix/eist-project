package server.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import server.service.POIService;
import server.model.flights.poi.PointOfInterest;



import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class PoiRest {

    private POIService poiService;

    public PoiRest(POIService poiService){
        this.poiService = poiService;
    }


    @PostMapping("pois")
    public ResponseEntity<PointOfInterest> createPointOfInterest(@RequestBody PointOfInterest poi){
        if (poi.getID() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(poiService.savePointOfInterest(poi));
    }

    @PutMapping("pois/{poiID}")
    public ResponseEntity<PointOfInterest> updatePointOfInterest(@RequestBody PointOfInterest newPointOfInterest, @PathVariable("poiID") Integer poiID){
        if(!newPointOfInterest.getID().equals(poiID)){ // todo fix this
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(poiService.savePointOfInterest(newPointOfInterest));
    }

    @GetMapping("pois/{poiID}")
    public ResponseEntity<Object> getPointOfInterest(@PathVariable("poiID") Integer poiID, @RequestParam String getReq){
        return switch (getReq) {
            case "POI"-> ResponseEntity.ok(poiService.getPointOfInterest(poiID));
            case "POI_Location"-> ResponseEntity.ok(poiService.getPointOfInterest(poiID).getLocation());
            default -> ResponseEntity.badRequest().build();
        };
    }

    @DeleteMapping("pois/{poiID}")
    public ResponseEntity<Void> deletePointOfInterest(@PathVariable("poiID") Integer poiID){
        poiService.removePointOfInterest(poiID);
        return ResponseEntity.noContent().build();
    }
}
