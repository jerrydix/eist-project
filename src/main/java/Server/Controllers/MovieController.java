package Server.Controllers;

import Server.Model.Entertainment.Movies.Catalogue;
import Server.Model.Entertainment.Movies.CatalogueFactory;

public class MovieController {
    private Catalogue catalogue;
    public MovieController() {
        catalogue = CatalogueFactory.createMovieCatalogue();
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }
}
