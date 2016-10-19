package monopoly.model.player;

import monopoly.model.Owner;
import monopoly.model.field.Property;

import java.util.ArrayList;
import java.util.List;

public class Player implements Owner {

    private String name;
    private int money;
    private int position;
    private State state;
    private List<Property> properties;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.position = 0;
        this.state = State.InGame;
        this.properties = new ArrayList<>();
    }

    @Override
    public boolean hasEnoughMoneyFor(int value) {
        return getMoney() - value >= 0;
    }

    @Override
    public void increaseMoney(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value should be positive.");
        }

        this.money += value;
    }

    @Override
    public void reduceMoney(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value should be positive.");
        }

        this.money -= value;
        if (this.money < 0) {
            this.money = 0;
        }
    }

    @Override
    public void addProperty(Property property) {
        if (property.hasOwner()) {
            throw new IllegalArgumentException("Property is already owned by a player.");
        }

        this.properties.add(property);
        property.setOwner(this);
    }

    public void removeProperty(Property property) {
        if (!this.properties.contains(property)) {
            throw new IllegalArgumentException("Only properties owned by the player should be removed!");
        }

        this.properties.remove(property);
    }

    public void setPosition(int position) {
        if (position < -1 || position > 39) {
            throw new IllegalArgumentException("Position should be in range [-1, 39].");
        }

        this.position = position;
    }

    public void step(int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException("Steps should be positive.");
        }

        position = (position + steps) % 40;
    }

    public int getMoney() {
        return money;
    }

    public int getPosition() {
        return position;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Property> getProperties() {
        return properties;
    }
}
