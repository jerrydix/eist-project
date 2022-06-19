package server.model.entertainment.movies;

import java.util.List;

public class Catalogue {
    // defaults to -1, if non-existent
    private int id = -1;
    private final List<Movie> movies;
    Catalogue(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public int getId() {
        return id;
    }
}
