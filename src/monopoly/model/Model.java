package monopoly.model;

import monopoly.model.bank.Bank;
import monopoly.model.board.Board;
import monopoly.model.deck.Deck;
import monopoly.model.deck.card.Card;
import monopoly.model.dice.Dice;
import monopoly.model.dice.DiceResult;
import monopoly.model.field.Field;
import monopoly.model.field.Property;
import monopoly.model.field.UpgradeableField;
import monopoly.model.player.Player;
import monopoly.model.player.State;
import monopoly.model.player.changer.PlayerChanger;
import monopoly.util.logger.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Model {

    private Board board;
    private Dice dice;
    private PlayerChanger playerChanger;
    private Logger logger;
    private Deck chanceCardDeck;
    private Deck communityCardDeck;
    private Bank bank;

    public Model(
            Board board,
            Dice dice,
            PlayerChanger playerChanger,
            Logger logger,
            Deck chanceCardDeck,
            Deck communityCardDeck,
            Bank bank) {
        this.board = board;
        this.dice = dice;
        this.playerChanger = playerChanger;
        this.logger = logger;
        this.chanceCardDeck = chanceCardDeck;
        this.communityCardDeck = communityCardDeck;
        this.bank = bank;
    }

    public void movePlayer(Player player, int steps) {
        if (player.isInJail()) return;
        player.step(steps);

        logger.info(String.format("%s player moved %d field(s).", player.getName(), steps));
    }

    public void moveCurrentPlayer(int value) {
        Player player = this.getCurrentPlayer();
        movePlayer(player, value);
    }

    public Player endTurn() {
        Player currentPlayer = playerChanger.currentPlayer();
        Player nextPlayer = playerChanger.nextPlayer();
        logger.info(String.format("%s's turn.", nextPlayer));
        if (currentPlayer == nextPlayer) {
            logger.info(String.format("%s won the Game!", nextPlayer));
            return null;
        }

        return nextPlayer;
    }

    public DiceResult roll() {

        DiceResult result = dice.roll();

        boolean success = playerChanger.handleRoll(result);

        if (success) {
            logger.info(String.format("%s rolled %d + %d.", playerChanger.getName(playerChanger.currentPlayerIndex()), result.getResult().get(0), result.getResult().get(1)));
            return result;
        } else {
            logger.info(String.format("%s got jailed.", playerChanger.getName(playerChanger.currentPlayerIndex())));
            //playerChanger.nextPlayer();
            return result;
        }

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
        owner.addProperty(property);
    }

    public void buyProperty() {
        Player player = this.getCurrentPlayer();
        Field field = board.getFieldAt(player.getPosition());

        if (!(field instanceof Property)) {
            throw new RuntimeException("Cannot bought a not ownable field.");
        }

        Property property = (Property) field;
        buyProperty(player, property, property.getPrice());
    }

    public Player getCurrentPlayer() {
        return playerChanger.currentPlayer();
    }

    public int getCurrentPlayerIndex() {
        return playerChanger.currentPlayerIndex();
    }

    public Field getCurrentPlayersField() {
        return board.getFieldAt(getCurrentPlayer());
    }

    public UpgradeableField getFieldByName(String name) {return board.getFieldByName(name); }

    public Property getPropertyAt(int ind) {
        return (Property) board.getFieldAt(ind);
    }

    public int getPlayerMoney(int ind) {
        return playerChanger.getPlayerMoney(ind);
    }

    public void increasePlayerMoney(int ind, int sum) {
        playerChanger.increasePlayerMoney(ind, sum);
    }

    public void reducePlayerMoney(int ind, int sum) {
        playerChanger.reducePlayerMoney(ind, sum);
    }

    public void setPlayerName(int ind, String name) {
        playerChanger.setName(name, ind);
    }

    public String getPlayerName(int ind) {
        return playerChanger.getName(ind);
    }

    public Player getPlayer(int ind) {
        return playerChanger.getPlayer(ind);
    }

    public int getPlayerPosition(int ind) {
        return playerChanger.getPlayer(ind).getPosition();
    }

    public String drawChanceCard()  {
        Card card = chanceCardDeck.draw();
        card.Effect();
        return card.getText();
    }

    public String drawCommunityCard()  {
        Card card = communityCardDeck.draw();
        card.Effect();
        return card.getText();
    }

    public DiceResult freeCurrentPlayerFromJail(int type) {

        boolean success = false;
        DiceResult result = null;
        switch (type) {
            case 1:
                Card card = getCurrentPlayer().useFreeFromJailCard();
                if (card.getId() == 5) {
                    communityCardDeck.putCardBack(card);
                } else if (card.getId() == 7) {
                    chanceCardDeck.putCardBack(card);
                }
                break;
            case 2:
                getCurrentPlayer().reduceMoney(50);
                break;
            case 3:
                result = dice.roll();
                Set<Integer> uniqueValues = new HashSet<>(result.getResult());
                success = (uniqueValues.size() == 1);
                break;
        }

        if ((type != 3 || success) && (getCurrentPlayer().getState() != State.InBankruptcy)) {
            getCurrentPlayer().setPosition(10);
            getCurrentPlayer().setState(State.InGame);
            getCurrentPlayer().setDiceRollsLeft(3);
            getCurrentPlayer().setTurnsLeftInJail(0);
        }

        return result;
    }

    public int getBankMoney() {
        return bank.getMoney();
    }

    public int getBankHouseCount() {
        return bank.getHouseCount();
    }

    public int getBankHotelCount() {
        return bank.getHotelCount();
    }

    public Bank getBank() {
        return bank;
    }

    public void lockPlayerToJail(int ind) {
        playerChanger.lockPlayerToJail(ind);
    }

    public boolean canSellProperty(Property prop) {
        if (prop.getLevel() > 0) return false;
        ArrayList<Property> propsFromSameColorGroup = board.getPropsFromColorGroup(prop);

        for (Property p : propsFromSameColorGroup) {
            if (p.getLevel() > 0) return false;
        }

        return true;
    }

    public boolean canUpgradeProperty(Property propToUpgrade) {
        if (propToUpgrade.getLevel() == 5) return false;
        ArrayList<Property> propsFromSameColorGroup = board.getPropsFromColorGroup(propToUpgrade);

        for (Property prop : propsFromSameColorGroup) {
            if (prop.getLevel() < propToUpgrade.getLevel()) return false;
            if (prop.getOwner() == null) return false;
            if (prop.getOwner() != null && !prop.getOwner().getName().equals(propToUpgrade.getOwner().getName()))
                return false;
        }

        return true;
    }

    public boolean canDowngradeProperty(Property propToDowngrade) {
        if (propToDowngrade.getLevel() == 0) return false;
        ArrayList<Property> propsFromSameColorGroup = board.getPropsFromColorGroup(propToDowngrade);

        for (Property prop : propsFromSameColorGroup) {
            if (prop.getLevel() > propToDowngrade.getLevel()) return false;
        }

        return true;
    }

    public boolean currentPlayerHasEnoughMoney(int upgradePrice) {
        return playerChanger.currentPlayer().hasEnoughMoneyFor(upgradePrice);
    }

    public boolean canBankGiveBuilding(Property propToUpgrade) {
        return bank.canGiveBuilding(propToUpgrade);
    }

    public void buyBuilding(Property propToUpgrade) {
        propToUpgrade.upgrade();
        getCurrentPlayer().reduceMoney(propToUpgrade.getUpgradePrice());
        bank.increaseMoney(propToUpgrade.getUpgradePrice());

        if (propToUpgrade.getLevel() == 5) {
            bank.addHouse(4);
            bank.takeHotel(1);
        } else {
            bank.takeHouse(1);
        }
    }

    public boolean bankHasEnoughMoney(int val) {
        return getBank().hasEnoughMoneyFor(val);
    }

    public void sellBuilding(Property propToDowngrade) {
        propToDowngrade.downgrade();
        if (propToDowngrade.getLevel() == 4) {
            getCurrentPlayer().increaseMoney(propToDowngrade.getUpgradePrice() * 5 / 2);
            bank.reduceMoney(propToDowngrade.getUpgradePrice() * 5 / 2);
        } else {
            getCurrentPlayer().increaseMoney(propToDowngrade.getUpgradePrice() / 2);
            bank.reduceMoney(propToDowngrade.getUpgradePrice() / 2);
        }

        if (propToDowngrade.getLevel() == 4) {
            bank.addHotel(1);
            bank.takeHouse(4);
        } else {
            bank.addHouse(1);
        }

    }
}
