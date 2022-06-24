package server.model.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GeocodingParser {

    //index 0: latitude, index 1: longitude
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
