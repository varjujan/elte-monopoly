package monopoly.model.dice;

import java.util.Arrays;
import java.util.List;

public class SingleDiceResult implements DiceResult {

    private int value;

    public SingleDiceResult(int value) {
        this.value = value;
    }

    @Override
    public List<Integer> getResult() {
        return Arrays.asList(value);
    }

    @Override
    public int getRollValue() {
        return value;
    }

}
