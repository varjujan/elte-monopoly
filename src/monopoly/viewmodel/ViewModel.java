package monopoly.viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import monopoly.model.Model;

import java.util.Arrays;
import java.util.List;

public class ViewModel {

    private final Model model;

    private List<PlayerViewModel> players;

    public ViewModel(Model model) {
        this.model = model;
        this.players = Arrays.asList(new PlayerViewModel(model.getPlayer(0)), new PlayerViewModel(model.getPlayer(0)),
                new PlayerViewModel(model.getPlayer(0)), new PlayerViewModel(model.getPlayer(0)));
    }

    public int getPlayerMoney(int ind) {
        return players.get(ind).getMoney();
    }

    public void increasePlayerMoney(int ind, int sum) {
        players.get(ind).increaseMoney(sum);
    }

    public void reducePlayerMoney(int ind, int sum) {
        players.get(ind).reduceMoney(sum);
    }

    public void updateNames(String player0Name, String player1Name, String player2Name, String player3Name) {
        players.get(0).setName(player0Name);
        players.get(1).setName(player1Name);
        players.get(2).setName(player2Name);
        players.get(3).setName(player3Name);
    }

    public IntegerProperty getPlayerMoneyProperty(int ind) {
        return players.get(ind).moneyProperty();
    }

    public StringProperty getPlayerNameProperty(int ind) {
        return players.get(ind).nameProperty();
    }
}
