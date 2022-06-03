package Server.Model.Catering;

import java.util.ArrayList;
import java.util.List;

public class CateringFactory {
    public static Catering createCatering(int amountApples, int amountWater, int amountCola) {
        List<Consumable> consumables = new ArrayList<>();
        consumables.add(getConsumable(ConsumableTag.APPLE, amountApples));
        consumables.add(getConsumable(ConsumableTag.WATER, amountWater));
        consumables.add(getConsumable(ConsumableTag.COLA, amountCola));
        return new Catering(consumables);
    }

    public static Consumable getConsumable(ConsumableTag tag, int amount) {
        String description = switch (tag) {
            case APPLE -> "Fresh, juicy and delicious!";
            case COLA -> "Your favourite softdrink!";
            case WATER -> "Classic.";
        };
        int price = switch (tag) {
            case APPLE -> 3;
            case COLA -> 2;
            case WATER -> 1;
        };
        String name = switch (tag) {
            case APPLE -> "Green Apple";
            case COLA -> "Cool Cola";
            case WATER -> "Witty Water";
        };
        return new Consumable(tag, name, description, price, amount);
    }
}
