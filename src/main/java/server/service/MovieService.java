package server.service;

import org.springframework.stereotype.Service;
import server.model.entertainment.CatalogueFactory;
import server.model.entertainment.MovieCatalogue;

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