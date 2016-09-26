package Monopoly.Model;

/**
 * Created by zoltankis on 2016. 04. 25..
 */
public class MonopolyProperty {

    private String propertyName;
    private int maxSize;
    private int actSize;
    private Player owner;
    private int numberOfHotels;
    private int getNumberOfHouses;


    public MonopolyProperty(String propertyName, int maxSize) {
        this.propertyName = propertyName;
        this.maxSize = maxSize;
        actSize = 0;
    }

    public int calculateRent() {
        //TODO
        return 0;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return propertyName;
    }
}
