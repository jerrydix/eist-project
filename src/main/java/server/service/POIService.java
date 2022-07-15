package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.poi.PointOfInterest;

import java.util.List;

@Service
public class POIService {


    @Autowired
    private UserService userService;

    public List<PointOfInterest> unsaveFavourite(String id) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        List<PointOfInterest> list = getPointsOfInterest();
        if (list == null) {
            return null;
        }
        for (PointOfInterest pointOfInterest : list) {
            if (pointOfInterest.getID().equals(id)) {
                pointOfInterest.unFavourite();
                user.removePOI(pointOfInterest);
            }
        }
        return list;
    }

    public List<PointOfInterest> saveFavourite(String id) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        List<PointOfInterest> list = getPointsOfInterest();
        if (list == null) {
            return null;
        }
        for (PointOfInterest pointOfInterest : list) {
            if (pointOfInterest.getID().equals(id)) {
                pointOfInterest.favourite();
                user.addPOI(pointOfInterest);
            }
        }
        return list;
    }

    public List<PointOfInterest> getPointsOfInterest() {
        User user = userService.getLoggedInUser();
        if (user == null || user.getCurrentFlight() == null) {
            return null;
        }
        List<PointOfInterest> list = user.getCurrentFlight().getEndLocation().getPoiList();
        List<PointOfInterest> list2 = user.getFavouritePOIs();

        for (int i = 0; i < list.size(); i++) {
            if (!list2.contains(list.get(i))) {
                list2.add(list.get(i));
            }
        }

        return list;
    }
}
