package server.model.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CityIATAParser {

    public static String parseCityIATAJson(String jsonText) {
        try {
            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray array = jsonObject.getJSONObject("response").getJSONArray("cities");
            return array.getJSONObject(0).getString("city_code");
        } catch (JSONException e) {
            System.out.println("City IATA could not be parsed");
        }
        return null;
    }
}
