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

    private POIService poiService;


    public PoiRest(POIService poiService) {
        this.poiService = poiService;
    }

    @PostMapping("api/favourite")
    public ResponseEntity<String> addToFavourites(@RequestBody PointOfInterest pointOfInterest) {
        PointOfInterest poi = poiService.saveFavourite(pointOfInterest);
        if (poi == null) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
        return ResponseEntity.ok("Added point of interest to favourites");
    }

    @GetMapping("api/favourites")
    public ResponseEntity<List<PointOfInterest>> getFavourites() {
        List<PointOfInterest> poiList = poiService.getFavourites();
        if (poiList == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(poiList);
    }


    @GetMapping("api/destinationPOI")
    public ResponseEntity<List<PointOfInterest>> getDestinationPOI() {
        List<PointOfInterest> poiList = poiService.getDestionationPOIs();
        if (poiList == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(poiList);
    }
}
