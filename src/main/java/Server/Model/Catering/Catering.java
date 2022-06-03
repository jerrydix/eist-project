package Server.Model.Catering;

import java.util.List;

public class Catering {
    private List<Consumable> consumables;

    Catering(List<Consumable> consumables) {
        this.consumables = consumables;
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }

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

    public boolean sellConsumable(ConsumableTag name) {
        for (Consumable item : consumables) {
            if (item.getTag() == name) {
                return item.sell();
            }
        }
        return false;
    }
}
