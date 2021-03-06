package monopoly.model.player.changer;

import monopoly.model.dice.MultipleDiceResult;
import monopoly.model.player.Player;
import monopoly.model.player.State;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SequentialPlayerChangerTest {

    @Test(expected = IllegalArgumentException.class)
    public void exceptionWithNoPlayer() {
        List<Player> emptyList = new ArrayList<Player>();
        SequentialPlayerChanger spChanger = new SequentialPlayerChanger(emptyList);
    }

    @Test
    public void nextPlayerWithOnePlayer() {
        Player player = mock(Player.class);
        List<Player> players = mock(List.class);

        when(player.getState()).thenReturn(State.InGame);
        when(players.get(anyInt())).thenReturn(player);
        when(players.size()).thenReturn(1);

        SequentialPlayerChanger spChanger = new SequentialPlayerChanger(players);

        for (int i = 0; i < 5; ++i) {
            assertEquals(player, spChanger.nextPlayer());
            assertEquals(player, spChanger.currentPlayer());
        }

        verify(players, times(25)).get(0);
        verify(player, times(5)).setDiceRollsLeft(anyInt());
    }

    @Test
    public void nextPlayerWithSeveralPlayers() {
        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        List<Player> players = mock(List.class);

        when(players.get(0)).thenReturn(player0);
        when(players.get(1)).thenReturn(player1);
        when(players.get(2)).thenReturn(player2);
        when(players.size()).thenReturn(3);

        when(player0.getState()).thenReturn(State.InJail);
        when(player1.getState()).thenReturn(State.InJail);
        when(player2.getState()).thenReturn(State.InJail);

        SequentialPlayerChanger spChanger = new SequentialPlayerChanger(players);

        assertEquals(player0, spChanger.currentPlayer());
        assertEquals(player1, spChanger.nextPlayer());
        assertEquals(player1, spChanger.currentPlayer());
        assertEquals(player2, spChanger.nextPlayer());
        assertEquals(player2, spChanger.currentPlayer());
        assertEquals(player0, spChanger.nextPlayer());
        assertEquals(player0, spChanger.currentPlayer());
        assertEquals(player1, spChanger.nextPlayer());
        assertEquals(player1, spChanger.currentPlayer());

        verify(players, times(17)).get(anyInt());
        verify(player0, times(0)).setDiceRollsLeft(anyInt());
        verify(player1, times(0)).setDiceRollsLeft(anyInt());
        verify(player2, times(0)).setDiceRollsLeft(anyInt());
    }

    @Test
    public void handleRollWithDifferentDices() {
        Player player = mock(Player.class);
        List<Player> players = mock(List.class);

        MultipleDiceResult mdRes = mock(MultipleDiceResult.class);
        List<Integer> integerList = mock(List.class);
        Iterator<Integer> intListIterator = mock(Iterator.class);

        when(integerList.iterator()).thenReturn(intListIterator);
        when(intListIterator.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(intListIterator.next()).thenReturn(1).thenReturn(2);

        when(mdRes.getResult()).thenReturn(integerList);

        when(players.get(0)).thenReturn(player);
        when(players.size()).thenReturn(1);

        SequentialPlayerChanger spChanger = new SequentialPlayerChanger(players);

        assertEquals(true, spChanger.handleRoll(mdRes));

        verify(player, times(1)).setDiceRollsLeft(0);
        verify(player, times(0)).decreaseDiceRollsLeft();
        verify(player, times(0)).setPosition(anyInt());
    }

    @Test
    public void handleRollWithEqualDices() {
        Player player = mock(Player.class);
        List<Player> players = mock(List.class);

        MultipleDiceResult mdRes = mock(MultipleDiceResult.class);
        List<Integer> integerList = mock(List.class);
        Iterator<Integer> intListIterator = mock(Iterator.class);

        when(integerList.iterator()).thenReturn(intListIterator);
        when(intListIterator.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true).thenReturn(true).thenReturn(false);
        when(intListIterator.next()).thenReturn(1).thenReturn(1).thenReturn(1).thenReturn(1).thenReturn(1).thenReturn(1);

        when(mdRes.getResult()).thenReturn(integerList);

        when(players.get(0)).thenReturn(player);
        when(players.size()).thenReturn(1);

        SequentialPlayerChanger spChanger = new SequentialPlayerChanger(players);

        when(player.getDiceRollsLeft()).thenReturn(2);
        assertEquals(true, spChanger.handleRoll(mdRes));

        when(player.getDiceRollsLeft()).thenReturn(1);
        assertEquals(true, spChanger.handleRoll(mdRes));

        when(player.getDiceRollsLeft()).thenReturn(0);
        assertEquals(false, spChanger.handleRoll(mdRes));

        verify(player, times(3)).decreaseDiceRollsLeft();
        verify(player, times(1)).setPosition(anyInt());
        verify(player, times(1)).setState(anyObject());
    }

    @Test
    public void nextPlayerWithOnlyOneInGamePlayer() {
        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        List<Player> players = mock(List.class);

        when(players.get(0)).thenReturn(player0);
        when(players.get(1)).thenReturn(player1);
        when(players.get(2)).thenReturn(player2);
        when(players.size()).thenReturn(3);

        when(player0.getState()).thenReturn(State.InGame);
        when(player1.getState()).thenReturn(State.InBankruptcy);
        when(player2.getState()).thenReturn(State.InBankruptcy);

        SequentialPlayerChanger spChanger = new SequentialPlayerChanger(players);

        assertEquals(player0, spChanger.currentPlayer());
        assertEquals(player0, spChanger.nextPlayer());
        assertEquals(player0, spChanger.currentPlayer());

        verify(players, times(8)).get(anyInt());
        verify(player0, times(1)).setDiceRollsLeft(anyInt());
        verify(player1, times(0)).setDiceRollsLeft(anyInt());
        verify(player2, times(0)).setDiceRollsLeft(anyInt());
    }

}
