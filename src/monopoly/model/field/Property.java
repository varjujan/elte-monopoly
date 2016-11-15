package monopoly.model.field;

import monopoly.model.Ownable;
import monopoly.model.Owner;

public class Property extends Field implements Ownable {

    private Owner owner;
    private int defaultPrice;

    public Property(/*Owner owner, */String name, int price) {
        super(name);
        this.defaultPrice = price;
        //this.owner = owner; (Bank)
    }

    public int getDefaultPrice() {
        return this.defaultPrice;
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
}
