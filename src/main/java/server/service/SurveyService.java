package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.surveys.Survey;
import server.repository.SurveyRepository;

import java.util.Optional;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    @Autowired
    private UserService userService;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey getSurveyWithId(long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        return survey.orElse(null);
    }

    public Survey saveSurvey(Survey survey) {
        User current = userService.getLoggedInUser();
        if (current == null) {
            return null;
        }
        survey.setUser(current);
        current.reward();
        current = userService.save(current);
        survey.setReward(current.getLatestReward());
        return surveyRepository.save(survey);
    }
}
