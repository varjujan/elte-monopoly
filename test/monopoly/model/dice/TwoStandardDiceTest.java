package monopoly.model.dice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TwoStandardDiceTest {

    @Test
    public void addFirstDiceValue() {
        StandardDice stdDice1 = mock(StandardDice.class);
        StandardDice stdDice2 = mock(StandardDice.class);

        when(stdDice1.roll()).thenReturn(1);
        when(stdDice2.roll()).thenReturn(0);

        Dice twoStdDice = new TwoStandardDices(stdDice1, stdDice2);
        int result = twoStdDice.roll();

        verify(stdDice1).roll();
        verify(stdDice2).roll();
        assertEquals(1, result);
    }

    @Test
    public void addSecondDiceValue() {
        StandardDice stdDice1 = mock(StandardDice.class);
        StandardDice stdDice2 = mock(StandardDice.class);

        when(stdDice1.roll()).thenReturn(0);
        when(stdDice2.roll()).thenReturn(1);

        Dice twoStdDice = new TwoStandardDices(stdDice1, stdDice2);
        int result = twoStdDice.roll();

        verify(stdDice1).roll();
        verify(stdDice2).roll();
        assertEquals(1, result);
    }

}
