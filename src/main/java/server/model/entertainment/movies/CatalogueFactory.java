package server.model.entertainment.movies;

import server.model.networking.HTTP_GetRequest;
import server.model.parsing.MovieParser;

import java.util.ArrayList;
import java.util.List;

public class CatalogueFactory {
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

    private static Movie getMovie(MovieTag tag) {
        String title = "&t=" + switch (tag) {
            case HULK -> "Hulk";
            case BLADERUNNER -> "Blade+Runner";
            case MORBIUS -> "Morbius";
            case STARWARS -> "Star+Wars";
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
