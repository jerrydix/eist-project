package server.model.flights.surveys;

import server.model.User;

public class Survey {

    private static int currentID = 0;
    private int surveyID;
    private User user;
    private String flightNumber;


    private int flightRating;
    private int cateringRating;
    private int entertainmentRating;
    private int comfortRating;
    private int serviceRating;

    private String comment;

    private Reward reward;

    public Survey(String flightNumber, int flightRating, int cateringRating, int entertainmentRating, int comfortRating,
                  int serviceRating, String comment, Reward reward) {
        this.reward = reward;
        this.surveyID = currentID++;
        this.flightNumber = flightNumber;
        this.cateringRating = cateringRating;
        this.entertainmentRating = entertainmentRating;
        this.comfortRating = comfortRating;
        this.flightRating = flightRating;
        this.serviceRating = serviceRating;
        this.comment = comment;
    }

    public int getID() {
        return this.surveyID;
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

    public String getFlightNumber() {
        return flightNumber;
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
