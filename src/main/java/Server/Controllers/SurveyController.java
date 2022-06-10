package Server.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Server.Model.Surveys.Survey;

public class SurveyController {
    private List<Survey> surveys;

    public SurveyController(){
        this.surveys = new ArrayList<Survey>();
    }

    private Survey getSurveyWithID(UUID surveyID){
        for(Survey survey : surveys){
            if(survey.getID().equals(surveyID)){
                return survey;
            }
        }
        return null;
    }


    public Survey saveSurvey(Survey survey) {
        for(int i = 0; i < surveys.size(); i++) { //TODO: not necessary if we don't do PutMapping
            if(survey.getID().equals(surveys.get(i).getID())) {
                surveys.set(i, survey);
                return survey;
            }
        }
        surveys.add(survey);
        return survey;
    }

    public Survey getSurvey(UUID surveyID) {
        return getSurveyWithID(surveyID);
    }

    public void removeSurvey(UUID surveyID) {
        this.surveys.removeIf(survey -> survey.getID().equals(surveyID));
    }

     
 }
