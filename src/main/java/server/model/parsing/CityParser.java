package server.model.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CityParser {

    //index 0: city name, index 1: IATA code
    public static String[] parseCityJson(String jsonText) {
        try {
            String[] data = new String[2];
            JSONObject jsonObject = new JSONObject(jsonText);
            if (jsonObject.has("status") && jsonObject.getString("status").equals("ZERO_RESULTS")) {
                System.out.println("Couldnt parse city json / no city with this name found");
                return null;
            }
            JSONArray array = jsonObject.getJSONObject("response").getJSONArray("cities");
            data[0] = array.getJSONObject(0).getString("name");
            data[1] = array.getJSONObject(0).getString("city_code");
            return data;
        } catch (JSONException e) {
            System.out.println("City IATA could not be parsed");
        }
        return null;
    }
}
