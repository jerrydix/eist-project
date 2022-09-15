package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.model.flights.Location;
import server.model.flights.poi.PointOfInterest;
import server.repository.POIRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class POIService {


    private final POIRepository poiRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;

    public POIService(POIRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    public PointOfInterest getFavouritePOI(User user, String id) {
        List<PointOfInterest> pois = userService.getFavouritePOIList(user);
        for (PointOfInterest pointOfInterest : pois) {
            if (pointOfInterest.getID().equals(id)) {
                return pointOfInterest;
            }
        }
        return null;
    }


    public List<PointOfInterest> unsaveFavourite(String id, int locationId) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        if (locationId == -1) {
            PointOfInterest pointOfInterest = this.getFavouritePOI(user, id);

            if (pointOfInterest != null) {
                pointOfInterest.unFavourite();
                user.removePOI(pointOfInterest);
                poiRepository.save(pointOfInterest);
            }

            return userService.save(user).getFavouritePOIList();
        }
        List<PointOfInterest> list = this.getPointsOfInterest(locationId);
        if (list == null) {
            return null;
        }
        for (PointOfInterest pointOfInterest : list) {
            if (pointOfInterest.getID().equals(id)) {
                pointOfInterest.unFavourite();
                poiRepository.save(pointOfInterest);
                user.removePOI(pointOfInterest);
            }
        }
        userService.save(user);
        return list;
    }

    public List<PointOfInterest> saveFavourite(String id, int locationId) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return null;
        }
        List<PointOfInterest> list = getPointsOfInterest(locationId);
        if (list == null) {
            return null;
        }
        for (PointOfInterest pointOfInterest : list) {
            if (pointOfInterest.getID().equals(id)) {
                pointOfInterest.favourite();
                poiRepository.save(pointOfInterest);
                user.addPOI(pointOfInterest);
            }
        }
        userService.save(user);
        return list;
    }

    public List<PointOfInterest> getPointsOfInterest(int locationId) {
        User user = userService.getLoggedInUser();
        if (user == null || user.getCurrentFlight() == null) {
            return null;
        }
        Location location = locationService.getLocationWithId(locationId);
        if (location == null) {
            return null;
        }
        List<PointOfInterest> list = location.getPointsOfInterest();
        List<PointOfInterest> list2 = userService.getFavouritePOIList(user);
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

    public List<PointOfInterest> getTopPointsOfInterest(int locationId) {
        Location location = locationService.getLocationWithId(locationId);
        if (location == null) {
            return null;
        }
        return location.getPointsOfInterest().stream().limit(10).toList();
    }
}
