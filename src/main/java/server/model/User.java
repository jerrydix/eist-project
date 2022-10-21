package server.model;

import server.model.flights.Flight;
import server.model.flights.FlightJourney;
import server.model.flights.poi.PointOfInterest;
import server.model.flights.surveys.Reward;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Entity
public class User {
    @Id
    private String username;

    private String password;

    private String salt;

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

    private double money;

    private String email;


    public User(String username, String password, String email) {
        this.username = username;
        this.salt = getNewSalt();
        this.password = this.getEncryptedPassword(password, this.salt);
        this.money = 2000;
        this.email = email;
    }

    protected User() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void adjustMoney(double money) {
        this.money += money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Checks if user typed in the correct password and authenticates him if it is correct
     *
     * @param password The password the user types in
     * @return A boolean which states whether the user is authenticated or not
     */
    public boolean authenticateUser(String password) {
        String calculatedHash = this.getEncryptedPassword(password, salt);
        if (calculatedHash.equals(this.password)) {
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

    public void exchange(Reward reward) {
        this.money += reward.getValue();
        rewards.remove(reward);
        latestReward = rewards.size() > 0 ? rewards.get(rewards.size() - 1) : null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // https://www.quickprogrammingtips.com/java/how-to-securely-store-passwords-in-java.html

    // Get a encrypted password using PBKDF2 hash algorithm
    public String getEncryptedPassword(String password, String salt) {
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160; // for SHA1
        int iterations = 20000; // NIST specifies 10000

        byte[] saltBytes = Base64.getDecoder().decode(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] encBytes = new byte[0];
        try {
            encBytes = f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(encBytes);
    }

    // Returns base64 encoded salt
    public String getNewSalt() {
        // Don't use Random!
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        // NIST recommends minimum 4 bytes. We use 8.
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
