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
    public Player currentPlayer() {
        return players.get(curr);
    }

    @Override
    public Player nextPlayer() {
        this.curr = (this.curr + 1) % players.size();
        return currentPlayer();
    }

    @Override
    public void setName(String name, int ind) {
        players.get(ind).setName(name);
    }

    @Override
    public void increasePlayerMoney(int ind, int sum) {
        players.get(ind).increaseMoney(sum);
    }

    @Override
    public void reducePlayerMoney(int ind, int sum) {
        players.get(ind).reduceMoney(sum);
    }

    @Override
    public int getPlayerMoney(int ind) {
        return players.get(ind).getMoney();
    }

    @Override
    public Player getPlayer(int ind) {
        return players.get(ind);
    }
}
