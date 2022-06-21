package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.service.POIService;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class PoiRest {

    private POIService poiService;

    public PoiRest(POIService poiService) {
        this.poiService = poiService;
    }

    //TODO: probably integrate this into location rest or just fetch location with its POI list and display that
    @GetMapping("pois/{poiID}")
    public ResponseEntity<Object> getPointOfInterest(@PathVariable("poiID") Integer poiID, @RequestParam String getReq) {
        return switch (getReq) {
            case "POI" -> ResponseEntity.ok(poiService.getPointOfInterest(poiID));
            case "POI_Location" -> ResponseEntity.ok(poiService.getPointOfInterest(poiID).getLocation());
            default -> ResponseEntity.badRequest().build();
        };
    }

}
