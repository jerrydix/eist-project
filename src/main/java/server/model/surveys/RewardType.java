package server.model.surveys;

public enum RewardType {
    MILE, SOUVENIR, COUPON, PRICE_DRAWING;

    public String toString() {
        return switch (this) {
            case MILE -> "mile";
            case COUPON -> "coupon";
            case SOUVENIR -> "souvenir";
            case PRICE_DRAWING -> "price drawing";
            default -> "lol";
        };
    }
}
