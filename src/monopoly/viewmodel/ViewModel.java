package monopoly.viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import monopoly.model.Model;
import monopoly.model.dice.DiceResult;
import monopoly.model.dice.MultipleDiceResult;
import monopoly.model.field.Field;
import monopoly.model.field.Property;
import monopoly.model.field.UpgradeableField;
import monopoly.model.player.Player;

import java.util.HashMap;
import java.util.Map;

public class ViewModel {

    private final Model model;

    private Map<Player, StringProperty> playerNames;
    private Map<Player, IntegerProperty> playerMoney;
    private Map<Player, IntegerProperty> playerPositions;
    private Map<Player, IntegerProperty> playerDiceRollsLeft;
    private Map<Player, ListProperty<monopoly.model.field.Property>> playerProperties;

    private Map<Integer, IntegerProperty> levelProperties;

    private StringProperty currentPlayerNameProperty;

    private IntegerProperty firstDiceValue;
    private IntegerProperty secondDiceValue;

    private IntegerProperty currentPlayerIndex;

    private IntegerProperty bankMoney;
    private IntegerProperty bankHouseCount;
    private IntegerProperty bankHotelCount;

    public ViewModel(Model model) {
        this.model = model;

        playerNames = new HashMap<Player, StringProperty>() {{
            put(model.getPlayer(0), new SimpleStringProperty(model.getPlayerName(0)));
            put(model.getPlayer(1), new SimpleStringProperty(model.getPlayerName(1)));
            put(model.getPlayer(2), new SimpleStringProperty(model.getPlayerName(2)));
            put(model.getPlayer(3), new SimpleStringProperty(model.getPlayerName(3)));
        }};

        playerMoney = new HashMap<Player, IntegerProperty>() {{
            put(model.getPlayer(0), new SimpleIntegerProperty(model.getPlayerMoney(0)));
            put(model.getPlayer(1), new SimpleIntegerProperty(model.getPlayerMoney(1)));
            put(model.getPlayer(2), new SimpleIntegerProperty(model.getPlayerMoney(2)));
            put(model.getPlayer(3), new SimpleIntegerProperty(model.getPlayerMoney(3)));
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

        playerDiceRollsLeft = new HashMap<Player, IntegerProperty>() {{
            put(model.getPlayer(0), new SimpleIntegerProperty(model.getPlayer(0).getDiceRollsLeft()));
            put(model.getPlayer(1), new SimpleIntegerProperty(model.getPlayer(1).getDiceRollsLeft()));
            put(model.getPlayer(2), new SimpleIntegerProperty(model.getPlayer(2).getDiceRollsLeft()));
            put(model.getPlayer(3), new SimpleIntegerProperty(model.getPlayer(3).getDiceRollsLeft()));
        }};

        firstDiceValue = new SimpleIntegerProperty(0);
        secondDiceValue = new SimpleIntegerProperty(0);

        currentPlayerIndex = new SimpleIntegerProperty(0);

        currentPlayerNameProperty = new SimpleStringProperty(model.getPlayer(0).getName());

        bankMoney = new SimpleIntegerProperty(model.getBankMoney());
        bankHouseCount = new SimpleIntegerProperty(model.getBankHouseCount());
        bankHotelCount = new SimpleIntegerProperty(model.getBankHotelCount());

        levelProperties = new HashMap<Integer, IntegerProperty>() {{
            put(1, new SimpleIntegerProperty(model.getPropertyAt(1).getLevel()));
            put(3, new SimpleIntegerProperty(model.getPropertyAt(3).getLevel()));
            put(6, new SimpleIntegerProperty(model.getPropertyAt(6).getLevel()));
            put(8, new SimpleIntegerProperty(model.getPropertyAt(8).getLevel()));
            put(9, new SimpleIntegerProperty(model.getPropertyAt(9).getLevel()));
            put(11, new SimpleIntegerProperty(model.getPropertyAt(11).getLevel()));
            put(13, new SimpleIntegerProperty(model.getPropertyAt(13).getLevel()));
            put(14, new SimpleIntegerProperty(model.getPropertyAt(14).getLevel()));
            put(16, new SimpleIntegerProperty(model.getPropertyAt(16).getLevel()));
            put(18, new SimpleIntegerProperty(model.getPropertyAt(18).getLevel()));
            put(19, new SimpleIntegerProperty(model.getPropertyAt(19).getLevel()));
            put(21, new SimpleIntegerProperty(model.getPropertyAt(21).getLevel()));
            put(23, new SimpleIntegerProperty(model.getPropertyAt(23).getLevel()));
            put(24, new SimpleIntegerProperty(model.getPropertyAt(24).getLevel()));
            put(26, new SimpleIntegerProperty(model.getPropertyAt(26).getLevel()));
            put(27, new SimpleIntegerProperty(model.getPropertyAt(27).getLevel()));
            put(29, new SimpleIntegerProperty(model.getPropertyAt(29).getLevel()));
            put(31, new SimpleIntegerProperty(model.getPropertyAt(31).getLevel()));
            put(32, new SimpleIntegerProperty(model.getPropertyAt(32).getLevel()));
            put(34, new SimpleIntegerProperty(model.getPropertyAt(34).getLevel()));
            put(37, new SimpleIntegerProperty(model.getPropertyAt(37).getLevel()));
            put(39, new SimpleIntegerProperty(model.getPropertyAt(39).getLevel()));
        }};
    }

    public int getPlayerMoney(int ind) {
        return playerMoney.get(model.getPlayer(ind)).get();
    }

    public void increasePlayerMoney(int ind, int sum) {
        model.increasePlayerMoney(ind, sum);

        //Update properties
        playerMoney.get(model.getPlayer(ind)).set(model.getPlayerMoney(ind));
    }

    public void reducePlayerMoney(int ind, int sum) {
        model.reducePlayerMoney(ind, sum);

        //Update properties
        playerMoney.get(model.getPlayer(ind)).set(model.getPlayerMoney(ind));
        currentPlayerNameProperty.set(model.getCurrentPlayer().getName());
    }

    public void setPlayerName(int ind, String name) {
        model.setPlayerName(ind, name);

        //Update properties
        playerNames.get(model.getPlayer(ind)).set(name);
    }

    public void moveCurrentPlayer(int value) {
        model.moveCurrentPlayer(value);

        //Update properties
        playerPositions.get(model.getCurrentPlayer()).set(model.getCurrentPlayer().getPosition());
        currentPlayerNameProperty.set(model.getCurrentPlayer().getName());
    }

    public void buyProperty() {
        model.buyProperty();

        //Update properties
        playerProperties.get(model.getCurrentPlayer()).set(FXCollections.observableArrayList(model.getCurrentPlayer().getProperties()));
        playerMoney.get(model.getCurrentPlayer()).set(model.getCurrentPlayer().getMoney());
        bankMoney.set(model.getBankMoney());
        bankHouseCount.set(model.getBankHouseCount());
        bankHotelCount.set(model.getBankHotelCount());
    }

    public DiceResult roll() {
        MultipleDiceResult result = (MultipleDiceResult) model.roll();

        //Update properties
        firstDiceValue.set(result.getResult().get(0));
        secondDiceValue.set(result.getResult().get(1));
        playerDiceRollsLeft.get(model.getCurrentPlayer()).set(model.getCurrentPlayer().getDiceRollsLeft());
        playerPositions.get(model.getCurrentPlayer()).set(model.getCurrentPlayer().getPosition());
        setCurrentPlayerIndex(model.getCurrentPlayerIndex());
        currentPlayerNameProperty.set(model.getCurrentPlayer().getName());
        bankMoney.set(model.getBankMoney());
        bankHouseCount.set(model.getBankHouseCount());
        bankHotelCount.set(model.getBankHotelCount());

        return result;
    }

    public Player getCurrentPlayer() {
        return model.getCurrentPlayer();
    }

    public Player getPlayer(int index) {
        return model.getPlayer(index);
    }

    public Player endTurn() {
        Player player = model.endTurn();

        if (player == null){
            return null;
        }

        //Update properties
        setCurrentPlayerIndex(model.getCurrentPlayerIndex());
        playerDiceRollsLeft.get(player).set(player.getDiceRollsLeft());
        currentPlayerNameProperty.set(model.getCurrentPlayer().getName());

        return player;
    }

    public Field getCurrentPlayersField() {
        return model.getCurrentPlayersField();
    }

    public UpgradeableField getFieldByName(String name) {return model.getFieldByName(name); }

    public boolean isCurrentPlayersFieldBuyable() {
        if (model.getCurrentPlayersField() instanceof monopoly.model.field.Property) {
            return (((monopoly.model.field.Property) model.getCurrentPlayersField()).getOwner() == model.getBank());
        } else {
            return false;
        }
    }

    public boolean isCurrentPlayersFieldChanceCard() {
        if (model.getCurrentPlayersField().getName() == "Chance") {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCurrentPlayersFieldCommunityChest() {
        if (model.getCurrentPlayersField().getName() == "Community Chest") {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCurrentPlayersFieldGoToJail() {
        if (model.getCurrentPlayersField().getName() == "Go To Jail") {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCurrentPlayersFieldIncomeTax() {
        if (model.getCurrentPlayersField().getName() == "Income Tax") {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCurrentPlayersFieldLuxuryTax() {
        if (model.getCurrentPlayersField().getName() == "Luxury Tax") {
            return true;
        } else {
            return false;
        }
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

    public void setFirstDiceValue(int value) {
        firstDiceValue.set(value);
    }

    public void setSecondDiceValue(int value) {
        secondDiceValue.set(value);
    }

    public IntegerProperty getFirstDiceValueProperty() {
        return firstDiceValue;
    }

    public IntegerProperty getSecondDiceValueProperty() {
        return secondDiceValue;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex.get();
    }

    public void setCurrentPlayerIndex(int value) {
        currentPlayerIndex.set(value);
    }

    public IntegerProperty currentPlayerIndexProperty() {
        return currentPlayerIndex;
    }

    public IntegerProperty playerDiceRollsLeftProperty(int index) {
        return playerDiceRollsLeft.get(model.getPlayer(index));
    }

    public String drawChanceCard() {
        String str = model.drawChanceCard();

        //Update properties
        for (int i=0; i<4; i++) {
            playerPositions.get(model.getPlayer(i)).set(model.getPlayer(i).getPosition());
            playerMoney.get(model.getPlayer(i)).set(model.getPlayer(i).getMoney());
        }

        currentPlayerNameProperty.set(model.getCurrentPlayer().getName());
        bankMoney.set(model.getBankMoney());
        bankHouseCount.set(model.getBankHouseCount());
        bankHotelCount.set(model.getBankHotelCount());

        return str;
    }

    public String drawCommunityCard() {
        String str = model.drawCommunityCard();

        //Update properties
        for (int i=0; i<4; i++) {
            playerPositions.get(model.getPlayer(i)).set(model.getPlayer(i).getPosition());
            playerMoney.get(model.getPlayer(i)).set(model.getPlayer(i).getMoney());
        }

        currentPlayerNameProperty.set(model.getCurrentPlayer().getName());
        bankMoney.set(model.getBankMoney());
        bankHouseCount.set(model.getBankHouseCount());
        bankHotelCount.set(model.getBankHotelCount());

        return str;
    }

    public void freeCurrentPlayerFromJail(int type) {
        DiceResult result = model.freeCurrentPlayerFromJail(type);

        //Update properties
        for (int i = 0; i < 4; i++) {
            playerPositions.get(model.getPlayer(i)).set(model.getPlayer(i).getPosition());
            playerMoney.get(model.getPlayer(i)).set(model.getPlayer(i).getMoney());
        }

        if (result != null) {
            firstDiceValue.set(result.getResult().get(0));
            secondDiceValue.set(result.getResult().get(1));
        }

        playerDiceRollsLeft.get(getCurrentPlayer()).set(getCurrentPlayer().getDiceRollsLeft());
        currentPlayerNameProperty.set(model.getCurrentPlayer().getName());

    }

    public StringProperty getCurrentPlayerNameProperty() {
        return currentPlayerNameProperty;
    }

    public IntegerProperty getBankMoneyProperty() {
        return bankMoney;
    }

    public IntegerProperty getBankHouseCountProperty() {
        return bankHouseCount;
    }

    public IntegerProperty getBankHotelCountProperty() {
        return bankHotelCount;
    }

    public void lockPlayerToJail(int ind) {
        model.lockPlayerToJail(ind);
    }

    public IntegerProperty getLevelProperty(int ind) {
        return levelProperties.get(ind);
    }

    public boolean canSellProperty(Property prop) {
        return model.canSellProperty(prop);
    }

    public boolean canUpgradeProperty(Property prop) {
        return model.canUpgradeProperty(prop);
    }

    public boolean canDowngradeProperty(Property prop) {
        return model.canDowngradeProperty(prop);
    }


    public boolean currentPlayerHasEnoughMoney(int upgradePrice) {
        return model.currentPlayerHasEnoughMoney(upgradePrice);
    }

    public boolean canBankGiveBuilding(Property propToUpgrade) {
        return model.canBankGiveBuilding(propToUpgrade);
    }

    public void buyBuilding(Property propToUpgrade) {
        model.buyBuilding(propToUpgrade);

        playerMoney.get(model.getCurrentPlayer()).set(model.getCurrentPlayer().getMoney());
        bankMoney.set(model.getBankMoney());
        bankHouseCount.set(model.getBankHouseCount());
        bankHotelCount.set(model.getBankHotelCount());
        for (Map.Entry<Integer, IntegerProperty> e : levelProperties.entrySet()) {
            levelProperties.get(e.getKey()).setValue(model.getPropertyAt(e.getKey()).getLevel());
        }
    }

    public boolean bankHasEnoughMoney(int val) {
        return model.bankHasEnoughMoney(val);
    }

    public int getBankHouseCount() {
        return model.getBankHouseCount();
    }

    public int getBankHotelCount() {
        return model.getBankHotelCount();
    }

    public void sellBuilding(Property propToDowngrade) {
        model.sellBuilding(propToDowngrade);

        playerMoney.get(model.getCurrentPlayer()).set(model.getCurrentPlayer().getMoney());
        bankMoney.set(model.getBankMoney());
        bankHouseCount.set(model.getBankHouseCount());
        bankHotelCount.set(model.getBankHotelCount());
        for (Map.Entry<Integer, IntegerProperty> e : levelProperties.entrySet()) {
            levelProperties.get(e.getKey()).setValue(model.getPropertyAt(e.getKey()).getLevel());
        }
    }
}
