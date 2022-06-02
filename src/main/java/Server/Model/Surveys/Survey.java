package Server.Model.Surveys;

import java.util.UUID;

public class Survey {
    private UUID surveyID;

    private UUID passengerID; //TODO: do we want to save this?
    private UUID flightNumber;


    private int flightRating;
    private int cateringRating;
    private int entertainmentRating;
    private int comfortRating;
    private int serviceRating;

    private String comment;

    private Reward reward;


    public Survey(UUID surveyID, UUID passengerID, UUID flightNumber, int flightRating, int cateringRating, int entertainmentRating, int comfortRating,
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
        this.reward = generateReward();
    }

    //TODO: generateReward-Funktion oder reward als Argument mitgeben
    private Reward generateReward() {
        return null;
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

    public UUID getFlightNumber() {
        return flightNumber;
    }

    public UUID getPassengerID() {
        return passengerID;
    }

    public UUID getSurveyID() {
        return surveyID;
    }
}
