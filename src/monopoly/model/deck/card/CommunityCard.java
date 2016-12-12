package monopoly.model.deck.card;

import monopoly.model.board.Board;
import monopoly.model.field.Property;
import monopoly.model.player.State;
import monopoly.model.player.changer.PlayerChanger;

public class CommunityCard implements Card{
    private Integer id;
    private String text;
    private PlayerChanger plChanger;
    private Board board;

    public CommunityCard(int id, PlayerChanger plChanger, Board board) {
        this.id = id;
        this.plChanger = plChanger;
        this.board = board;
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
                plChanger.currentPlayer().setPosition(0);
                break;
            case 2:
                text = "Bank error in your favor – Collect $200";
                plChanger.currentPlayer().increaseMoney(200);
                break;
            case 3:
                text = "Doctor's fees – Pay $50";
                plChanger.currentPlayer().reduceMoney(50);
                break;
            case 4:
                text = "From sale of stock you get $50";
                plChanger.currentPlayer().increaseMoney(50);
                break;
            case 5:
                text = "Get out of Jail Free – This card may be kept until needed.";
                plChanger.currentPlayer().addFreeFromJailCard(this);
                break;
            case 6:
                text = "Go directly to Jail – Do not pass Go, do not collect $200";
                plChanger.lockPlayerToJail(plChanger.currentPlayerIndex());
                break;
            case 7:
                text = "Grand Opera Night – Collect $50 from every player for opening night seats";
                for (int i=0; i<4; i++) {
                    if (i != plChanger.currentPlayerIndex() && plChanger.getPlayer(i).getState() != State.InBankruptcy ) {
                        plChanger.currentPlayer().increaseMoney(Math.min(plChanger.getPlayer(i).getMoney(),50));
                        plChanger.reducePlayerMoney(i,50);
                    }
                }
                break;
            case 8:
                text = "Holiday Fund matures - Receive $100";
                plChanger.currentPlayer().increaseMoney(100);
                break;
            case 9:
                text = "Income tax refund – Collect $20";
                plChanger.currentPlayer().increaseMoney(20);
                break;
            case 10:
                text = "It is your birthday - Collect $10 from each player";
                for (int i=0; i<4; i++) {
                    if (i != plChanger.currentPlayerIndex() && plChanger.getPlayer(i).getState() != State.InBankruptcy ) {
                        plChanger.currentPlayer().increaseMoney(Math.min(plChanger.getPlayer(i).getMoney(),10));
                        plChanger.reducePlayerMoney(i,10);
                    }
                }
                break;
            case 11:
                text = "Life insurance matures – Collect $100";
                plChanger.currentPlayer().increaseMoney(100);
                break;
            case 12:
                text = "Pay hospital fees of $100";
                plChanger.currentPlayer().reduceMoney(100);
                break;
            case 13:
                text = "Pay school fees of $150";
                plChanger.currentPlayer().reduceMoney(150);
                break;
            case 14:
                text = "Receive $25 consultancy fee";
                plChanger.currentPlayer().increaseMoney(25);
                break;
            case 15:
                text = "You are assessed for street repairs – $40 per house – $115 per hotel";
                int price = 0;
                for (Property property : plChanger.currentPlayer().getProperties()) {
                    //price += property.getLevel() < 5 ? property.getLevel()*40 : 115;
                }
                plChanger.currentPlayer().reduceMoney(price);
                break;
            case 16:
                text = "You have won second prize in a beauty contest – Collect $10";
                plChanger.currentPlayer().increaseMoney(10);
                break;
            case 17:
                text = "You inherit $100";
                plChanger.currentPlayer().increaseMoney(100);
                break;
        }
    }
}