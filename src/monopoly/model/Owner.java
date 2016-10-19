package monopoly.model;

import monopoly.model.field.Property;

public interface Owner {

    boolean hasEnoughMoneyFor(int value);
    void increaseMoney(int value);
    void reduceMoney(int value);

    void addProperty(Property property);

}
