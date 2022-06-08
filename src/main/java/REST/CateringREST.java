package REST;

import Server.Controllers.CateringController;
import Server.Model.Entertainment.Catering.Catering;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class CateringREST {

    private CateringController cateringController;

    public CateringREST(CateringController cateringController) {
        this.cateringController = cateringController;
    }

    @PutMapping("catering/{cateringID}")
    public ResponseEntity<Catering> updateCatering(@RequestBody Catering newCatering, @PathVariable Integer cateringID) {
        if (newCatering.getId() != cateringID) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cateringController.saveCatering(newCatering));
    }

    @GetMapping("catering")
    public ResponseEntity<Catering> getCatering() {
        return ResponseEntity.ok(cateringController.getCatering());
    }
}
