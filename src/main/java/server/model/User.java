package server.model;

import server.model.flights.FlightJourney;

import java.util.List;

public class User {
    private String username;
    private String password;

    private boolean authenticated;

    private List<FlightJourney> bookedFlightJourneys;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.authenticated = false;
    }

    public boolean authenticateUser(String password) {
        if (this.password.equals(password)) {
            this.authenticated = true;
        }
        return this.authenticated;
    }

    public void logout() {
        this.authenticated = false;
    }
}
