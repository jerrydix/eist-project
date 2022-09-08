package server.model.flights.surveys;

import server.model.User;
import server.model.flights.Flight;

import javax.persistence.*;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long surveyId;

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    @OneToOne(cascade = {CascadeType.ALL})
    private Flight flight;

    private int flightRating;
    private int cateringRating;
    private int entertainmentRating;
    private int comfortRating;
    private int serviceRating;

    private String comment;

    @OneToOne(cascade = {CascadeType.ALL})
    private Reward reward;

    public Survey(Flight flight, int flightRating, int cateringRating, int entertainmentRating, int comfortRating,
                  int serviceRating, String comment, Reward reward) {
        this.reward = reward;
        this.flight = flight;
        this.cateringRating = cateringRating;
        this.entertainmentRating = entertainmentRating;
        this.comfortRating = comfortRating;
        this.flightRating = flightRating;
        this.serviceRating = serviceRating;
        this.comment = comment;
    }

    protected Survey() {

    }

    public long getId() {
        return this.surveyId;
    }

    public int getCateringRating() {
        return cateringRating;
    }

    public int getComfortRating() {
        return comfortRating;
    }

    public int getEntertainmentRating() {
        return entertainmentRating;
    }

    public String getComment() {
        return comment;
    }

    public Flight getFlight() {
        return flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFlightRating() {
        return flightRating;
    }

    public int getServiceRating() {
        return serviceRating;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }
}
