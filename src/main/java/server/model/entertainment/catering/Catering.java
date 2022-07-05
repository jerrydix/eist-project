package server.model.entertainment.catering;

import java.util.List;

public class Catering {
    // defaults to -1, if non-existent
    private int id = -1;
    private List<Consumable> consumables;

    Catering(List<Consumable> consumables) {
        this.consumables = consumables;
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }
    public int getId() {
        return id;
    }

    /**
     * Adds a given consumable to the menu if it is in stock
     *
     * @param consumable The consumable that is to be added to the menu
     */
    public void addConsumableToMenu(Consumable consumable) {
        if (consumable.getAmountInStock() <= 0) {
            return;
        }

        for (Consumable item : consumables) {
            if (item.getTag() == consumable.getTag()) {
                item.increaseAmountInStock(consumable.getAmountInStock());
            }
        }
        consumables.add(consumable);
    }

    /**
     * "Sells" a food / drink item to a user, depending on its name and whether it is in stock
     *
     * @param name The name of the consumable that is to be sold
     * @return A boolean which states whether the consumable has been sold successfully or not
     */
    public boolean sellConsumable(ConsumableTag name) {
        for (Consumable item : consumables) {
            if (item.getTag() == name) {
                return item.sell();
            }
        }
        return false;
    }
}
