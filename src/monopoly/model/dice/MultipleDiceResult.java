package monopoly.model.dice;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Janos on 2016. 10. 28..
 */
public class MultipleDiceResult implements DiceResult {

    private List<SingleDiceResult> results;

    public MultipleDiceResult(SingleDiceResult... results) {
        this.results = Arrays.asList(results);
    }

    public List<SingleDiceResult> getResult() {
        return results;
    }

}
