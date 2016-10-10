package Monopoly.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Janos on 2016.04.04..
 */
public class Model {

    private ArrayList<Player> players;
    private IntegerProperty currentPlayerIndexProperty;
    private List<MonopolyProperty> allMonopolyProperties = new ArrayList<>();
    private Map<Integer, Object> boardElements = new HashMap<Integer, Object>();
    private MonopolyLogger monopolyLogger;
    private MonopolyChat monopolyChat;

    private IntegerProperty firstDiceProperty;
    private IntegerProperty secondDiceProperty;

    private IntegerProperty currentDiceRollsLeft;

    public Model() {
        players = new ArrayList<>();
        players.add(new Player(5000, "Egyeske"));
        players.add(new Player(5000, "Ketteske"));
        players.add(new Player(5000, "Harmaska"));
        players.add(new Player(5000, "Negyeske"));

        currentPlayerIndexProperty = new SimpleIntegerProperty(0);

        firstDiceProperty = new SimpleIntegerProperty(0);
        secondDiceProperty = new SimpleIntegerProperty(0);
    }

    public Model(String player1Name, String player2Name, String player3Name, String player4Name) {
        players = new ArrayList<>();
        players.add(new Player(5000, player1Name));
        players.add(new Player(5000, player2Name));
        players.add(new Player(5000, player3Name));
        players.add(new Player(5000, player4Name));

        //Create properties, one per field
        for (int i = 0; i < 39; i++) {
            allMonopolyProperties.add(new MonopolyProperty("TestProperty " + i, 10, 200));
        }

        fillBoardWithPropertiesAndFields();


        monopolyLogger = new MonopolyLogger();
        monopolyChat = new MonopolyChat();
        currentPlayerIndexProperty = new SimpleIntegerProperty(0);
        monopolyLogger.writeToLogger("Next player: " + getCurrentPlayer().getName());

        firstDiceProperty = new SimpleIntegerProperty(0);
        secondDiceProperty = new SimpleIntegerProperty(0);
    }

    //player money
    public StringProperty moneyTextProperty(int ind) {
        return players.get(ind).moneyTextProperty();
    }

    public int getMoney(int ind) {
        return players.get(ind).getMoney();
    }

    public void setMoney(int ind, int money) {
        players.get(ind).setMoney(money);
    }

    //player name
    public StringProperty nameProperty(int ind) {
        return players.get(ind).nameProperty();
    }

    public String getName(int ind) {
        return players.get(ind).getName();
    }

    public void setName(int ind, String name) {
        players.get(ind).setName(name);
    }

    //player position
    public IntegerProperty positionProperty(int ind) {
        return players.get(ind).positionProperty();
    }

    //player diceRollsLeft
    public IntegerProperty currentDiceRollsLeftProperty() {
        return getCurrentPlayer().diceRollsLeftProperty();
    }

    public int getPosition(int ind) {
        return players.get(ind).getPosition();
    }

    public void setPosition(int ind, int value) {
        players.get(ind).setPosition(value);
    }

    public void step(int ind, int value) {
        players.get(ind).step(value);
    }

    //currentPlayer
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndexProperty.get());
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndexProperty.get();
    }

    public void setCurrentPlayerIndexProperty(int value){
        currentPlayerIndexProperty.setValue(value);
    }

    public IntegerProperty currentPlayerIndexProperty(){
        return currentPlayerIndexProperty;
    }

    public void nextPlayer() {
        do {
            setCurrentPlayerIndexProperty((getCurrentPlayerIndex() + 1) % 4);
        } while (!getCurrentPlayer().getInGame());
        monopolyLogger.writeToLogger("Next player: " + getCurrentPlayer().getName());
    }

    public Player getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    //diceValues
    public IntegerProperty getFirstDiceProperty() {
        return firstDiceProperty;
    }

    public int getFirstDiceValue() {
        return firstDiceProperty.getValue();
    }

    public void setFirstDiceValue(int value) {
        firstDiceProperty.setValue(value);
    }

    public IntegerProperty getSecondDiceProperty() {
        return secondDiceProperty;
    }

    public int getSecondDiceValue() {
        return secondDiceProperty.getValue();
    }

    public void setSecondDiceValue(int value) {
        secondDiceProperty.setValue(value);
    }

    //buyProperty
    public void buyActProperty() {
        getCurrentPlayer().ownNewProperty(allMonopolyProperties.get(getCurrentPlayer().getPosition()));
        monopolyLogger.writeToLogger("Player " + getCurrentPlayer().getName() + " bought property: " + allMonopolyProperties.get(getCurrentPlayer().getPosition()));
    }

    public MonopolyLogger getMonopolyLogger() {
        return monopolyLogger;
    }

    public MonopolyChat getMonopolyChat() {
        return monopolyChat;
    }

    public void saveGame(File file) {
        //TODO: implement save method
    }

    private void fillBoardWithPropertiesAndFields() {
        boardElements.put(0, new Field("Start"));
        boardElements.put(1, new MonopolyProperty("Mediterranean Avenua", 3, 60));
        boardElements.put(2, new Field("Community Chest"));
        boardElements.put(3, new MonopolyProperty("Baltic Avenue", 3, 60));
        boardElements.put(4, new Field("Income Tax"));
        boardElements.put(5, new MonopolyProperty("Reading Railroad", 0, 200));
        boardElements.put(6, new MonopolyProperty("Oriental Avenue", 3, 100));
        boardElements.put(7, new Field("Chance"));
        boardElements.put(8, new MonopolyProperty("Vermont Avenue", 3, 100));
        boardElements.put(9, new MonopolyProperty("Connecticut Avenue", 3, 120));
        boardElements.put(10, new Field("Jail"));

        boardElements.put(11, new MonopolyProperty("St. Charles Place", 3, 140));
        boardElements.put(12, new MonopolyProperty("Electric Company", 0, 150));
        boardElements.put(13, new MonopolyProperty("States Avenue", 3, 140));
        boardElements.put(14, new MonopolyProperty("Virginia Avenue", 3, 160));
        boardElements.put(15, new MonopolyProperty("Pensylvania Raildoad", 0, 200));
        boardElements.put(16, new MonopolyProperty("ST. James Place", 3, 180));
        boardElements.put(17, new Field("Community Chest"));
        boardElements.put(18, new MonopolyProperty("Tenesse Avenue", 3, 180));
        boardElements.put(19, new MonopolyProperty("New York Avenue", 3, 200));
        boardElements.put(20, new Field("Free Parking"));

        boardElements.put(21, new MonopolyProperty("Kentucky Avenue", 3, 220));
        boardElements.put(22, new Field("Chance"));
        boardElements.put(23, new MonopolyProperty("Indiana Avenue", 3, 220));
        boardElements.put(24, new MonopolyProperty("Illinois Avenue", 3, 240));
        boardElements.put(25, new MonopolyProperty("B. & O. Railroad", 0, 200));
        boardElements.put(26, new MonopolyProperty("Atlantic Avenue", 3, 260));
        boardElements.put(27, new MonopolyProperty("Ventnor Avenue", 3, 260));
        boardElements.put(28, new MonopolyProperty("Water Works", 0, 150));
        boardElements.put(29, new MonopolyProperty("Marvin Gardens", 3, 280));
        boardElements.put(30, new Field("Go To Jail"));

        boardElements.put(31, new MonopolyProperty("Pacific Avenue", 3, 300));
        boardElements.put(32, new MonopolyProperty("North Carolina Avenue", 3, 300));
        boardElements.put(33, new Field("Community Chest"));
        boardElements.put(34, new MonopolyProperty("Pensilvania Avenue", 3, 320));
        boardElements.put(35, new MonopolyProperty("Short Line", 0, 200));
        boardElements.put(36, new Field("Chance"));
        boardElements.put(37, new MonopolyProperty("Park Place", 3, 350));
        boardElements.put(38, new Field("Luxury Tax"));
        boardElements.put(39, new MonopolyProperty("Board Walk", 3, 400));

    }

    public Boolean isActFieldProperty() {
        if(boardElements.get(getCurrentPlayer().getPosition()) instanceof MonopolyProperty ){
            return true;
        }
        return false;
    }
}
