package server.service;

import org.springframework.stereotype.Service;
import server.model.surveys.Survey;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyService {
    private List<Survey> surveys;

    public SurveyService() {
        this.surveys = new ArrayList<Survey>();
    }

    private Survey getSurveyWithID(int surveyID) {
        for (Survey survey : surveys) {
            if (survey.getID() == surveyID) {
                return survey;
            }
        }
        return null;
    }


    public Survey saveSurvey(Survey survey) {
        surveys.add(survey);
        survey.surveyCompletion();
        return survey;
    }

    public Survey getSurvey(int surveyID) {
        return getSurveyWithID(surveyID);
    }
}
