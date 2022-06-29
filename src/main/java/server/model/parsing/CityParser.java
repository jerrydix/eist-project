package server.model.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CityParser {

    //index 0: city name, index 1: IATA code
    public static String[] parseCityJson(String jsonText) {
        try {
            String[] data = new String[3];
            JSONObject jsonObject = new JSONObject(jsonText);
            if (jsonObject.has("status") && jsonObject.getString("status").equals("ZERO_RESULTS")) {
                System.out.println("Couldnt parse city json / no city with this name found");
                return null;
            }
            JSONArray array = jsonObject.getJSONObject("response").getJSONArray("cities");
            for (int i = 0; i < array.length() && i < 3; i++) {
                data[i] = array.getJSONObject(0).getString("name") + " (" + array.getJSONObject(0).getString("city_code") + ")";
            }
            return data;
        } catch (JSONException e) {
            System.out.println("City IATA could not be parsed");
        }
        return null;
    }
}
