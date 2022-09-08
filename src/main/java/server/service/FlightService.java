package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.Flight;
import server.model.flights.FlightFactory;
import server.model.flights.FlightJourney;
import server.model.flights.Suggestion;
import server.repository.FlightRepository;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private UserService userService;

    @Autowired
    private FlightRepository flightRepository;

    public Suggestion[] getSuggestions(String city) {

        String[] sug = Flight.getSuggestions(city);
        Suggestion[] suggestions = new Suggestion[sug.length];
        for (int i = 0; i < suggestions.length; i++) {
            suggestions[i] = new Suggestion(sug[i]);
        }
        return suggestions;
    }

    public List<Flight> getFlights(String from, String to, String date) {
        List<Flight> list = flightRepository.findByStartNameAndEndNameAndDepartureDate(from, to, date);
        if (list.isEmpty()) {
            list = (List<Flight>) flightRepository.saveAll(FlightFactory.fetchFlightsFromToAt(from, to, date));
        }
        return list;
    }

    public FlightJourney constructJourney(List<Flight> flights) {
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

    private FlightJourney buildJourney(List<Flight> flights) {
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
