package server.service;

import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.Flight;
import server.model.flights.FlightFactory;
import server.model.flights.FlightJourney;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> systemUsers;

    private boolean loggedIn;

    private User loggedInUser;

    public UserService() {
        this.systemUsers = new ArrayList<>();
        loggedIn = false;
        loggedInUser = null;
    }

    public boolean registerUser(String username, String password, String flightNumber) {
        User user = getUser(username);
        if (user != null || password == null || password.isBlank()) {
            return false;
        }
        user = new User(username, password);

        FlightJourney journey = FlightFactory.generateRandomJourney(flightNumber);
        user.setCurrentFlight(journey.getFlights().get(0));
        user.addJourney(journey);

        systemUsers.add(user);
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

    public boolean logout(String username) {
        if (!loggedIn) {
            return false;
        }
        User user = getUser(username);
        if (user == null || !user.isAuthenticated()) {
            return false;
        }
        user.logout();
        loggedIn = false;
        loggedInUser = null;
        return true;
    }

    public Flight getCurrentFlight(String username) {
        User user = getUser(username);
        if (user == null || loggedInUser != user) {
            return null;
        }
        return user.getCurrentFlight();
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
        for (User user : systemUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getSystemUsers() {
        return systemUsers;
    }

    public void printUsers() {
        for (User user : systemUsers) {
            System.out.println(user.getUsername());
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
