package server.parsing;

import org.json.JSONException;
import org.json.JSONObject;
import server.model.entertainment.Movie;

public class MovieParser {

    /**
     * A parser method to parse the API response by the OMDb into a Movie object
     *
     * @param json The API response containing the Movie data, formatted in json
     * @return A Movie object containing the movie data
     */
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
        } catch (JSONException e) {
            System.out.println("Incomplete JSON object! Unable to parse.");
            return null;
        }
    }
}