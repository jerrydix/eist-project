package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.surveys.Survey;
import server.service.SurveyService;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class SurveyRest {

    private SurveyService surveyService;

    public SurveyRest(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("api/surveys")
    public ResponseEntity<Survey> createSurvey(@RequestParam(name = "username") String username, @RequestBody Survey survey) {
        Survey newSurvey = surveyService.saveSurvey(username, survey);
        if (newSurvey == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newSurvey);
    }
}
