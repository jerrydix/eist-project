package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.surveys.Survey;

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


    public Survey saveSurvey(String username, Survey survey) {
        User current = userService.getUser(username);
        if (current == null || !current.isAuthenticated()) {
            return null;
        }
        surveys.add(survey);
        survey.setUser(current);
        current.reward();
        return survey;
    }

    public Survey getSurvey(int surveyID) {
        return getSurveyWithID(surveyID);
    }
}
