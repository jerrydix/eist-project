package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.model.flights.Flight;
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
    public ResponseEntity<String[]> getSuggestions(@RequestParam String city) {
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
}
