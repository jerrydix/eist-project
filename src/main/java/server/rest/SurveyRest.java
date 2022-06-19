package server.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import server.service.SurveyService;
import server.model.surveys.Survey;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class SurveyRest {

    private SurveyService surveyService;

    public SurveyRest(SurveyService surveyService){
        this.surveyService = surveyService;
    }


    @PostMapping("surveys")
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey){
        if (survey.getID() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(surveyService.saveSurvey(survey));
    }

    @PutMapping("surveys/{surveyID}")
    public ResponseEntity<Survey> updateSurvey(@RequestBody Survey newSurvey, @PathVariable("surveyID") UUID surveyID){
        if(!newSurvey.getID().equals(surveyID)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(surveyService.saveSurvey(newSurvey));
    } //TODO: do we need a put option for survey -> customre usually would not be able to update the survey once finished

    @GetMapping("surveys/{surveyID}")
    public ResponseEntity<Survey> getSurvey(@PathVariable("surveyID") UUID surveyID){
        return ResponseEntity.ok(surveyService.getSurvey(surveyID));
    }

    @DeleteMapping("surveys/{surveyID}") //TODO: same with this, is customer able to delete the survey?
    public ResponseEntity<Void> deleteSurvey(@PathVariable("surveyID") UUID surveyID){
        surveyService.removeSurvey(surveyID);
        return ResponseEntity.noContent().build();
    }


}
