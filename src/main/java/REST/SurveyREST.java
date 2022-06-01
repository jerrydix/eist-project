package REST;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import Server.Controllers.SurveyController;
import Server.Model.Surveys.Survey;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class SurveyREST {

    private SurveyController surveyController;

    public SurveyREST(SurveyController surveyController){
        this.surveyController = surveyController;
    }


    @PostMapping("surveys")
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey){
        if (survey.getID() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(surveyController.saveSurvey(survey));
    }

    @PutMapping("surveys/{surveyID}")
    public ResponseEntity<Survey> updateSurvey(@RequestBody Survey newSurvey, @PathVariable("surveyID") UUID surveyID){
        if(!newSurvey.getID().equals(surveyID)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(surveyController.saveSurvey(newSurvey));
    }

    @GetMapping("surveys/{surveyID}")
    public ResponseEntity<Survey> getSurvey(@PathVariable("surveyID") UUID surveyID){
        return ResponseEntity.ok(surveyController.getSurvey(surveyID));
    }

    @DeleteMapping("surveys/{surveyID}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable("surveyID") UUID surveyID){
        surveyController.removeSurvey(surveyID);
        return ResponseEntity.noContent().build();
    }


}
