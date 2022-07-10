package server.model;

import server.model.flights.Flight;
import server.model.flights.FlightJourney;
import server.model.surveys.Reward;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;

    private boolean authenticated = false;

    private List<Reward> rewards = new ArrayList<>();

    private Reward latestReward;
    private List<FlightJourney> bookedFlightJourneys = new ArrayList<>();

    private Flight currentFlight;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Checks if user typed in the correct password and authenticates him if it is correct
     *
     * @param password The password the user types in
     * @return A boolean which states whether the user is authenticated or not
     */
    public boolean authenticateUser(String password) {
        if (this.password.equals(password)) {
            this.authenticated = true;
        }
        return this.isAuthenticated();
    }

    public void logout() {
        this.authenticated = false;
    }

    /**
     * Adds a new reward to the user's rewards list
     */
    public void reward() {
        Reward reward = new Reward();
        this.latestReward = reward;
        rewards.add(reward);
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

    public Flight getCurrentFlight() {
        return currentFlight;
    }

    public void setCurrentFlight(Flight currentFlight) {
        this.currentFlight = currentFlight;
    }

    public void addJourney(FlightJourney flightJourney) {
        bookedFlightJourneys.add(flightJourney);
    }

    public Reward getLatestReward() {
        return latestReward;
    }

    public void setLatestReward(Reward latestReward) {
        this.latestReward = latestReward;
    }
}
