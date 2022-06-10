package Server.Model.Surveys;

import java.util.UUID;

public class Survey {
    private UUID surveyID;
    private int passengerID;
    private int flightNumber;


    private int flightRating;
    private int cateringRating;
    private int entertainmentRating;
    private int comfortRating;
    private int serviceRating;

    private String comment;

    private Reward reward;


    public Survey(UUID surveyID, int passengerID, int flightNumber, int flightRating, int cateringRating, int entertainmentRating, int comfortRating,
    int serviceRating, String comment) {
        this.surveyID = surveyID;
        this.passengerID = passengerID;
        this.flightNumber = flightNumber;
        this.cateringRating = cateringRating;
        this.entertainmentRating = entertainmentRating;
        this.comfortRating = comfortRating;
        this.flightRating = flightRating;
        this.serviceRating = serviceRating;
        this.comment = comment;
        this.reward = new Reward();
    }


    public UUID getID(){
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

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getPassengerID() {
        return passengerID;
    }

}
