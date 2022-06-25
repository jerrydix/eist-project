package server.service;

import server.model.entertainment.movies.MovieCatalogue;
import server.model.entertainment.movies.CatalogueFactory;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private MovieCatalogue movieCatalogue;
    public MovieService() {
        movieCatalogue = CatalogueFactory.createMovieCatalogue();
    }

    public MovieCatalogue getCatalogue() {
        return movieCatalogue;
    }
}
