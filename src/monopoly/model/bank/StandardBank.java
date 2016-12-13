package monopoly.model.bank;

import monopoly.model.field.Property;

import java.util.ArrayList;
import java.util.List;

public class StandardBank implements Bank {

    private String name = "Bank";
    private int money;
    private int houseCount;
    private int hotelCount;
    private List<Property> properties;

    public StandardBank(int money, int houseCount, int hotelcount) {
        this.money = money;
        this.houseCount = houseCount;
        this.hotelCount = hotelcount;
        this.properties = new ArrayList<>();
    }

    public StandardBank() {
        this.money = 14580;
        this.houseCount = 32;
        this.hotelCount = 12;
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
            throw new RuntimeException("Bank should not go bankrupt.");
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addProperty(Property property) {
        this.properties.add(property);
        property.setOwner(this);
    }

    @Override
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public int getHouseCount() {
        return houseCount;
    }

    @Override
    public boolean canGiveBuilding(Property propToUpgrade) {
        if (propToUpgrade.getLevel() == 4) {
            if (hotelCount > 0) return true;
        } else {
            if (houseCount > 0) return true;
        }

        return false;
    }


    public void setHouseCount(int houseCount) {
        this.houseCount = houseCount;
    }

    @Override
    public int getHotelCount() {
        return hotelCount;
    }

    public void setHotelcount(int hotelCount) {
        this.hotelCount = hotelCount;
    }

    @Override
    public void addHouse(int count) {
        houseCount += count;
    }

    @Override
    public void takeHouse(int count) {
        houseCount -= count;
    }

    @Override
    public void addHotel(int count) {
        hotelCount += count;
    }

    @Override
    public void takeHotel(int count) {
        hotelCount -= count;
    }

}
