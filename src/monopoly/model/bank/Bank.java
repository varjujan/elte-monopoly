package monopoly.model.bank;

import monopoly.model.Owner;
import monopoly.model.field.Property;

public interface Bank extends Owner {
    int getMoney();

    int getHotelCount();

    int getHouseCount();

    boolean canGiveBuilding(Property propToUpgrade);

    void addHouse(int count);

    void takeHouse(int count);

    void addHotel(int count);

    void takeHotel(int count);
}
