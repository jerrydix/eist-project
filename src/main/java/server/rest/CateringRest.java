package server.rest;

import server.service.CateringService;
import server.model.entertainment.catering.Catering;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class CateringRest {

    private CateringService cateringService;

    public CateringRest(CateringService cateringService) {
        this.cateringService = cateringService;
    }

    @PutMapping("catering/{cateringID}")
    public ResponseEntity<Catering> updateCatering(@RequestBody Catering newCatering, @PathVariable Integer cateringID) {
        if (newCatering.getId() != cateringID) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cateringService.saveCatering(newCatering));
    }

    @GetMapping("catering")
    public ResponseEntity<Catering> getCatering() {
        return ResponseEntity.ok(cateringService.getCatering());
    }
}