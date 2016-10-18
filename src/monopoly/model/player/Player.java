package monopoly.model.player;

import monopoly.model.Owner;

public class Player implements Owner {

    private String name;
    private int money;
    private int position;
    private State state;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.position = 0;
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

    public int getPosition() {
        return position;
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
