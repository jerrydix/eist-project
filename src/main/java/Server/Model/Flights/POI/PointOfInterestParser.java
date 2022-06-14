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

    public static List<PointOfInterest> parsePOIJson(String jsonText) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonText.toString());
        JSONArray resultsArray = jsonObject.getJSONArray("results");
        List<PointOfInterest> poiList = new ArrayList<>();

        for (int i = 0; i < resultsArray.length(); i++) {
            String id = resultsArray.getJSONObject(i).getString("place_id");
            String name = resultsArray.getJSONObject(i).getString("name");
            String address = resultsArray.getJSONObject(i).getString("vicinity");
            String type = resultsArray.getJSONObject(i).getJSONArray("types").getString(0);
            double rating = resultsArray.getJSONObject(i).getDouble("rating");
            double latitude = resultsArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
            double longitude = resultsArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng");

            poiList.add(new PointOfInterest(id, name, address, type, null, rating, longitude, latitude));
        }
        return poiList;
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
        try {
            System.out.println(PointOfInterestParser.toString(PointOfInterestParser.parsePOIJson(HTTP_GetRequest.httpRequest("https://maps.googleapis.com/maps/api/place/nearbysearch/json", new String[]{"?location=-33.8670522%2C151.1957362", "&radius=1500", "&type=point_of_interest", "&key=AIzaSyBKiScI4WumTVipTbFuC6KPHic3dC66tvM"}))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
