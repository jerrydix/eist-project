package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.FlightFactory;
import server.model.flights.FlightJourney;
import server.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private boolean loggedIn;

    private User loggedInUser;

    public UserService() {
        loggedIn = false;
        loggedInUser = null;
    }

    public boolean registerUser(String username, String password, String flightNumber) {
        if (password == null || password.isBlank() || username == null || username.isBlank() || flightNumber == null || flightNumber.isBlank()) {
            return false;
        }
        User user = getUser(username);
        if (user != null) {
            return false;
        }
        user = new User(username, password);

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
        if (user == null || user.isAuthenticated()) {
            return false;
        }
        loggedIn = user.authenticateUser(password);
        if (loggedIn) {
            loggedInUser = user;
        }

        return loggedIn;
    }

    public boolean logout() {
        if (!loggedIn) {
            return false;
        }
        User user = loggedInUser;
        if (user == null) {
            return false;
        }
        user.logout();
        loggedIn = false;
        loggedInUser = null;
        return true;
    }


    public User getUserData(String username) {
        if (!loggedIn) {
            return null;
        }
        User user = getUser(username);
        if (user == null || !user.isAuthenticated()) {
            return null;
        }
        return user;
    }


    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean completedSurvey() {
        User user = getLoggedInUser();
        if (user == null) {
            return false;
        }
        return user.hasCompletedSurvey();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
