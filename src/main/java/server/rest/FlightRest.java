package server.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.model.flights.Flight;
import server.service.FlightService;


@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class FlightRest {
    private FlightService flightService;

    public FlightRest(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("flights")
    public Flight getFlight(@RequestParam String flightNumber) {
        return flightService.getFlight(flightNumber);
    }
}
