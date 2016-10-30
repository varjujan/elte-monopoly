package monopoly.model.player.changer;

import monopoly.model.dice.MultipleDiceResult;
import monopoly.model.player.Player;

public interface PlayerChanger {
    Player currentPlayer();

    int currentPlayerIndex();

    Player nextPlayer();

    String getName(int ind);

    void setName(String name, int ind);

    int getPlayerMoney(int ind);

    void increasePlayerMoney(int ind, int sum);

    void reducePlayerMoney(int ind, int sum);

    Player getPlayer(int ind);

    boolean handleRoll(MultipleDiceResult result);
}
