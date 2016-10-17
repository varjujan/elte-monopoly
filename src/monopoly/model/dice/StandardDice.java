package monopoly.model.dice;

import monopoly.util.random.Randomizer;

public class StandardDice implements Dice {

    private Randomizer randomizer;

    public StandardDice(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

    @Override
    public int roll() {
        return randomizer.random(1, 6);
    }
}
