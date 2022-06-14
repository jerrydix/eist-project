package Server.Model.Flights.POI;

import Server.Model.Flights.Flight;
import Server.Model.Flights.Location;
import Server.Model.Flights.Weather.Weather;
import Server.Model.Flights.Weather.WeatherParser;
import Server.Model.Networking.HTTP_GetRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PointOfInterestParser {

    public static List<PointOfInterest> parsePOIJson(String jsonText) {
        try {
            JSONObject jsonObject = new JSONObject(jsonText.toString());
            JSONArray resultsArray = jsonObject.getJSONArray("results");
            List<PointOfInterest> poiList = new ArrayList<>();

            for (int i = 0; i < resultsArray.length() && i<10; i++) {
                String id = resultsArray.getJSONObject(i).getString("place_id");
                String name = resultsArray.getJSONObject(i).getString("name");
                String address = resultsArray.getJSONObject(i).getString("vicinity");
                String type = resultsArray.getJSONObject(i).getJSONArray("types").getString(0);
                if(type.equals("lodging")){
                    continue;
                }
                double rating = -1;
                if(!resultsArray.getJSONObject(i).isNull("rating")){
                    rating = resultsArray.getJSONObject(i).getDouble("rating") ;
                }
                double latitude = resultsArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                double longitude = resultsArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                poiList.add(new PointOfInterest(id, name, address, type, null, rating, longitude, latitude));
            }
            return poiList;
        }catch (JSONException exception){
            System.out.println("Couldn't parse POI JSON "+exception.toString());
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
        System.out.println((PointOfInterest.fetchPOIs(-15.960259,-5.691079)).size());
    }




}
