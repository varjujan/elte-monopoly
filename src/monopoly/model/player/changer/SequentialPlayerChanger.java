package monopoly.model.player.changer;

import monopoly.model.player.Player;

import java.util.List;

public class SequentialPlayerChanger implements PlayerChanger {

    private List<Player> players;
    private int curr;

    public SequentialPlayerChanger(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("At least one player should exist.");
        }

        this.players = players;
        this.curr = 0;
    }

    @Override
    public Player current() {
        return players.get(0);
    }

    @Override
    public Player next() {
        this.curr = (this.curr + 1) % players.size();
        return current();
    }
}
