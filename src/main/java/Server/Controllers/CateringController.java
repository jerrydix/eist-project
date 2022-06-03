package Server.Controllers;

import Server.Model.Catering.Catering;
import Server.Model.Catering.CateringFactory;

public class CateringController {

    private Catering catering;

    public CateringController() {
        catering = CateringFactory.createCatering(50, 50, 50);
    }

    public Catering getCatering() {
        return catering;
    }

    public Catering saveCatering(Catering catering) {
        //TODO: used in updating caterings
        return null;
    }
}
