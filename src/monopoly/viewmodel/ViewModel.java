package monopoly.viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import monopoly.model.Model;
import monopoly.model.field.Field;
import monopoly.model.player.Player;

import java.util.HashMap;
import java.util.Map;

public class ViewModel {

    private final Model model;

    private Map<Player, StringProperty> playerNames;
    private Map<Player, IntegerProperty> playerMoney;
    private Map<Player, IntegerProperty> playerPositions;
    private Map<Player, ListProperty<monopoly.model.field.Property>> playerProperties;
    private BooleanProperty currentPlayersFieldNotBuyable;

    private IntegerProperty firstDiceValue;
    private IntegerProperty secondDiceValue;

    public ViewModel(Model model) {
        this.model = model;

        playerNames = new HashMap<Player, StringProperty>() {{
            put(model.getPlayer(0), new SimpleStringProperty(model.getPlayer(0).getName()));
            put(model.getPlayer(1), new SimpleStringProperty(model.getPlayer(1).getName()));
            put(model.getPlayer(2), new SimpleStringProperty(model.getPlayer(2).getName()));
            put(model.getPlayer(3), new SimpleStringProperty(model.getPlayer(3).getName()));
        }};

        playerMoney = new HashMap<Player, IntegerProperty>() {{
            put(model.getPlayer(0), new SimpleIntegerProperty(model.getPlayer(0).getMoney()));
            put(model.getPlayer(1), new SimpleIntegerProperty(model.getPlayer(1).getMoney()));
            put(model.getPlayer(2), new SimpleIntegerProperty(model.getPlayer(2).getMoney()));
            put(model.getPlayer(3), new SimpleIntegerProperty(model.getPlayer(3).getMoney()));
        }};

        playerPositions = new HashMap<Player, IntegerProperty>() {{
            put(model.getPlayer(0), new SimpleIntegerProperty(model.getPlayerPosition(0)));
            put(model.getPlayer(1), new SimpleIntegerProperty(model.getPlayerPosition(1)));
            put(model.getPlayer(2), new SimpleIntegerProperty(model.getPlayerPosition(2)));
            put(model.getPlayer(3), new SimpleIntegerProperty(model.getPlayerPosition(3)));
        }};

        playerProperties = new HashMap<Player, ListProperty<monopoly.model.field.Property>>() {{
            put(model.getPlayer(0), new SimpleListProperty<>());
            put(model.getPlayer(1), new SimpleListProperty<>());
            put(model.getPlayer(2), new SimpleListProperty<>());
            put(model.getPlayer(3), new SimpleListProperty<>());
        }};

        currentPlayersFieldNotBuyable = new SimpleBooleanProperty(true);
    }

    public int getPlayerMoney(int ind) {
        return playerMoney.get(model.getPlayer(ind)).get();
    }

    public void increasePlayerMoney(int ind, int sum) {
        model.increasePlayerMoney(ind, sum);
        playerMoney.get(model.getPlayer(ind)).set(model.getPlayerMoney(ind));
    }

    public void reducePlayerMoney(int ind, int sum) {
        model.reducePlayerMoney(ind, sum);
        playerMoney.get(model.getPlayer(ind)).set(model.getPlayerMoney(ind));
    }

    public void setPlayerName(int ind, String name) {
        model.setPlayerName(ind, name);
        playerNames.get(model.getPlayer(ind)).set(name);
    }

    public void moveCurrentPlayer(int value) {
        model.moveCurrentPlayer(value);

        playerPositions.get(model.getCurrentPlayer()).set(model.getCurrentPlayer().getPosition());

        if (model.getCurrentPlayersField() instanceof monopoly.model.field.Property) {
            currentPlayersFieldNotBuyable.setValue(((monopoly.model.field.Property) model.getCurrentPlayersField()).hasOwner());
        } else {
            currentPlayersFieldNotBuyable.setValue(true);
        }
    }

    public void buyProperty() {
        model.buyProperty();

        playerProperties.get(model.getCurrentPlayer()).set(FXCollections.observableArrayList(model.getCurrentPlayer().getProperties()));
        playerMoney.get(model.getCurrentPlayer()).set(model.getCurrentPlayer().getMoney());
        if (model.getCurrentPlayersField() instanceof monopoly.model.field.Property) {
            currentPlayersFieldNotBuyable.setValue(((monopoly.model.field.Property) model.getCurrentPlayersField()).hasOwner());
        } else {
            currentPlayersFieldNotBuyable.setValue(true);
        }
    }

    public Player getCurrentPlayer() {
        return model.getCurrentPlayer();
    }

    public Field getCurrentPlayersField() {
        return model.getCurrentPlayersField();
    }

    public IntegerProperty getPlayerMoneyProperty(int ind) {
        return playerMoney.get(model.getPlayer(ind));
    }

    public StringProperty getPlayerNameProperty(int ind) {
        return playerNames.get(model.getPlayer(ind));
    }

    public IntegerProperty getPlayerPositionProperty(int ind) {
        return playerPositions.get(model.getPlayer(ind));
    }

    public ListProperty<monopoly.model.field.Property> getPlayerPropertiesProperty(int ind) {
        return playerProperties.get(model.getPlayer(ind));
    }

    public BooleanProperty getCurrentPlayersFieldNotBuyableProperty() {
        return currentPlayersFieldNotBuyable;
    }
}
