package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.model.flights.surveys.Survey;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Survey findSurveyBySurveyId(Long surveyId);
}
