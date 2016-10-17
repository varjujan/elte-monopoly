package monopoly.model.dice;

import monopoly.util.random.Randomizer;

public class TwoStandardDices implements Dice {

    private StandardDice dice1;
    private StandardDice dice2;

    public TwoStandardDices(StandardDice dice1, StandardDice dice2) {
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    @Override
    public int roll() {
        return dice1.roll() + dice2.roll();
    }

    //TODO: Getter for rolled numbers per dice.
}
