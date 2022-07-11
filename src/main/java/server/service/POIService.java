package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.Location;
import server.model.flights.poi.PointOfInterest;

import java.util.List;

@Service
public class POIService {


    @Autowired
    private UserService userService;

    public PointOfInterest saveFavourite(PointOfInterest pointOfInterest) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        PointOfInterest poi = Location.getPOIWithId(pointOfInterest.getID());
        if (poi == null) {
            return null;
        }
        user.addPOI(pointOfInterest);

        return poi;
    }

    public List<PointOfInterest> getFavourites() {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        return user.getFavouritePOIs();
    }
}
