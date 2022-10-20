package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.Flight;
import server.model.flights.FlightJourney;
import server.repository.JourneyRepository;
import server.utility.EmailPurpose;

import java.util.List;

@Service
public class JourneyService {

    private final JourneyRepository journeyRepository;

    @Autowired
    private UserService userService;

    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public FlightJourney save(FlightJourney flightJourney) {
        return this.journeyRepository.save(flightJourney);
    }

    public FlightJourney constructJourney(List<Flight> flights) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        FlightJourney journey = this.buildJourney(flights);
        if (journey != null) {
            journey = this.save(journey);
            user.addJourney(journey);
            userService.save(user);
            userService.sendEmail(userService.getLoggedInUser(), EmailPurpose.BOOKING, journey.toEmailContent());
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
}
