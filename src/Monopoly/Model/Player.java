package Monopoly.Model;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janos on 2016.04.04..
 */
public class Player {
    private IntegerProperty moneyProperty;
    private StringProperty moneyTextProperty;

    private StringProperty nameProperty;
    private IntegerProperty positionProperty;

    private boolean inGame;

    private ListProperty<MonopolyProperty> monopolyPropertiesObserveble = new <MonopolyProperty>SimpleListProperty();
    private List<MonopolyProperty> monopolyProperties = new ArrayList<>();


    public Player(int money, String name) {
        moneyProperty = new SimpleIntegerProperty(money);
        moneyProperty.addListener((observableValue, number, number2) -> {
            int value = moneyProperty.get();
            if (Integer.toString(value).equals(moneyTextProperty.get())) return;
            moneyTextProperty.set(Integer.toString(value));
        });

        moneyTextProperty = new SimpleStringProperty(Integer.toString(money));
        moneyTextProperty.addListener((observableValue, s, s2) -> {
            try {
                int parsedValue = Integer.parseInt(observableValue.getValue());
                if (parsedValue == moneyProperty.get()) return;
                moneyProperty().set(parsedValue);
            } catch (NumberFormatException e) {
                // too bad
            }
        });

        nameProperty = new SimpleStringProperty(name);

        positionProperty = new SimpleIntegerProperty(0);

        inGame = true;

    }

    //moneyProperty getter/setter
    public int getMoney() {
        return moneyProperty.get();
    }

    public void setMoney(int money) {
        moneyProperty.set(money);
    }

    public IntegerProperty moneyProperty() {
        return moneyProperty;
    }

    //moneyTextProperty getter/setter
    public String getMoneyText() {
        return moneyTextProperty.get();
    }

    public void setMoneyText(String moneyText) {
        moneyTextProperty.set(moneyText);
    }

    public StringProperty moneyTextProperty() {
        return moneyTextProperty;
    }

    //nameProperty getter/setter
    public String getName() {
        return nameProperty.get();
    }

    public void setName(String name) {
        nameProperty.set(name);
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    //positionProperty getter/setter
    public int getPosition() {
        return positionProperty.get();
    }

    public void setPosition(int position) {
        if (position < 40) {
            positionProperty.set(position);
        }
    }

    public void step(int value) {
        positionProperty.set((getPosition() + value) % 40);
    }

    public IntegerProperty positionProperty() {
        return positionProperty;
    }

    //inGame getter/setter
    public boolean getInGame() {
        return inGame;
    }

    public void setInGame(boolean value) {
        inGame = value;
    }

    public ListProperty<MonopolyProperty> getMonopolyProperties() {
        return monopolyPropertiesObserveble;
    }

    //Add a property to the player (not necessary in the final version)
    public void ownNewProperty(MonopolyProperty name) {
        monopolyProperties.add(name);
        monopolyPropertiesObserveble.set(FXCollections.observableArrayList(monopolyProperties));
    }
}
