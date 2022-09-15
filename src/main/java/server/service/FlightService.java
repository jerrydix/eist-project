package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.Flight;
import server.model.flights.FlightFactory;
import server.model.flights.Location;
import server.model.flights.Suggestion;
import server.repository.FlightRepository;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private JourneyService journeyService;

    @Autowired
    private LocationService locationService;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

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
            list = FlightFactory.fetchFlightsFromToAt(from, to, date);
        }
        Location fromLoc = locationService.getLocationWithIATA(from);
        Location toLoc = locationService.getLocationWithIATA(to);

        for (Flight flight : list) {
            flight.setFullStartName(from);
            flight.setFullEndName(to);
            flight.setStartLocation(fromLoc);
            flight.setEndLocation(toLoc);
        }

        list = (List<Flight>) flightRepository.saveAll(list);

        return list;
    }

    public Flight getCurrentFlight() {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        return user.getCurrentFlight();
    }
}
