package monopoly.model.player.changer;

import monopoly.model.dice.DiceResult;
import monopoly.model.player.Player;
import monopoly.model.player.State;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public int currentPlayerIndex() {
        return curr;
    }

    @Override
    public Player nextPlayer() {
        this.curr = (this.curr + 1) % players.size();

        if (currentPlayer().getState() != State.InJail) {
            currentPlayer().setDiceRollsLeft(3);
        }

        return currentPlayer();
    }

    @Override
    public String getName(int ind) {
        return players.get(ind).getName();
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

    @Override
    public boolean handleRoll(DiceResult result) {
        Set<Integer> uniqueValues = new HashSet<>(result.getResult());

        if (uniqueValues.size() == 1) {
            currentPlayer().decreaseDiceRollsLeft();
            if (currentPlayer().getDiceRollsLeft() == 0) {
                currentPlayer().setPosition(-1);
                currentPlayer().setState(State.InJail);
                return false;
            }
        } else {
            currentPlayer().setDiceRollsLeft(0);
        }

        return true;
    }
}
