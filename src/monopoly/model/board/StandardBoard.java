package monopoly.model.board;

import monopoly.model.field.Field;
import monopoly.model.field.Property;
import monopoly.model.field.StandardField;

import java.util.HashMap;
import java.util.Map;

public class StandardBoard implements Board {

    private Map<Integer, Field> board;

    @Override
    public Field getFieldAt(int pos) {
        if (!board.containsKey(pos)) {
            throw new IllegalArgumentException("There is no Field at the given position.");
        }

        return board.get(pos);
    }

    public StandardBoard() {
        this.board = new HashMap<>();
        this.board.put(0, new StandardField("Start"));
        this.board.put(1, new Property("Mediterranean Avenue", 60));
        this.board.put(2, new StandardField("Community Chest"));
        this.board.put(3, new Property("Baltic Avenue", 60));
        this.board.put(4, new StandardField("Income Tax"));
        this.board.put(5, new Property("Reading Railroad", 200));
        this.board.put(6, new Property("Oriental Avenue", 100));
        this.board.put(7, new StandardField("Chance"));
        this.board.put(8, new Property("Vermont Avenue", 100));
        this.board.put(9, new Property("Connecticut Avenue", 120));
        this.board.put(10, new StandardField("Jail"));

        this.board.put(11, new Property("St. Charles Place", 140));
        this.board.put(12, new Property("Electric Company", 150));
        this.board.put(13, new Property("States Avenue", 140));
        this.board.put(14, new Property("Virginia Avenue", 160));
        this.board.put(15, new Property("Pennsylvania Railroad", 200));
        this.board.put(16, new Property("ST. James Place", 180));
        this.board.put(17, new StandardField("Community Chest"));
        this.board.put(18, new Property("Tennessee Avenue", 180));
        this.board.put(19, new Property("New York Avenue", 200));
        this.board.put(20, new StandardField("Free Parking"));

        this.board.put(21, new Property("Kentucky Avenue", 220));
        this.board.put(22, new StandardField("Chance"));
        this.board.put(23, new Property("Indiana Avenue", 220));
        this.board.put(24, new Property("Illinois Avenue", 240));
        this.board.put(25, new Property("B. & O. Railroad", 200));
        this.board.put(26, new Property("Atlantic Avenue", 260));
        this.board.put(27, new Property("Ventnor Avenue", 260));
        this.board.put(28, new Property("Water Works", 150));
        this.board.put(29, new Property("Marvin Gardens", 280));
        this.board.put(30, new StandardField("Go To Jail"));

        this.board.put(31, new Property("Pacific Avenue", 300));
        this.board.put(32, new Property("North Carolina Avenue", 300));
        this.board.put(33, new StandardField("Community Chest"));
        this.board.put(34, new Property("Pennsylvania Avenue", 320));
        this.board.put(35, new Property("Short Line", 200));
        this.board.put(36, new StandardField("Chance"));
        this.board.put(37, new Property("Park Place", 350));
        this.board.put(38, new StandardField("Luxury Tax"));
        this.board.put(39, new Property("Board Walk", 400));
    }
}
