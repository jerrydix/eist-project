package server.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AirportParser {

    public static List<String> parseAirportJson(String jsonText) {
        try {
            JSONObject obj = new JSONObject(jsonText.toString());
            JSONArray arr = obj.getJSONArray("response");
            List<String> airports = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                if (arr.getJSONObject(i).has("iata_code")) {
                    airports.add(arr.getJSONObject(i).getString("iata_code"));
                }
            }
            return airports;
        } catch (JSONException exception) {
            System.out.println("Couldn't parse Airport IATA json" + jsonText + "\n\n" + exception);
        }
        return null;
    }
}
