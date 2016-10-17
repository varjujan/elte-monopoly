package monopoly.model.player.changer;

import monopoly.model.player.Player;

public interface PlayerChanger {
    Player current();
    Player next();
}
