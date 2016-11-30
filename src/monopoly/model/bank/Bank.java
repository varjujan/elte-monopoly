package monopoly.model.bank;

import monopoly.model.Owner;

public interface Bank extends Owner {
    int getMoney();

    int getHotelCount();

    int getHouseCount();
}
