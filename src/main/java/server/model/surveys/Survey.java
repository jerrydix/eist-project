package server.model.surveys;

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


    public Survey(User user, String flightNumber, int flightRating, int cateringRating, int entertainmentRating, int comfortRating,
                  int serviceRating, String comment) {
        this.surveyID = currentID++;
        this.user = user;
        this.flightNumber = flightNumber;
        this.cateringRating = cateringRating;
        this.entertainmentRating = entertainmentRating;
        this.comfortRating = comfortRating;
        this.flightRating = flightRating;
        this.serviceRating = serviceRating;
        this.comment = comment;
        this.reward = new Reward();
    }

    public void surveyCompletion() {
        user.reward();
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
}
