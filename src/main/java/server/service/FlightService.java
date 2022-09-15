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
import java.util.Optional;

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

    public Suggestion[] getSuggestions(String city) {

        String[] sug = Flight.getSuggestions(city);
        Suggestion[] suggestions = new Suggestion[sug.length];
        for (int i = 0; i < suggestions.length; i++) {
            suggestions[i] = new Suggestion(sug[i]);
        }
        return suggestions;
    }

    public Location getLocation(String locationNameWithIATA) {
        String locationName = FlightFactory.getName(locationNameWithIATA);
        Optional<Location> optLoc = locationService.getLocationWithName(locationName);
        Location location;
        if (optLoc.isEmpty()) {
            location = locationService.saveLocation(FlightFactory.getLocation(locationNameWithIATA));
        } else {
            location = optLoc.get();
        }

        return location;
    }

    public List<Flight> getFlights(String from, String to, String date) {
        List<Flight> list = flightRepository.findByStartNameAndEndNameAndDepartureDate(from, to, date);
        if (list.isEmpty()) {
            list = FlightFactory.fetchFlightsFromToAt(from, to, date);
        }
        Location fromLoc = this.getLocation(from);
        Location toLoc = this.getLocation(to);

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
