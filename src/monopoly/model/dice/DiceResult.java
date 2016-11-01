package monopoly.model.dice;

import java.util.List;

public interface DiceResult {
    List<Integer> getResult();

    int getRollValue();
}
