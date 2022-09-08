package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.model.flights.FlightJourney;

@Repository
public interface JourneyRepository extends CrudRepository<FlightJourney, Long> {
}
