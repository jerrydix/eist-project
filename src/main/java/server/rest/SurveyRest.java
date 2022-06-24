package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.User;
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
        User current = User.getUser(username);
        if (current == null || !current.isAuthenticated()) {
            return ResponseEntity.badRequest().build();
        }
        survey.setUser(current);
        return ResponseEntity.ok(surveyService.saveSurvey(survey));
    }
}
