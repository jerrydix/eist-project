package Server.Model.Surveys;

public class Reward {

    private RewardType rewardType;
    private int amount;

    //TODO: genauer implementieren -> einzelne Unterklassen, Generation
    public Reward(RewardType rewardType, int amount) {
        this.rewardType = rewardType;
        this.amount = amount;
    }
}
