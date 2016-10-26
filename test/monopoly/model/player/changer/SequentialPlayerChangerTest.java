package monopoly.model.player.changer;

import monopoly.model.player.Player;
import org.junit.Test;

import java.util.ArrayList;
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

        when(players.get(anyInt())).thenReturn(player);
        when(players.size()).thenReturn(1);

        SequentialPlayerChanger spChanger = new SequentialPlayerChanger(players);

        for (int i = 0; i < 5; ++i) {
            assertEquals(player, spChanger.nextPlayer());
            assertEquals(player, spChanger.currentPlayer());
        }

        verify(players, times(10)).get(0);
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

        verify(players, times(9)).get(anyInt());
    }

}
