package Monopoly.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janos on 2016.04.04..
 */
public class Model {

    private ArrayList<Player> players;
    private int currentPlayer; //TODO: should use IntegerProperty instead of int
    private List<MonopolyProperty> allMonopolyProperties = new ArrayList<>();
    private MonopolyLogger monopolyLogger;
    private MonopolyChat monopolyChat;

    private IntegerProperty firstDiceProperty;
    private IntegerProperty secondDiceProperty;

    public Model() {
        players = new ArrayList<Player>();
        players.add(new Player(5000, "Egyeske"));
        players.add(new Player(5000, "Ketteske"));
        players.add(new Player(5000, "Harmaska"));
        players.add(new Player(5000, "Negyeske"));

        currentPlayer = 0;

        firstDiceProperty = new SimpleIntegerProperty(0);
        secondDiceProperty = new SimpleIntegerProperty(0);
    }

    public Model(String player1Name, String player2Name, String player3Name, String player4Name) {
        players = new ArrayList<Player>();
        players.add(new Player(5000, player1Name));
        players.add(new Player(5000, player2Name));
        players.add(new Player(5000, player3Name));
        players.add(new Player(5000, player4Name));

        //Create properties, one per field
        for (int i = 0; i < 39; i++) {
            allMonopolyProperties.add(new MonopolyProperty("TestProperty " + i, 10));
        }

        monopolyLogger = new MonopolyLogger();
        monopolyChat = new MonopolyChat();
        currentPlayer = 0;
        monopolyLogger.writeToLogger("Next player: " + players.get(currentPlayer).getName());

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
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void nextPlayer() {
        do {
            currentPlayer = (currentPlayer + 1) % 4;
        } while (!players.get(currentPlayer).getInGame());
        monopolyLogger.writeToLogger("Next player: " + players.get(currentPlayer).getName());
    }

    public Player getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    //diceValues
    public IntegerProperty getFirstDiceProperty() { return firstDiceProperty; }

    public int getFirstDiceValue() { return firstDiceProperty.getValue(); }

    public void setFirstDiceValue(int value) {
        firstDiceProperty.setValue(value);
    }

    public IntegerProperty getSecondDiceProperty() { return secondDiceProperty; }

    public int getSecondDiceValue() { return secondDiceProperty.getValue(); }

    public void setSecondDiceValue(int value) {
        secondDiceProperty.setValue(value);
    }

    //buyProperty
    public void buyActProperty() {
        players.get(currentPlayer - 1).ownNewProperty(allMonopolyProperties.get(players.get(currentPlayer - 1).getPosition()));
        monopolyLogger.writeToLogger("Player " + players.get(currentPlayer - 1).getName() + " bought property: " + allMonopolyProperties.get(players.get(currentPlayer - 1).getPosition()));
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
}
