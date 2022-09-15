package server.model;

import server.model.flights.Flight;
import server.model.flights.FlightJourney;
import server.model.flights.poi.PointOfInterest;
import server.model.flights.surveys.Reward;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    private String username;

    private String password;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<PointOfInterest> favouritePOIList = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Reward> rewards = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    private Reward latestReward = null;

    private boolean completedSurvey = false;

    @OneToMany
    private List<FlightJourney> bookedFlightJourneys = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    private Flight currentFlight;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected User() {

    }

    /**
     * Checks if user typed in the correct password and authenticates him if it is correct
     *
     * @param password The password the user types in
     * @return A boolean which states whether the user is authenticated or not
     */
    public boolean authenticateUser(String password) {
        if (this.password.equals(password)) {
            this.setFavourites();
            return true;
        }
        return false;
    }

    private void setFavourites() {
        for (PointOfInterest pointOfInterest : favouritePOIList) {
            pointOfInterest.favourite();
        }
    }

    public void logout() {
        this.unsetFavourites();
    }

    private void unsetFavourites() {
        for (PointOfInterest pointOfInterest : favouritePOIList) {
            pointOfInterest.unFavourite();
        }
    }


    /**
     * Adds a new reward to the user's rewards list
     */
    public void reward() {
        Reward reward = new Reward();
        this.completedSurvey = true;
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

    public void addPOI(PointOfInterest poi) {
        if (!this.favouritePOIList.contains(poi)) {
            this.favouritePOIList.add(poi);
        }
    }

    public void removePOI(PointOfInterest poi) {
        this.favouritePOIList.remove(poi);
    }


    public List<PointOfInterest> getFavouritePOIList() {
        return favouritePOIList;
    }

    public void setFavouritePOIList(List<PointOfInterest> favouritePOIList) {
        this.favouritePOIList = favouritePOIList;
    }

    public boolean hasCompletedSurvey() {
        return completedSurvey;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public boolean isCompletedSurvey() {
        return completedSurvey;
    }

    public void setCompletedSurvey(boolean completedSurvey) {
        this.completedSurvey = completedSurvey;
    }

    public List<FlightJourney> getBookedFlightJourneys() {
        return bookedFlightJourneys;
    }

    public void setBookedFlightJourneys(List<FlightJourney> bookedFlightJourneys) {
        this.bookedFlightJourneys = bookedFlightJourneys;
    }

    public FlightJourney getLastBookedJourney() {
        return this.bookedFlightJourneys.get(bookedFlightJourneys.size() - 1);
    }
}
