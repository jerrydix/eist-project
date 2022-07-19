package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.flights.Flight;
import server.model.flights.FlightJourney;
import server.model.flights.Suggestion;
import server.service.FlightService;

import java.util.List;


@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class FlightRest {
    private FlightService flightService;

    public FlightRest(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("api/citySuggestion")
    public ResponseEntity<Suggestion[]> getSuggestions(@RequestParam String city) {
        if (city == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(flightService.getSuggestions(city));
    }

    @PostMapping("api/flights")
    public ResponseEntity<List<Flight>> getFlights(@RequestParam String from, @RequestParam String to, @RequestParam String date) {
        if (from == null || to == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(flightService.getFlights(from, to, date));
    }

    @PostMapping("api/journey")
    public ResponseEntity<FlightJourney> constructJourney(@RequestBody List<Flight> flights) {

        FlightJourney journey = flightService.constructJourney(flights);
        if (journey == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(journey);

    }

    @PostMapping("api/currentFlight")
    public ResponseEntity<Flight> getCurrentFlight() {
        Flight current = flightService.getCurrentFlight();
        if (current == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(current);
    }
}
