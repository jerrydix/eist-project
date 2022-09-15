package server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.model.flights.poi.PointOfInterest;

@Repository
public interface POIRepository extends CrudRepository<PointOfInterest, String> {
    
}
