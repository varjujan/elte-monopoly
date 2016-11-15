package monopoly.model.dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultipleDiceResult implements DiceResult {

    private List<DiceResult> results;

    public MultipleDiceResult(DiceResult... results) {
        this.results = Arrays.asList(results);
    }

    @Override
    public List<Integer> getResult() {
        List<Integer> ret = new ArrayList<>();
        for (DiceResult sdRes : results) {
            ret.addAll(sdRes.getResult());
        }
        return ret;
    }

    @Override
    public int getRollValue() {
        return results.stream().mapToInt(DiceResult::getRollValue).sum();
    }

}
