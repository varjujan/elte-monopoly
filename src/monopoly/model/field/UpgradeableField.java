package monopoly.model.field;

public interface UpgradeableField {
    int getPrice();
    int getHouseCount();
    int getHotelCount();
    int getUpgradePrice();
    int getRentLevel0();
    int getRentLevel1();
    int getRentLevel2();
    int getRentLevel3();
    int getRentLevel4();
    int getRentLevel5();
    String getName();
}
