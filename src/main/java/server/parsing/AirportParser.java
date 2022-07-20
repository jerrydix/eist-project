package server.parsing;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AirportParser {



    public static String parseAirportJson(String jsonText) {
        try {
            JSONObject obj = new JSONObject(jsonText.toString());
            return obj.getJSONArray("response").getJSONObject(0).getString("iata_code");
        } catch (JSONException exception) {
            System.out.println("Couldn't parse Airport IATA json");
        }
        return null;
    }
}
