package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.surveys.Survey;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyService {
    private List<Survey> surveys;

    @Autowired
    private UserService userService;

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
        User current = userService.getLoggedInUser();
        if (current == null) {
            return null;
        }
        surveys.add(survey);
        survey.setUser(current);
        current.reward();
        survey.setReward(current.getLatestReward());
        return survey;
    }

    public Survey getSurvey(int surveyID) {
        return getSurveyWithID(surveyID);
    }
}
