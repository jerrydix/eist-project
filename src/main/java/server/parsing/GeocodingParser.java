package server.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GeocodingParser {

    /**
     * parseGeocodingJson() parses the longitude and latitude of a location and returns them in an array of doubles
     *
     * @param json The API response of the Google Maps Geocoding API, formatted in json
     * @return An array of doubles (length: 2), whereby index 0 is the longitude and index 1 ist the latitude of the city coordinates
     */
    public static double[] parseGeocodingJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("results");
            JSONObject location = array.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
            double lat = location.getDouble("lat");
            double lng = location.getDouble("lng");
            return new double[]{lng, lat};
        } catch (JSONException jsonException) {
            System.out.println("Geocoding parser was unable to parse: " + json);
        }
        return null;
    }

}
