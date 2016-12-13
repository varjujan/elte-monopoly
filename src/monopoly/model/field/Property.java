package monopoly.model.field;

import monopoly.model.Ownable;
import monopoly.model.Owner;


public class Property extends Field implements Ownable, UpgradeableField {

    private Owner owner;
    private int price;

    private int upgradePrice;
    private int maxLevel;
    private int level;

    private int rentLevel0;
    private int rentLevel1;
    private int rentLevel2;
    private int rentLevel3;
    private int rentLevel4;
    private int rentLevel5;

    private ColorGroup color;

    public Property(String name, int upgradePrice, int maxLevel, int price, int rentLevel0, int rentLevel1,
                    int rentLevel2, int rentLevel3, int rentLevel4, int rentLevel5, ColorGroup color) {
        super(name);
        this.price = price;
        this.upgradePrice = upgradePrice;
        this.maxLevel = maxLevel;
        this.level = 0;
        this.price = price;
        this.rentLevel0 = rentLevel0;
        this.rentLevel1 = rentLevel1;
        this.rentLevel2 = rentLevel2;
        this.rentLevel3 = rentLevel3;
        this.rentLevel4 = rentLevel4;
        this.rentLevel5 = rentLevel5;
        this.color = color;
    }

    public Property(Owner owner, String name, int upgradePrice, int maxLevel, int price, int rentLevel0, int rentLevel1,
                    int rentLevel2, int rentLevel3, int rentLevel4, int rentLevel5, ColorGroup color) {
        super(name);
        this.owner = owner;
        this.price = price;
        this.upgradePrice = upgradePrice;
        this.maxLevel = maxLevel;
        this.level = 0;
        this.price = price;
        this.rentLevel0 = rentLevel0;
        this.rentLevel1 = rentLevel1;
        this.rentLevel2 = rentLevel2;
        this.rentLevel3 = rentLevel3;
        this.rentLevel4 = rentLevel4;
        this.rentLevel5 = rentLevel5;
        this.color = color;
    }

    public int getRentValue() {
        if (maxLevel > 0) {
            switch (level) {
                case 0:
                    return rentLevel0;
                case 1:
                    return rentLevel1;
                case 2:
                    return rentLevel2;
                case 3:
                    return rentLevel3;
                case 4:
                    return rentLevel4;
                case 5:
                    return rentLevel5;
            }
        }

        return 0;
    }

    public void upgrade() {
        if (level == 5) return;
        level += 1;
    }

    public void downgrade() {
        if (level == 0) return;
        level -= 1;
    }


    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean hasOwner() {
        return owner != null;
    }

    @Override
    public Owner getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public int getUpgradePrice() {
        return upgradePrice;
    }

    public void setUpgradePrice(int upgradePrice) {
        this.upgradePrice = upgradePrice;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRentLevel0() {
        return rentLevel0;
    }

    public void setRentLevel0(int rentLevel0) {
        this.rentLevel0 = rentLevel0;
    }

    public int getRentLevel1() {
        return rentLevel1;
    }

    public void setRentLevel1(int rentLevel1) {
        this.rentLevel1 = rentLevel1;
    }

    public int getRentLevel2() {
        return rentLevel2;
    }

    public void setRentLevel2(int rentLevel2) {
        this.rentLevel2 = rentLevel2;
    }

    public int getRentLevel3() {
        return rentLevel3;
    }

    public void setRentLevel3(int rentLevel3) {
        this.rentLevel3 = rentLevel3;
    }

    public int getRentLevel4() {
        return rentLevel4;
    }

    public void setRentLevel4(int rentLevel4) {
        this.rentLevel4 = rentLevel4;
    }

    public int getRentLevel5() {
        return rentLevel5;
    }

    public void setRentLevel5(int rentLevel5) {
        this.rentLevel5 = rentLevel5;
    }

    public int getHouseCount(){
        if(level < 5) return level;
        else return 0;
    }

    public int getHotelCount(){
        if(level < 5) return 0;
        else return 1;
    }

    public ColorGroup getColor() {
        return color;
    }

    public void setColor(ColorGroup color) {
        this.color = color;
    }


}
