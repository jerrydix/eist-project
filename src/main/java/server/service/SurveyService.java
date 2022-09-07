package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.surveys.Survey;
import server.repository.SurveyRepository;

@Service
public class SurveyService {

    @Autowired
    private UserService userService;

    @Autowired
    private SurveyRepository surveyRepository;

    public Survey getSurveyWithId(long surveyId) {
        return surveyRepository.findSurveyBySurveyId(surveyId);
    }

    public Survey saveSurvey(Survey survey) {
        User current = userService.getLoggedInUser();
        if (current == null) {
            return null;
        }
        survey.setUser(current);
        current.reward();
        survey.setReward(current.getLatestReward());
        return surveyRepository.save(survey);
    }
}
