package monopoly.model.field;

public abstract class Field {

    private String name;

    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
