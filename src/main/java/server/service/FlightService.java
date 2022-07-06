package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.Flight;
import server.model.flights.FlightJourney;

import java.util.Arrays;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private UserService userService;

    public String[] getSuggestions(String city) {
        return Flight.getSuggestions(city);
    }

    public List<Flight> getFlights(String from, String to, String date) {
        return Flight.fetchFlightsFromToAt(from, to, date);
    }

    public FlightJourney constructJourney(Flight[] flights, UserService userService) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        FlightJourney journey = this.buildJourney(flights);
        if (journey != null) {
            user.addJourney(journey);
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

    public Flight getCurrentFlight() {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        return user.getCurrentFlight();
    }
}
