package server.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AirportParser {

    /**
     * parseAirportJson() parses the IATA of the airports of a given into a list of strings
     *
     * @param jsonText The get request response of the Airlabs API (airports DB) containing airport IATA and formatted in json
     * @return A list of aiport IATAs of the given city
     *
     */

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
