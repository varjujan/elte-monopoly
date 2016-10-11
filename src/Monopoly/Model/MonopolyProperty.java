package Monopoly.Model;

public class MonopolyProperty {

    private String propertyName;
    private int prices;
    private int maxSize;
    private int actSize;
    private Player owner;
    private int numberOfHotels;
    private int getNumberOfHouses;
    private int price;


    public MonopolyProperty(String propertyName, int maxSize, int price) {
        this.propertyName = propertyName;
        this.maxSize = maxSize;
        actSize = 0;
        this.price=price;
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

    public int getPrice(){
        return price;
    }
}
