package monopoly.model.deck.card;

import monopoly.model.board.Board;
import monopoly.model.field.Property;
import monopoly.model.player.Player;
import monopoly.model.player.State;
import monopoly.model.player.changer.PlayerChanger;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ChanceCardTest {
    @Test
    public void Effect () {
        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Player player3 = mock(Player.class);

        PlayerChanger playerChanger = mock(PlayerChanger.class);

        Board board = mock(Board.class);

        Property property1 = mock(Property.class);
        Property property2 = mock(Property.class);

        Random random = mock(Random.class);

        when(random.nextInt(anyInt())).thenReturn(6);

        when(playerChanger.currentPlayer()).thenReturn(player0);
        when(playerChanger.getPlayer(0)).thenReturn(player0);
        when(playerChanger.getPlayer(1)).thenReturn(player1);
        when(playerChanger.getPlayer(2)).thenReturn(player2);
        when(playerChanger.getPlayer(3)).thenReturn(player3);

        when(player0.getMoney()).thenReturn(100);
        when(player1.getMoney()).thenReturn(100);
        when(player2.getMoney()).thenReturn(100);
        when(player3.getMoney()).thenReturn(100);

        when(player0.getState()).thenReturn(State.InGame);
        when(player1.getState()).thenReturn(State.InGame);
        when(player2.getState()).thenReturn(State.InGame);
        when(player3.getState()).thenReturn(State.InGame);

        when(player0.getPosition()).thenReturn(10);
        when(player1.getPosition()).thenReturn(20);
        when(player2.getPosition()).thenReturn(30);
        when(player3.getPosition()).thenReturn(40);

        when(player3.getName()).thenReturn("TestPlayer");

        when(property1.getOwner()).thenReturn(player0);
        when(board.getFieldAt(25)).thenReturn(property1);

        when(property2.getOwner()).thenReturn(player3);
        when(board.getFieldAt(12)).thenReturn(property2);

        ChanceCard chanceCard = new ChanceCard(1, playerChanger, board, random);

        chanceCard.Effect();

        verify(player0, times(1)).setPosition(0);
        verify(player0, times(1)).increaseMoney(200);
        assertEquals("Advance to Start!", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player2);
        when(playerChanger.currentPlayerIndex()).thenReturn(2);
        chanceCard = new ChanceCard(2, playerChanger, board, random);

        chanceCard.Effect();

        verify(player2, times(1)).setPosition(24);
        verify(player2, times(1)).increaseMoney(200);
        assertEquals("Advance to Illinois Ave.", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player3);
        when(playerChanger.currentPlayerIndex()).thenReturn(3);
        chanceCard = new ChanceCard(3, playerChanger, board, random);

        chanceCard.Effect();

        verify(player3, times(1)).setPosition(12);
        assertEquals("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.", chanceCard.getText());

        when(playerChanger.currentPlayer()).thenReturn(player2);
        when(playerChanger.currentPlayerIndex()).thenReturn(2);
        chanceCard = new ChanceCard(3, playerChanger, board, random);

        chanceCard.Effect();

        verify(player2, times(1)).setPosition(12);
        verify(player3, times(1)).increaseMoney(anyInt());
        verify(player2, times(1)).reduceMoney(anyInt());
        assertEquals("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.The Electric Company is owned and you rolled 7. You pay 70$ to TestPlayer.", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player1);
        when(playerChanger.currentPlayerIndex()).thenReturn(1);
        chanceCard = new ChanceCard(4, playerChanger, board, random);

        chanceCard.Effect();

        verify(player1, times(1)).setPosition(25);
        assertEquals("Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player3);
        when(playerChanger.currentPlayerIndex()).thenReturn(3);
        chanceCard = new ChanceCard(6, playerChanger, board, random);

        chanceCard.Effect();

        verify(player3, times(1)).increaseMoney(50);
        assertEquals("Bank pays you dividend of $50.", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player0);
        when(playerChanger.currentPlayerIndex()).thenReturn(0);
        chanceCard = new ChanceCard(7, playerChanger, board, random);

        chanceCard.Effect();

        verify(player0, times(1)).addFreeFromJailCard(chanceCard);
        assertEquals("Get out of Jail Free – This card may be kept until needed.", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player1);
        when(playerChanger.currentPlayerIndex()).thenReturn(1);
        chanceCard = new ChanceCard(8, playerChanger, board, random);

        chanceCard.Effect();

        verify(player1, times(1)).setPosition(17);
        assertEquals("Go Back 3 Spaces", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player2);
        when(playerChanger.currentPlayerIndex()).thenReturn(2);
        chanceCard = new ChanceCard(9, playerChanger, board, random);

        chanceCard.Effect();

        verify(player2, times(1)).setPosition(-1);
        verify(player2, times(1)).setState(State.InJail);
        assertEquals("Go directly to Jail – Do not pass Go, do not collect $200", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player3);
        when(playerChanger.currentPlayerIndex()).thenReturn(3);
        chanceCard = new ChanceCard(10, playerChanger, board, random);

        chanceCard.Effect();

        verify(player3, times(1)).reduceMoney(0);
        assertEquals("Make general repairs on all your property – For each house pay $25 – For each hotel $100", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player0);
        when(playerChanger.currentPlayerIndex()).thenReturn(0);
        chanceCard = new ChanceCard(11, playerChanger, board, random);

        chanceCard.Effect();

        verify(player0, times(1)).reduceMoney(15);
        assertEquals("Pay poor tax of $15", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player0);
        when(playerChanger.currentPlayerIndex()).thenReturn(0);
        chanceCard = new ChanceCard(12, playerChanger, board, random);

        chanceCard.Effect();

        verify(player0, times(1)).setPosition(5);
        verify(player0, times(2)).increaseMoney(200);
        assertEquals("Take a trip to Reading Railroad", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player3);
        when(playerChanger.currentPlayerIndex()).thenReturn(3);
        chanceCard = new ChanceCard(13, playerChanger, board, random);

        chanceCard.Effect();

        verify(player3, times(1)).setPosition(39);
        verify(player3, times(1)).increaseMoney(200);
        assertEquals("Take a walk on the Boardwalk", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player1);
        when(playerChanger.currentPlayerIndex()).thenReturn(1);
        chanceCard = new ChanceCard(14, playerChanger, board, random);

        chanceCard.Effect();

        verify(player1, times(3)).reduceMoney(50);
        verify(playerChanger, times(1)).increasePlayerMoney(0, 50);
        verify(playerChanger, times(1)).increasePlayerMoney(2, 50);
        verify(playerChanger, times(1)).increasePlayerMoney(3, 50);
        assertEquals("You have been elected Chairman of the Board – Pay each player $50", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player2);
        when(playerChanger.currentPlayerIndex()).thenReturn(2);
        chanceCard = new ChanceCard(15, playerChanger, board, random);

        chanceCard.Effect();

        verify(player2, times(1)).increaseMoney(150);
        assertEquals("Your building loan matures – Collect $150", chanceCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player3);
        when(playerChanger.currentPlayerIndex()).thenReturn(3);
        chanceCard = new ChanceCard(16, playerChanger, board, random);

        chanceCard.Effect();

        verify(player3, times(1)).increaseMoney(100);
        assertEquals("You have won a crossword competition - Collect $100", chanceCard.getText());
    }
}
