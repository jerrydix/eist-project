package server.model.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AirportParser {

    //todo remove

    public static List<String> parseAirportJson(String jsonText) {
        try {
            JSONObject obj = new JSONObject(jsonText.toString());
        } catch (JSONException exception) {
            System.out.println("Couldn't parse Airport IATA json");
        }
        return null;
    }
}
