package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.flights.Flight;
import server.model.flights.FlightJourney;
import server.service.JourneyService;

import java.util.List;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})

public class JourneyRest {
    private final JourneyService journeyService;

    public JourneyRest(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @PostMapping("api/journey")
    public ResponseEntity<FlightJourney> constructJourney(@RequestBody List<Flight> flights) {

        FlightJourney journey = journeyService.constructJourney(flights);
        if (journey == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(journey);

    }

}
