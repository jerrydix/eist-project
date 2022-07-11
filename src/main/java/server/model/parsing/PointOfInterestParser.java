package server.model.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import server.model.flights.poi.PointOfInterest;

import java.util.ArrayList;
import java.util.List;

public class PointOfInterestParser {

    /**
     * parsePOIJson parses the api response of the Google Maps PLaces API and returns a list of POI objects (top 50 POIs)
     *
     * @param jsonText The get request api response of the Google Maps Places API, formatted in json
     * @return A list of the top 50 POIs sorted by relevance at the given location
     */
    public static List<PointOfInterest> parsePOIJson(String jsonText) {
        try {
            JSONObject jsonObject = new JSONObject(jsonText.toString());
            JSONArray resultsArray = jsonObject.getJSONArray("results");
            List<PointOfInterest> poiList = new ArrayList<>();

            for (int i = 0; i < resultsArray.length() && i < 50; i++) {
                String id = resultsArray.getJSONObject(i).getString("place_id");
                String name = resultsArray.getJSONObject(i).getString("name");
                String address = resultsArray.getJSONObject(i).getString("vicinity");
                String type = resultsArray.getJSONObject(i).getJSONArray("types").getString(0);
                if (type.equals("lodging")) {
                    continue;
                }
                double rating = -1;
                if (!resultsArray.getJSONObject(i).isNull("rating")) {
                    rating = resultsArray.getJSONObject(i).getDouble("rating");
                }
                double latitude = resultsArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                double longitude = resultsArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                poiList.add(new PointOfInterest(id, name, address, type, rating, longitude, latitude));
            }
            return poiList;
        } catch (JSONException exception) {
            System.out.println("Couldn't parse POI JSON " + exception.toString());
        }
        return null;
    }

    public static String toString(List<PointOfInterest> pois) {
        StringBuilder builder = new StringBuilder();
        for (PointOfInterest pointOfInterest : pois) {
            builder.append(pointOfInterest.toString());
            builder.append("\n\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println((PointOfInterest.fetchPOIs(-15.960259, -5.691079)));
    }


}
