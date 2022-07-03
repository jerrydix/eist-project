package server.service;

import org.springframework.stereotype.Service;
import server.model.flights.Flight;
import server.model.flights.FlightJourney;

import java.util.Arrays;
import java.util.List;

@Service
public class FlightService {

    public String[] getSuggestions(String city) {
        return Flight.getSuggestions(city);
    }

    public List<Flight> getFlights(String from, String to, String date) {
        return Flight.fetchFlightsFromToAt(from, to, date);
    }

    public FlightJourney constructJourney(Flight[] flights, String username, UserService userService) {
        if (!userService.getLoggedInUser().getUsername().equals(username)) {
            return null;
        }
        FlightJourney journey = this.buildJourney(flights);
        if (journey != null) {
            userService.getUser(username).addJourney(journey);
        }
        return journey;
    }

    private FlightJourney buildJourney(Flight[] arr) {
        List<Flight> flights = Arrays.asList(arr);
        if (flights.isEmpty()) {
            return null;
        }
        FlightJourney journey = new FlightJourney();
        return journey.buildJourney(flights) ? journey : null;
    }
}
