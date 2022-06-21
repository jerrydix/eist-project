package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.entertainment.catering.Catering;
import server.service.CateringService;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class CateringRest {

    private CateringService cateringService;

    public CateringRest(CateringService cateringService) {
        this.cateringService = cateringService;
    }

    @GetMapping("catering")
    public ResponseEntity<Catering> getCatering() {
        return ResponseEntity.ok(cateringService.getCatering());
    }
}