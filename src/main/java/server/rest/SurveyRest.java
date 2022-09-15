package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.flights.surveys.Survey;
import server.service.SurveyService;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class SurveyRest {

    private final SurveyService surveyService;

    public SurveyRest(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("api/surveys")
    public ResponseEntity<String> createSurvey(@RequestBody Survey survey) {
        Survey newSurvey = surveyService.saveSurvey(survey);
        if (newSurvey == null) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
        return ResponseEntity.ok("Congrats, your reward is: " + survey.getReward().toString());
    }
}
