package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.model.flights.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, String> {
    Flight findByNumber(String flightNumber);
}