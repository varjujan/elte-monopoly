package monopoly.model.board;

import monopoly.model.field.Field;
import monopoly.model.player.Player;

public interface Board {

    Field getFieldAt(int pos);
    Field getPlayerPos(Player player);
    void stepPlayer(Player player, int pos);

}
