package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.surveys.Survey;
import server.service.SurveyService;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class SurveyRest {

    private SurveyService surveyService;

    public SurveyRest(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("surveys")
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) {
        if (survey.getUser() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(surveyService.saveSurvey(survey));
    }
}
