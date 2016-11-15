package monopoly.model.deck.card;

import java.util.Random;
import monopoly.model.Owner;
import monopoly.model.board.Board;
import monopoly.model.field.Property;
import monopoly.model.player.Player;
import monopoly.model.player.State;
import monopoly.model.player.changer.PlayerChanger;

public class ChanceCard implements Card{
    private Integer id;
    private String text;
    private PlayerChanger plChanger;
    private Board board;
    private Random random;

    public ChanceCard(int id, PlayerChanger plChanger, Board board, Random random) {
        this.id = id;
        this.plChanger = plChanger;
        this.board = board;
        this.random = random;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void Effect () {
        int playerPosition = plChanger.currentPlayer().getPosition();
        switch (id) {
            case 1:
                text = "Advance to Start!";
                if (playerPosition > 0) {
                    plChanger.currentPlayer().increaseMoney(200);
                }
                plChanger.currentPlayer().setPosition(0);
                break;
            case 2:
                text = "Advance to Illinois Ave.";
                if (playerPosition > 24) {
                    plChanger.currentPlayer().increaseMoney(200);
                }
                plChanger.currentPlayer().setPosition(24);
                break;
            case 3:
                text = "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.";
                Owner utilityOwner;
                if (playerPosition > 28) {
                    utilityOwner = ((Property) board.getFieldAt(12)).getOwner();
                    if (utilityOwner == null || utilityOwner == plChanger.currentPlayer()) {
                        plChanger.currentPlayer().setPosition(12);
                    } else {
                        int diceRollResult = random.nextInt(12) + 1;
                        ((Player) utilityOwner).increaseMoney(Math.min(diceRollResult * 10, plChanger.currentPlayer().getMoney()));
                        plChanger.currentPlayer().reduceMoney(diceRollResult * 10);
                        text += "The Electric Company is owned and you rolled " + (diceRollResult) + ". You pay " + (diceRollResult * 10) + "$ to " + ((Player) utilityOwner).getName() + ".";
                        plChanger.currentPlayer().setPosition(12);
                    }
                } else {
                    utilityOwner = ((Property) board.getFieldAt(28)).getOwner();
                    if (utilityOwner == null || utilityOwner == plChanger.currentPlayer()) {
                        plChanger.currentPlayer().setPosition(28);
                    } else {
                        int diceRollResult = random.nextInt(12) + 1;
                        ((Player) utilityOwner).increaseMoney(Math.min(diceRollResult * 10, plChanger.currentPlayer().getMoney()));
                        plChanger.currentPlayer().reduceMoney(diceRollResult * 10);
                        text += "The Water Works is owned and you rolled " + (diceRollResult) + ". You pay " + (diceRollResult * 10) + "$ to " + ((Player) utilityOwner).getName() + ".";
                        plChanger.currentPlayer().setPosition(28);
                    }
                }
                break;
            case 4:
            case 5:
                text = "Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.";
                Owner propertyOwner;
                if (playerPosition > 35) {
                    propertyOwner = ((Property) board.getFieldAt(5)).getOwner();
                    plChanger.currentPlayer().setPosition(5);
                    if (propertyOwner != null) {
                        //((Player) propertyOwner).increaseMoney(Math.min(plChanger.currentPlayer().getMoney(),((Property) board.getFieldAt(5)).getRent()*2));
                        //plChanger.currentPlayer().reduceMoney( ((Property) board.getFieldAt(5)).getRent()*2);
                    }
                } else if (playerPosition > 25) {
                    propertyOwner = ((Property) board.getFieldAt(35)).getOwner();
                    plChanger.currentPlayer().setPosition(35);
                    if (propertyOwner != null) {
                        //((Player) propertyOwner).increaseMoney(Math.min(plChanger.currentPlayer().getMoney(),((Property) board.getFieldAt(35)).getRent()*2));
                        //plChanger.currentPlayer().reduceMoney( ((Property) board.getFieldAt(35)).getRent()*2);
                    }
                } else if (playerPosition > 15) {
                    propertyOwner = ((Property) board.getFieldAt(25)).getOwner();
                    plChanger.currentPlayer().setPosition(25);
                    if (propertyOwner != null) {
                        //((Player) propertyOwner).increaseMoney(Math.min(plChanger.currentPlayer().getMoney(),((Property) board.getFieldAt(25)).getRent()*2));
                        //plChanger.currentPlayer().reduceMoney( ((Property) board.getFieldAt(25)).getRent()*2);
                    }
                } else {
                    propertyOwner = ((Property) board.getFieldAt(15)).getOwner();
                    plChanger.currentPlayer().setPosition(15);
                    if (propertyOwner != null) {
                        //((Player) propertyOwner).increaseMoney(Math.min(plChanger.currentPlayer().getMoney(),((Property) board.getFieldAt(15)).getRent()*2));
                        //plChanger.currentPlayer().reduceMoney( ((Property) board.getFieldAt(15)).getRent()*2);
                    }
                }
                break;
            case 6:
                text = "Bank pays you dividend of $50.";
                plChanger.currentPlayer().increaseMoney(50);
                break;
            case 7:
                text = "Get out of Jail Free – This card may be kept until needed.";
                plChanger.currentPlayer().addFreeFromJailCard(this);
                break;
            case 8:
                text = "Go Back 3 Spaces";
                plChanger.currentPlayer().setPosition((playerPosition - 3) % 40);
                break;
            case 9:
                text = "Go directly to Jail – Do not pass Go, do not collect $200";
                plChanger.currentPlayer().setPosition(-1);
                plChanger.currentPlayer().setState(State.InJail);
                break;
            case 10:
                text = "Make general repairs on all your property – For each house pay $25 – For each hotel $100";
                int price = 0;
                for (Property property : plChanger.currentPlayer().getProperties()) {
                    //price += property.getLevel() < 5 ? property.getLevel()*25 : 100;
                }
                plChanger.currentPlayer().reduceMoney(price);
                break;
            case 11:
                text = "Pay poor tax of $15";
                plChanger.currentPlayer().reduceMoney(15);
                break;
            case 12:
                text = "Take a trip to Reading Railroad";
                plChanger.currentPlayer().setPosition(5);
                if (playerPosition > 5) {
                    plChanger.currentPlayer().increaseMoney(200);
                }
                break;
            case 13:
                text = "Take a walk on the Boardwalk";
                plChanger.currentPlayer().setPosition(39);
                if (playerPosition > 39) {
                    plChanger.currentPlayer().increaseMoney(200);
                }
                break;
            case 14:
                text = "You have been elected Chairman of the Board – Pay each player $50";
                for (int i = 0; i < 4 && plChanger.currentPlayer().getState() == State.InGame; i++) {
                    if (i != plChanger.currentPlayerIndex() && plChanger.getPlayer(i).getState() == State.InGame) {
                        plChanger.increasePlayerMoney(i, Math.min(plChanger.currentPlayer().getMoney(), 50));
                        plChanger.currentPlayer().reduceMoney(50);
                    }
                }
                break;
            case 15:
                text = "Your building loan matures – Collect $150";
                plChanger.currentPlayer().increaseMoney(150);
                break;
            case 16:
                text = "You have won a crossword competition - Collect $100";
                plChanger.currentPlayer().increaseMoney(100);
                break;
        }
    }
}
