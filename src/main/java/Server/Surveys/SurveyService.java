package Server.Surveys;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SurveyService {
    private List<Survey> surveys;

    public SurveyService(){
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
        //TODO: Michael
        //used in updating an existing and adding new surveys
        return null;
    }

    public Survey getSurvey(UUID surveyID) {
        return getSurveyWithID(surveyID);
    }

    public void removeSurvey(UUID surveyID) {
        this.surveys.removeIf(survey -> survey.getID().equals(surveyID));
    }

     
 }
