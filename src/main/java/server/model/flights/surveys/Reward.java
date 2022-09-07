package server.model.flights.surveys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class Reward {

    private static final RewardType[] REWARD_TYPES = RewardType.values();
    private final RewardType rewardType;
    private final int amount;
    private final String description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Reward() {
        this.rewardType = REWARD_TYPES[generateRandom(0, REWARD_TYPES.length)];

        this.amount = switch (rewardType) {
            case MILE -> generateRandom(10000, 60000);
            case SOUVENIR, COUPON -> 1;
            default -> generateRandom(1, 5);
        };

        this.description = switch (rewardType) {
            case MILE -> "Applicable in the Garching Airlines online shop.";
            case SOUVENIR -> switch (generateRandom(0, 3)) {
                case 0 -> "Flag of your destination country!";
                case 1 -> "Free neck pillow!";
                default -> "Garching Airlines shirt!";
            };
            case COUPON -> switch (generateRandom(0, 3)) {
                case 0 -> "Free drink at BurgerKing!";
                case 1 -> "Free coffee at Starbucks!";
                default -> "Free ice-cream at Starbucks!";
            };
            default -> "Ticket for the Garching Airlines customer lottery!";
        };
    }

    public RewardType getRewardType() {
        return rewardType;
    }

    public int getAmount() {
        return amount;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Generates a random number for the mileage reward type.
     *
     * @param min The minimum amount of miles that can be rewarded
     * @param max The maximum amount of miles that can be rewarded
     * @return A random number equally big or greater than min and smaller than max - min
     */
    private int generateRandom(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }

    public String toString() {
        return amount + " " + rewardType.toString() + "(s)! " + description;
    }
}
