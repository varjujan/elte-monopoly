package monopoly.model.dice;

/**
 * Created by Janos on 2016. 10. 28..
 */
public class SingleDiceResult implements DiceResult {

    private int value;

    public SingleDiceResult(int value) {
        this.value = value;
    }

    public int getResult() {
        return value;
    }

}
