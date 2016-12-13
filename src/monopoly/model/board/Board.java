package monopoly.model.board;

import monopoly.model.field.Field;
import monopoly.model.field.Property;
import monopoly.model.field.UpgradeableField;
import monopoly.model.player.Player;

import java.util.ArrayList;

public interface Board {

    Field getFieldAt(int pos);

    Field getFieldAt(Player player);

    UpgradeableField getFieldByName(String name);

    ArrayList<Property> getPropsFromColorGroup(Property prop);
}
