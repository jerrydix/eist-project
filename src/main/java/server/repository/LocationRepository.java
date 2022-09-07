package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.model.flights.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    Location findByLocationId(Long id);
}
