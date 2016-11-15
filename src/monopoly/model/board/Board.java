package monopoly.model.board;

import monopoly.model.field.Field;
import monopoly.model.player.Player;

public interface Board {

    Field getFieldAt(int pos);

    Field getFieldAt(Player player);
}
