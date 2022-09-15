package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.flights.poi.PointOfInterest;
import server.service.POIService;

import java.util.List;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class PoiRest {

    private final POIService poiService;


    public PoiRest(POIService poiService) {
        this.poiService = poiService;
    }

    @PostMapping("api/favourite")
    public ResponseEntity<List<PointOfInterest>> addToFavourites(@RequestParam String id, @RequestParam int locationID) {
        List<PointOfInterest> pois = poiService.saveFavourite(id, locationID);
        if (pois == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(pois);
    }

    @PostMapping("api/unfavourite")
    public ResponseEntity<List<PointOfInterest>> removeFromFavourites(@RequestParam String id, @RequestParam int locationID) {
        List<PointOfInterest> pois = poiService.unsaveFavourite(id, locationID);
        if (pois == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(pois);
    }

    @GetMapping("api/poi")
    public ResponseEntity<List<PointOfInterest>> getPointsOfInterest(@RequestParam int locationID) {
        List<PointOfInterest> poiList = poiService.getPointsOfInterest(locationID);
        if (poiList == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(poiList);
    }

    @GetMapping("api/poitop")
    public ResponseEntity<List<PointOfInterest>> getTopPointsOfInterest(@RequestParam int locationID) {
        List<PointOfInterest> poiList = poiService.getTopPointsOfInterest(locationID);
        if (poiList == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(poiList);
    }
}
