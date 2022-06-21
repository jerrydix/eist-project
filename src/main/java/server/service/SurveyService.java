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
        for (int i = 0; i < surveys.size(); i++) { //TODO: not necessary if we don't do PutMapping
            if (survey.getID() == (surveys.get(i).getID())) {
                surveys.set(i, survey);
                return survey;
            }
        }
        surveys.add(survey);
        return survey;
    }

    public Survey getSurvey(int surveyID) {
        return getSurveyWithID(surveyID);
    }
}
