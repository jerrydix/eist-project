package server.model;

import server.model.flights.FlightJourney;
import server.model.surveys.Reward;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static final List<User> systemUsers = new ArrayList<>();

    private static boolean loggedIn = false;
    private String username;
    private String password;

    private boolean authenticated = false;

    private List<Reward> rewards = new ArrayList<>();

    private List<FlightJourney> bookedFlightJourneys;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        systemUsers.add(this);
    }

    public static User getUser(String username) {
        for (User user : systemUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getSystemUsers() {
        return systemUsers;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean authenticateUser(String password) {
        if (this.password.equals(password)) {
            this.authenticated = true;
        }
        loggedIn = this.authenticated;
        return this.isAuthenticated();
    }

    public void logout() {
        this.authenticated = false;
        loggedIn = false;
    }

    public void reward() {
        rewards.add(new Reward());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}