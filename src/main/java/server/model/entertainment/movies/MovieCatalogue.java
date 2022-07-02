package server.model.entertainment.movies;

import java.util.List;

public class MovieCatalogue {
    private final List<Movie> movies;
    MovieCatalogue(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
