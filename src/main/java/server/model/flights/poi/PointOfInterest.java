package server.model.flights.poi;

import server.networking.HTTP_GetRequest;
import server.parsing.PointOfInterestParser;
import server.utility.KeyReader;

import java.util.ArrayList;
import java.util.List;

public class PointOfInterest {

    private String id;
    private String title;
    private String address;
    private String pointOfInterestType;
    private String description;
    private String label;
    private double rating;
    private Position position;
    private int favourited;
    private String formattedType;

    public PointOfInterest(String id, String title, String address, String pointOfInterestType,
                           double rating, double longitude, double latitude) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.pointOfInterestType = pointOfInterestType;
        this.label = "T";
        this.rating = rating;
        this.favourited = 0;
        this.position = new Position(latitude, longitude);
        this.description = toString();
        this.formattedType = this.typeToString(pointOfInterestType);
    }

    /**
     * A wrapper method for fetching POIs for a specific location.
     *
     * @param longitude The longitude of the location whose POIs are fetched
     * @param latitude  The latitude of the location whose POIs are fetched
     * @return A list of POIs of "location"
     */

    public static List<PointOfInterest> fetchPOIs(double longitude, double latitude) {
        var completeList = new ArrayList<PointOfInterest>();
        var pagetoken1 = PointOfInterestParser.parsePOIJson(
                HTTP_GetRequest.httpRequest("https://maps.googleapis.com/maps/api/place/nearbysearch/json",
                        new String[]{"?location=" + longitude + "%2C" + latitude, "&radius=100000",
                                "&rankby=prominence", "&type=tourist_attraction", "&pagetoken",
                                "&key=" + KeyReader.getAPIkey()}), completeList);
        try {
            Thread.sleep(1750L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var pagetoken2 = PointOfInterestParser.parsePOIJson(
                HTTP_GetRequest.httpRequest("https://maps.googleapis.com/maps/api/place/nearbysearch/json",
                        new String[]{"?pagetoken=" + pagetoken1,
                                "&key=" + KeyReader.getAPIkey()}), completeList);
        /*try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var dada = PointOfInterestParser.parsePOIJson(
                HTTP_GetRequest.httpRequest("https://maps.googleapis.com/maps/api/place/nearbysearch/json",
                        new String[]{"?pagetoken=" + pagetoken2,
                                "&key=" + KeyReader.getAPIkey()}), completeList);
        */
        return completeList;
    }

    public void favourite() {
        this.favourited = 1;
        this.label = "F";
    }


    public void unFavourite() {
        this.favourited = 0;
        this.label = "T";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getID() {
        return id;
    }

    public String getPointOfInterestType() {
        return pointOfInterestType;
    }

    public void setPointOfInterestType(String pointOfInterestType) {
        this.pointOfInterestType = pointOfInterestType;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavourited() {
        return favourited;
    }

    public void setFavourited(int favourited) {
        this.favourited = favourited;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFormattedType() {
        return formattedType;
    }

    public void setFormattedType(String formattedType) {
        this.formattedType = formattedType;
    }

    private String typeToString(String type) {
        int wordSep = type.indexOf('_');
        String ans = "";
        ans += type.charAt(0);
        ans = ans.toUpperCase();
        if (wordSep == -1) {
            return ans + type.substring(1);
        }
        ans += type.substring(1, wordSep);
        String newAns = " ";
        newAns += type.charAt(wordSep + 1);
        newAns = newAns.toUpperCase();
        return ans + newAns + type.substring(wordSep + 2);
    }

    @Override
    public String toString() {
        return "Address: " + address + " | " + typeToString(pointOfInterestType)
                + " | Rating: " + rating;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PointOfInterest other)) {
            return false;
        }
        return this.id.equals(other.id);
    }
}
