package server.model.parsing;

import org.json.JSONException;
import org.json.JSONObject;
import server.model.entertainment.movies.Movie;

public class MovieParser {
    public static Movie parseMovieJson(String json) {
        try {
            JSONObject response = new JSONObject(json);
            // throws classcast or nullpointerexception - how to handle?
            if (!response.getBoolean("Response")) {
                System.out.println(response.getString("Error"));
                return null;
            }
            return new Movie(
                    response.getString("Title"),
                    response.getInt("Year"),
                    response.getString("Rated"),
                    response.getString("Genre").split("\\s*,\\s*"),
                    response.getString("Director"),
                    response.getString("Plot"),
                    response.getString("Poster")
            );
        }
        catch (JSONException e) {
            System.out.println("Incomplete JSON object! Unable to parse.");
            return null;
        }
    }
}