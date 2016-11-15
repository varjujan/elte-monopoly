package monopoly.model.dice;

public class TwoStandardDices implements Dice {

    private StandardDice dice1;
    private StandardDice dice2;

    public TwoStandardDices(StandardDice dice1, StandardDice dice2) {
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    @Override
    public MultipleDiceResult roll() {
        return new MultipleDiceResult(dice1.roll(), dice2.roll());
    }

}
