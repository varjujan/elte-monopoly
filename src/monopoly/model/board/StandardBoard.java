package monopoly.model.board;

import monopoly.model.field.Field;
import monopoly.model.field.Property;
import monopoly.model.field.StandardField;
import monopoly.model.player.Player;
import monopoly.model.player.State;

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

    @Override
    public Field getFieldAt(Player player) {
        if (player.getState() == State.InBankruptcy) {
            throw new IllegalArgumentException("Player should be still in game.");
        }

        return board.get(player.getPosition());
    }

    public StandardBoard() {
        this.board = new HashMap<>();
        this.board.put(-1, new StandardField("Jail"));
        this.board.put(0, new StandardField("Start"));
        this.board.put(1, new Property(/*Bank, */"Mediterranean Avenue", 60));
        this.board.put(2, new StandardField("Community Chest"));
        this.board.put(3, new Property(/*Bank, */"Baltic Avenue", 60));
        this.board.put(4, new StandardField("Income Tax"));
        this.board.put(5, new Property(/*Bank, */"Reading Railroad", 200));
        this.board.put(6, new Property(/*Bank, */"Oriental Avenue", 100));
        this.board.put(7, new StandardField("Chance"));
        this.board.put(8, new Property(/*Bank, */"Vermont Avenue", 100));
        this.board.put(9, new Property(/*Bank, */"Connecticut Avenue", 120));
        this.board.put(10, new StandardField("Passing by jail"));

        this.board.put(11, new Property(/*Bank, */"St. Charles Place", 140));
        this.board.put(12, new Property(/*Bank, */"Electric Company", 150));
        this.board.put(13, new Property(/*Bank, */"States Avenue", 140));
        this.board.put(14, new Property(/*Bank, */"Virginia Avenue", 160));
        this.board.put(15, new Property(/*Bank, */"Pennsylvania Railroad", 200));
        this.board.put(16, new Property(/*Bank, */"ST. James Place", 180));
        this.board.put(17, new StandardField("Community Chest"));
        this.board.put(18, new Property(/*Bank, */"Tennessee Avenue", 180));
        this.board.put(19, new Property(/*Bank, */"New York Avenue", 200));
        this.board.put(20, new StandardField("Free Parking"));

        this.board.put(21, new Property(/*Bank, */"Kentucky Avenue", 220));
        this.board.put(22, new StandardField("Chance"));
        this.board.put(23, new Property(/*Bank, */"Indiana Avenue", 220));
        this.board.put(24, new Property(/*Bank, */"Illinois Avenue", 240));
        this.board.put(25, new Property(/*Bank, */"B. & O. Railroad", 200));
        this.board.put(26, new Property(/*Bank, */"Atlantic Avenue", 260));
        this.board.put(27, new Property(/*Bank, */"Ventnor Avenue", 260));
        this.board.put(28, new Property(/*Bank, */"Water Works", 150));
        this.board.put(29, new Property(/*Bank, */"Marvin Gardens", 280));
        this.board.put(30, new StandardField("Go To Jail"));

        this.board.put(31, new Property(/*Bank, */"Pacific Avenue", 300));
        this.board.put(32, new Property(/*Bank, */"North Carolina Avenue", 300));
        this.board.put(33, new StandardField("Community Chest"));
        this.board.put(34, new Property(/*Bank, */"Pennsylvania Avenue", 320));
        this.board.put(35, new Property(/*Bank, */"Short Line", 200));
        this.board.put(36, new StandardField("Chance"));
        this.board.put(37, new Property(/*Bank, */"Park Place", 350));
        this.board.put(38, new StandardField("Luxury Tax"));
        this.board.put(39, new Property(/*Bank, */"Board Walk", 400));
    }
}
