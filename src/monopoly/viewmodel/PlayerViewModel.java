package monopoly.viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import monopoly.model.player.Player;

public class PlayerViewModel {

    private Player player;
    private StringProperty name;
    private IntegerProperty money;

    public PlayerViewModel(Player player) {
        this.player = player;
        name = new SimpleStringProperty(player.getName());
        money = new SimpleIntegerProperty(player.getMoney());
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        player.setName(name);
        this.name.setValue(player.getName());
    }

    public IntegerProperty moneyProperty() {
        return money;
    }

    public int getMoney() {
        return money.get();
    }

    public void increaseMoney(int value) {
        player.increaseMoney(value);
        money.setValue(player.getMoney());
    }

    public void reduceMoney(int value) {
        player.reduceMoney(value);
        money.setValue(player.getMoney());
    }

}
