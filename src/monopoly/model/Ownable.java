package monopoly.model;

public interface Ownable {

    boolean hasOwner();
    Owner getOwner();
    void setOwner(Owner owner);

}
