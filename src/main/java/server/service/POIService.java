package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.Location;
import server.model.flights.poi.PointOfInterest;

import java.util.ArrayList;
import java.util.List;

@Service
public class POIService {


    @Autowired
    private UserService userService;

    public List<PointOfInterest> unsaveFavourite(String id, int locationID) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        if (locationID == -1) {
            List<PointOfInterest> list = new ArrayList<>(user.getFavouritePOIs());

            for (PointOfInterest pointOfInterest : list) {
                if (pointOfInterest.getID().equals(id)) {
                    pointOfInterest.unFavourite();
                    user.removePOI(pointOfInterest);
                }
            }
            return user.getFavouritePOIs();
        }
        List<PointOfInterest> list = this.getPointsOfInterest(locationID);
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

    public List<PointOfInterest> saveFavourite(String id, int locationID) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        List<PointOfInterest> list = getPointsOfInterest(locationID);
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

    public List<PointOfInterest> getPointsOfInterest(int locationID) {
        User user = userService.getLoggedInUser();
        if (user == null || user.getCurrentFlight() == null) {
            return null;
        }
        Location location = Location.getLocationWithId(locationID);
        if (location == null) {
            return null;
        }
        List<PointOfInterest> list = location.getPointsOfInterest();
        List<PointOfInterest> list2 = user.getFavouritePOIs();
        List<PointOfInterest> newList = new ArrayList<>(list2);


        for (PointOfInterest pointOfInterest : list) {
            if (!list2.contains(pointOfInterest)) {
                newList.add(pointOfInterest);
            }
        }

        for (PointOfInterest pointOfInterest : list2) {
            newList.remove(pointOfInterest);
            newList.add(pointOfInterest);
        }

        return newList;
    }

    public List<PointOfInterest> getTopPointsOfInterest(int locationID) {
        Location location = Location.getLocationWithId(locationID);
        if (location == null) {
            return null;
        }
        return location.getPointsOfInterest().stream().limit(10).toList();
    }
}
