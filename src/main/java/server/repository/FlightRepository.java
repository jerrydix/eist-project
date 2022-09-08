package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.model.flights.Flight;

import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, String> {
    Flight findByNumber(String flightNumber);

    List<Flight> findByStartNameAndEndNameAndDepartureDate(String st, String end, String date);
}