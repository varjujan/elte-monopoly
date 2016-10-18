package monopoly.model.dice;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import monopoly.util.random.Randomizer;

public class StandardDiceTest {

    @Test
    public void roll() {
        Randomizer randomizer = mock(Randomizer.class);
        Dice dice = new StandardDice(randomizer);

        when(randomizer.random(anyInt(), anyInt())).thenReturn(1);

        int result = dice.roll();

        verify(randomizer).random(1, 6);
        assertEquals(1, result);
    }

}
