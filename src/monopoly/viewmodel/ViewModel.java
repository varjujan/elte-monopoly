package monopoly.viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import monopoly.model.Model;

import java.util.Arrays;
import java.util.List;

public class ViewModel {

    private final Model model;

    private List<StringProperty> playerNames;
    private List<IntegerProperty> playerMoney;
    private List<IntegerProperty> playerPositions;

    private IntegerProperty firstDiceValue;
    private IntegerProperty secondDiceValue;

    public ViewModel(Model model) {
        this.model = model;

        playerNames = Arrays.asList(new SimpleStringProperty(model.getPlayer(0).getName()),
                new SimpleStringProperty(model.getPlayer(1).getName()),
                new SimpleStringProperty(model.getPlayer(2).getName()),
                new SimpleStringProperty(model.getPlayer(3).getName()));

        playerMoney = Arrays.asList(new SimpleIntegerProperty(model.getPlayer(0).getMoney()),
                new SimpleIntegerProperty(model.getPlayer(1).getMoney()),
                new SimpleIntegerProperty(model.getPlayer(2).getMoney()),
                new SimpleIntegerProperty(model.getPlayer(3).getMoney()));

        playerPositions = Arrays.asList(new SimpleIntegerProperty(model.getPlayerPosition(0)),
                new SimpleIntegerProperty(model.getPlayerPosition(1)),
                new SimpleIntegerProperty(model.getPlayerPosition(2)),
                new SimpleIntegerProperty(model.getPlayerPosition(3)));
    }

    public int getPlayerMoney(int ind) {
        return playerMoney.get(ind).get();
    }

    public void increasePlayerMoney(int ind, int sum) {
        model.increasePlayerMoney(ind, sum);
        playerMoney.get(ind).set(model.getPlayerMoney(ind));
    }

    public void reducePlayerMoney(int ind, int sum) {
        model.reducePlayerMoney(ind, sum);
        playerMoney.get(ind).set(model.getPlayerMoney(ind));
    }

    public void setPlayerName(int ind, String name) {
        model.setPlayerName(ind, name);
        playerNames.get(ind).set(name);
    }

    public IntegerProperty getPlayerMoneyProperty(int ind) {
        return playerMoney.get(ind);
    }

    public StringProperty getPlayerNameProperty(int ind) {
        return playerNames.get(ind);
    }

    public IntegerProperty getPlayerPositionProperty(int ind) {
        return playerPositions.get(ind);
    }
}
