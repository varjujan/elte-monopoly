package monopoly.model.dice;

import monopoly.util.random.Randomizer;

public class StandardDice implements Dice {

    private Randomizer randomizer;

    public StandardDice(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

    @Override
    public SingleDiceResult roll() {
        return new SingleDiceResult(randomizer.random(1, 6));
    }
}
