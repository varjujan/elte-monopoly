package monopoly.model;

import monopoly.model.board.Board;
import monopoly.model.dice.Dice;
import monopoly.model.field.Field;
import monopoly.model.field.Property;
import monopoly.model.player.Player;
import monopoly.model.player.changer.PlayerChanger;
import monopoly.util.logger.Logger;

public class Model {

    private Board board;
    private Dice dice; //TODO: Where should I implement the dice rolling?
    private PlayerChanger playerChanger;
    private Logger logger;

    public Model(
            Board board,
            Dice dice,
            PlayerChanger playerChanger,
            Logger logger) {
        this.board = board;
        this.dice = dice;
        this.playerChanger = playerChanger;
        this.logger = logger;
    }

    public void movePlayer(Player player, int steps) {
        board.stepPlayer(player, steps);
        logger.info(String.format("%s player moved %d field(s).", player.getName(), steps));
    }

    public void movePlayer(int value) {
        Player player = this.getCurrentPlayer();
        movePlayer(player, value);
    }

    public void endTurn() {
        Player nextPlayer = playerChanger.next();
        logger.info(String.format("%s's turn.", nextPlayer));
    }

    public void buyProperty(Owner owner, Property property, int price) {
        if (!owner.hasEnoughMoneyFor(price)) {
            throw new RuntimeException(String.format(
                    "%s has no enough money to buy %s for %d.", owner, property, price));
        }

        logger.info(String.format("%s bought %s.", owner, property));
        Owner oldOwner = property.getOwner();
        if (oldOwner != null) {
            oldOwner.increaseMoney(price);
        }
        owner.reduceMoney(price);
        property.setOwner(owner);
    }

    public void buyProperty() {
        Player player = this.getCurrentPlayer();
        Field field = board.getPlayerPos(player);

        if (!(field instanceof Property)) {
            throw new RuntimeException("Cannot bought a not ownable field.");
        }

        Property property = (Property) field;
        buyProperty(player, property, property.getDefaultPrice());
    }

    public Player getCurrentPlayer() {
        return playerChanger.current();
    }

    //public int getPlayerMoney(int ind) { return playerChanger.getPlayerMoney(ind); }

    //public void increasePlayerMoney(int ind, int sum) { playerChanger.increasePlayerMoney(ind, sum); }

    //public void reducePlayerMoney(int ind, int sum) { playerChanger.reducePlayerMoney(ind, sum); }

    public Player getPlayer(int ind) {
        return playerChanger.getPlayer(ind);
    }
}
