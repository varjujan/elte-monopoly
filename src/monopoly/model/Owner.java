package monopoly.model;

public interface Owner {

    boolean hasEnoughMoneyFor(int value);
    void increaseMoney(int value);
    void reduceMoney(int value);

}
