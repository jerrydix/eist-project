package server.model.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CityParser {

    /**
     * parseCityJson() parses the name and IATA of a given city into an array of strings
     *
     * @param jsonText The get request response of the Airlabs API (cities DB) containing city information and formatted in json
     * @return A string array (length: 2) containing the city name and its IATA code, whereby index 0 is the city name and index 1 is the IATA code
     */
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
                data[i] = array.getJSONObject(i).getString("name") + " (" + array.getJSONObject(i).getString("city_code") + ")";
            }
            return data;
        } catch (JSONException e) {
            System.out.println("City IATA could not be parsed");
        }
        return null;
    }
}
