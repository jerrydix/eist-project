package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.model.flights.Location;

import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    public Optional<Location> findByName(String name);
}
