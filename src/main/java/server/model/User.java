package server.model;

import server.model.flights.FlightJourney;
import server.model.surveys.Reward;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;

    private boolean authenticated = false;

    private List<Reward> rewards = new ArrayList<>();

    private List<FlightJourney> bookedFlightJourneys;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public boolean authenticateUser(String password) {
        if (this.password.equals(password)) {
            this.authenticated = true;
        }
        return this.isAuthenticated();
    }

    public void logout() {
        this.authenticated = false;
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
