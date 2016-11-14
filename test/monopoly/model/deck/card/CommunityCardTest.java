package monopoly.model.deck.card;

import monopoly.model.board.Board;
import monopoly.model.player.Player;
import monopoly.model.player.State;
import monopoly.model.player.changer.PlayerChanger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CommunityCardTest {

    @Test
    public void Effect () {
        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Player player3 = mock(Player.class);

        PlayerChanger playerChanger = mock(PlayerChanger.class);

        Board board = mock(Board.class);

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

        CommunityCard communityCard = new CommunityCard(1, playerChanger, board);

        communityCard.Effect();

        verify(player0, times(1)).setPosition(0);
        verify(player0, times(1)).increaseMoney(200);
        assertEquals("Advance to Start!", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player1);
        communityCard = new CommunityCard(2, playerChanger, board);

        communityCard.Effect();

        verify(player1, times(1)).increaseMoney(200);
        assertEquals("Bank error in your favor – Collect $200", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player2);
        communityCard = new CommunityCard(3, playerChanger, board);

        communityCard.Effect();

        verify(player2, times(1)).reduceMoney(50);
        assertEquals("Doctor's fees – Pay $50", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player3);
        communityCard = new CommunityCard(4, playerChanger, board);

        communityCard.Effect();

        verify(player3, times(1)).increaseMoney(50);
        assertEquals("From sale of stock you get $50", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player0);
        communityCard = new CommunityCard(5, playerChanger, board);

        communityCard.Effect();

        verify(player0, times(1)).addFreeFromJailCard(communityCard);
        assertEquals("Get out of Jail Free – This card may be kept until needed.", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player1);
        communityCard = new CommunityCard(6, playerChanger, board);

        communityCard.Effect();

        verify(player1, times(1)).setPosition(-1);
        verify(player1, times(1)).setState(State.InJail);
        assertEquals("Go directly to Jail – Do not pass Go, do not collect $200", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player2);
        when(playerChanger.currentPlayerIndex()).thenReturn(2);
        communityCard = new CommunityCard(7, playerChanger, board);

        communityCard.Effect();

        verify(player2, times(3)).increaseMoney(50);
        verify(playerChanger, times(1)).reducePlayerMoney(0, 50);
        verify(playerChanger, times(1)).reducePlayerMoney(1, 50);
        verify(playerChanger, times(1)).reducePlayerMoney(3, 50);
        assertEquals("Grand Opera Night – Collect $50 from every player for opening night seats", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player3);
        when(playerChanger.currentPlayerIndex()).thenReturn(3);
        communityCard = new CommunityCard(8, playerChanger, board);

        communityCard.Effect();

        verify(player3, times(1)).increaseMoney(100);
        assertEquals("Holiday Fund matures - Receive $100", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player0);
        when(playerChanger.currentPlayerIndex()).thenReturn(0);
        communityCard = new CommunityCard(9, playerChanger, board);

        communityCard.Effect();

        verify(player0, times(1)).increaseMoney(20);
        assertEquals("Income tax refund – Collect $20", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player1);
        when(playerChanger.currentPlayerIndex()).thenReturn(1);
        communityCard = new CommunityCard(10, playerChanger, board);

        communityCard.Effect();

        verify(player1, times(3)).increaseMoney(10);
        verify(playerChanger, times(1)).reducePlayerMoney(0, 10);
        verify(playerChanger, times(1)).reducePlayerMoney(2, 10);
        verify(playerChanger, times(1)).reducePlayerMoney(3, 10);
        assertEquals("It is your birthday - Collect $10 from each player", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player2);
        when(playerChanger.currentPlayerIndex()).thenReturn(2);
        communityCard = new CommunityCard(11, playerChanger, board);

        communityCard.Effect();

        verify(player2, times(1)).increaseMoney(100);
        assertEquals("Life insurance matures – Collect $100", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player3);
        when(playerChanger.currentPlayerIndex()).thenReturn(3);
        communityCard = new CommunityCard(12, playerChanger, board);

        communityCard.Effect();

        verify(player3, times(1)).reduceMoney(100);
        assertEquals("Pay hospital fees of $100", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player0);
        when(playerChanger.currentPlayerIndex()).thenReturn(0);
        communityCard = new CommunityCard(13, playerChanger, board);

        communityCard.Effect();

        verify(player0, times(1)).reduceMoney(150);
        assertEquals("Pay school fees of $150", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player1);
        when(playerChanger.currentPlayerIndex()).thenReturn(1);
        communityCard = new CommunityCard(14, playerChanger, board);

        communityCard.Effect();

        verify(player1, times(1)).increaseMoney(25);
        assertEquals("Receive $25 consultancy fee", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player2);
        when(playerChanger.currentPlayerIndex()).thenReturn(2);
        communityCard = new CommunityCard(16, playerChanger, board);

        communityCard.Effect();

        verify(player2, times(1)).increaseMoney(10);
        assertEquals("You have won second prize in a beauty contest – Collect $10", communityCard.getText());

        //----------------------------------------------------------------------
        when(playerChanger.currentPlayer()).thenReturn(player3);
        when(playerChanger.currentPlayerIndex()).thenReturn(3);
        communityCard = new CommunityCard(17, playerChanger, board);

        communityCard.Effect();

        verify(player3, times(2)).increaseMoney(100);
        assertEquals("You inherit $100", communityCard.getText());
    }
}
