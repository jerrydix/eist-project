package server.service;

import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.FlightFactory;
import server.model.flights.FlightJourney;
import server.model.flights.poi.PointOfInterest;
import server.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private boolean loggedIn;

    private String loggedInUser;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        loggedIn = false;
        loggedInUser = null;
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public List<PointOfInterest> getFavouritePOIList(User user) {
        Optional<User> userOpt = userRepository.findById(user.getUsername());
        return userOpt.map(User::getFavouritePOIList).orElse(null);
    }

    public boolean registerUser(String username, String password, String flightNumber) {
        if (password == null || password.isBlank() || username == null || username.isBlank() || flightNumber == null || flightNumber.isBlank()) {
            return false;
        }
        if (userRepository.findById(username).isPresent()) {
            return false;
        }
        User user = new User(username, password);

        FlightJourney journey = FlightFactory.generateRandomJourney(flightNumber);
        user.setCurrentFlight(journey.getFlights().get(0));
        user.addJourney(journey);


        userRepository.save(user);

        return true;
    }

    public boolean authenticateUser(String username, String password) {
        if (loggedIn) {
            return false;
        }
        User user = getUser(username);
        if (user == null) {
            return false;
        }
        loggedIn = user.authenticateUser(password);
        if (loggedIn) {
            loggedInUser = user.getUsername();
        }
        userRepository.save(user);

        return loggedIn;
    }

    public boolean logout() {
        if (!loggedIn) {
            return false;
        }
        User user = getLoggedInUser();
        if (user == null) {
            return false;
        }
        user.logout();
        loggedIn = false;
        loggedInUser = null;
        userRepository.save(user);
        return true;
    }

    public User getUser(String username) {
        if (username == null) {
            return null;
        }
        Optional<User> user = userRepository.findById(username);
        return user.orElse(null);
    }

    public boolean completedSurvey() {
        User user = getLoggedInUser();
        if (user == null) {
            return false;
        }
        return user.hasCompletedSurvey();
    }

    public User getLoggedInUser() {
        return getUser(loggedInUser);
    }
}
