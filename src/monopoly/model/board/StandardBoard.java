package monopoly.model.board;

import monopoly.model.bank.Bank;
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

    public StandardBoard(Bank bank) {
        this.board = new HashMap<>();
        this.board.put(-1, new StandardField("Jail"));
        this.board.put(0, new StandardField("Start"));
        this.board.put(1, new Property(bank, "Mediterranean Avenue", 50, 5, 60, 2, 10, 30, 90, 160, 250));
        this.board.put(2, new StandardField("Community Chest"));
        this.board.put(3, new Property(bank, "Baltic Avenue", 50, 5, 60, 4, 20, 60, 180, 320, 450));
        this.board.put(4, new StandardField("Income Tax"));
        this.board.put(5, new Property(bank, "Reading Railroad", -1, 0, 200, -1, -1, -1, -1, -1, -1));
        this.board.put(6, new Property(bank, "Oriental Avenue", 50, 5, 100, 6, 30, 90, 270, 400, 550));
        this.board.put(7, new StandardField("Chance"));
        this.board.put(8, new Property(bank, "Vermont Avenue", 50, 5, 100, 6, 30, 90, 270, 400, 550));
        this.board.put(9, new Property(bank, "Connecticut Avenue", 50, 5, 120, 8, 40, 100, 300, 450, 600));
        this.board.put(10, new StandardField("Passing by jail"));

        this.board.put(11, new Property(bank, "St. Charles Place", 100, 5, 140, 10, 50, 150, 450, 625, 750));
        this.board.put(12, new Property(bank, "Electric Company", -1, 0, 150, -1, -1, -1, -1, -1, -1));
        this.board.put(13, new Property(bank, "States Avenue", 100, 5, 140, 10, 50, 150, 450, 625, 750));
        this.board.put(14, new Property(bank, "Virginia Avenue", 100, 5, 160, 12, 60, 180, 500, 700, 900));
        this.board.put(15, new Property(bank, "Pennsylvania Railroad", -1, 0, 200, -1, -1, -1, -1, -1, -1));
        this.board.put(16, new Property(bank, "St. James Place", 100, 5, 180, 14, 70, 200, 550, 750, 950));
        this.board.put(17, new StandardField("Community Chest"));
        this.board.put(18, new Property(bank, "Tennessee Avenue", 100, 5, 180, 14, 70, 200, 550, 750, 950));
        this.board.put(19, new Property(bank, "New York Avenue", 100, 5, 200, 16, 80, 220, 600, 800, 1000));
        this.board.put(20, new StandardField("Free Parking"));

        this.board.put(21, new Property(bank, "Kentucky Avenue", 150, 5, 220, 18, 90, 250, 700, 875, 1050));
        this.board.put(22, new StandardField("Chance"));
        this.board.put(23, new Property(bank, "Indiana Avenue", 150, 5, 220, 18, 90, 250, 700, 875, 1050));
        this.board.put(24, new Property(bank, "Illinois Avenue", 150, 5, 220, 20, 100, 300, 750, 925, 1100));
        this.board.put(25, new Property(bank, "B. & O. Railroad", -1, 0, 200, -1, -1, -1, -1, -1, -1));
        this.board.put(26, new Property(bank, "Atlantic Avenue", 150, 5, 260, 22, 110, 330, 800, 975, 1150));
        this.board.put(27, new Property(bank, "Ventnor Avenue", 150, 5, 260, 22, 110, 330, 800, 975, 1150));
        this.board.put(28, new Property(bank, "Water Works", -1, 0, 150, -1, -1, -1, -1, -1, -1));
        this.board.put(29, new Property(bank, "Marvin Gardens", 150, 5, 280, 24, 120, 360, 850, 1025, 1200));
        this.board.put(30, new StandardField("Go To Jail"));

        this.board.put(31, new Property(bank, "Pacific Avenue", 200, 5, 300, 26, 130, 390, 900, 1100, 1275));
        this.board.put(32, new Property(bank, "North Carolina Avenue", 200, 5, 300, 26, 130, 390, 900, 1100, 1275));
        this.board.put(33, new StandardField("Community Chest"));
        this.board.put(34, new Property(bank, "Pennsylvania Avenue", 200, 5, 320, 28, 150, 450, 1000, 1200, 1400));
        this.board.put(35, new Property(bank, "Short Line", -1, 0, 200, -1, -1, -1, -1, -1, -1));
        this.board.put(36, new StandardField("Chance"));
        this.board.put(37, new Property(bank, "Park Place", 200, 5, 350, 35, 175, 500, 1100, 1300, 1500));
        this.board.put(38, new StandardField("Luxury Tax"));
        this.board.put(39, new Property(bank, "Board Walk", 200, 5, 400, 50, 200, 600, 1400, 1700, 2000));

        board.entrySet().stream().filter(m -> m.getValue() instanceof Property).forEach(m -> bank.addProperty((Property) m.getValue()));
    }
}
