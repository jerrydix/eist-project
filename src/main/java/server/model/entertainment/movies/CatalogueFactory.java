package server.model.entertainment.movies;

import server.model.networking.HTTP_GetRequest;
import server.model.parsing.MovieParser;

import java.util.ArrayList;
import java.util.List;

public class CatalogueFactory {
    /**
     * Create a movie for each entry in {@link MovieTag} as long as it is registered in
     * {@link CatalogueFactory#getMovie(MovieTag) getMovie()} and a valid entry in
     * {@linkplain <a href="http://omdbapi.com">OMDb</a>} exists.
     * Gather all movies in a list.
     * @return {@link MovieCatalogue} with list of movies based on {@link MovieTag}
     */
    public static MovieCatalogue createMovieCatalogue() {
        List<Movie> movies = new ArrayList<>();
        for (MovieTag tag : MovieTag.values()) {
            Movie movie = getMovie(tag);
            if (movie != null) {
                movies.add(movie);
            }
        }
        return new MovieCatalogue(movies);
    }

    /**
     * Build an URL for {@linkplain <a href="http://omdbapi.com">OMDb</a>} and return the
     * {@link Movie} object after parsing the JSON response using {@link MovieParser}.
     * All tags have a corresponding entry in the first switch statement.
     * @param tag Short-hand for a searchable movie on OMDb
     * @return A {@link Movie} object
     */
    private static Movie getMovie(MovieTag tag) {
        String title = "&t=" + switch (tag) {
            case HULK -> "Hulk";
            case BLADERUNNER -> "Blade+Runner";
            case MORBIUS -> "Morbius";
            case STARWARS -> "Star+Wars";
            case SPEED -> "Speed";
            case PENGUIN -> "Penguin";
        };
        String[] urlParameters = {
                "?apikey=b0c08011",
                "&type=movie",
                "&plot=short",
                "&r=json",
                title
        };
        return MovieParser.parseMovieJson(
                HTTP_GetRequest.httpRequest("http://www.omdbapi.com", urlParameters)
        );
    }
}
