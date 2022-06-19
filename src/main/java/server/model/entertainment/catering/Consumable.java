package server.model.entertainment.catering;

class Consumable {
    private final String name;
    private final ConsumableTag tag;
    private final String description;
    private final int price;
    private int amountInStock;

    Consumable(ConsumableTag tag, String name, String description, int price, int amountInStock) {
        this.tag = tag;
        this.name = name;
        this.description = description;
        this.amountInStock = amountInStock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ConsumableTag getTag() {
        return tag;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void increaseAmountInStock(int amount) {
        if (amount > 0) {
            amountInStock += amount;
        }
    }

    public boolean sell() {
        if (amountInStock > 0) {
            amountInStock -= 1;
            return true;
        }
        return false;
    }
}
