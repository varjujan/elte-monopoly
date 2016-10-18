package monopoly.model.player;

import monopoly.model.Owner;

public class Player implements Owner {

    private String name;
    private int money;
    private State state;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.state = State.InGame;
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

    public int getMoney() {
        return money;
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
}
