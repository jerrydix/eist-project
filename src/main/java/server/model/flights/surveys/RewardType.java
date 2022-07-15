package server.model.flights.surveys;

public enum RewardType {
    MILE, SOUVENIR, COUPON, PRICE_DRAWING;

    public String toString() {
        return switch (this) {
            case MILE -> "Miles";
            case COUPON -> "Coupon";
            case SOUVENIR -> "Souvenir";
            case PRICE_DRAWING -> "Price drawing";
            default -> "lol";
        };
    }
}
