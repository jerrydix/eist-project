package server.model.entertainment.movies;

import java.util.ArrayList;
import java.util.List;

public class CatalogueFactory {
    public static Catalogue createMovieCatalogue() {
        List<Movie> movies = new ArrayList<>();
        for (MovieTag tag : MovieTag.values()) {
            movies.add(getMovie(tag));
        }
        return new Catalogue(movies);
    }

    public static Movie getMovie(MovieTag tag) {
        String description = switch(tag) {
            case HULK -> "The Green Menace";
            case RICKROLL -> "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
            case PENGUINS -> "There is no sweeter creature on earth!";
        };
        String name = switch (tag) {
            case HULK -> "The Monstrous Hulk";
            case RICKROLL -> "Rick Astley - Never Gonna Give You Up";
            case PENGUINS -> "PGdP - A Documentary";
        };
        int ageRating = switch (tag) {
            case HULK -> 12;
            case RICKROLL, PENGUINS -> 0;
        };
        String coverPath = "movieCovers/" + tag + ".jpg";
        return new Movie(tag, name, description, ageRating, coverPath);
    }
}
