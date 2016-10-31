package monopoly.model.dice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TwoStandardDiceTest {

    @Test
    public void addFirstDiceValue() {
        StandardDice stdDice1 = mock(StandardDice.class);
        StandardDice stdDice2 = mock(StandardDice.class);
        SingleDiceResult sdRes1 = mock(SingleDiceResult.class);
        SingleDiceResult sdRes2 = mock(SingleDiceResult.class);

        when(sdRes1.getResult()).thenReturn(1);
        when(sdRes2.getResult()).thenReturn(0);
        when(stdDice1.roll()).thenReturn(sdRes1);
        when(stdDice2.roll()).thenReturn(sdRes2);

        Dice twoStdDice = new TwoStandardDices(stdDice1, stdDice2);
        DiceResult result = twoStdDice.roll();

        verify(stdDice1).roll();
        verify(stdDice2).roll();
        assertEquals(1, ((MultipleDiceResult) result).getResult().get(0).getResult());
        assertEquals(0, ((MultipleDiceResult) result).getResult().get(1).getResult());
    }

    @Test
    public void addSecondDiceValue() {
        StandardDice stdDice1 = mock(StandardDice.class);
        StandardDice stdDice2 = mock(StandardDice.class);
        SingleDiceResult sdRes1 = mock(SingleDiceResult.class);
        SingleDiceResult sdRes2 = mock(SingleDiceResult.class);

        when(sdRes1.getResult()).thenReturn(0);
        when(sdRes2.getResult()).thenReturn(1);
        when(stdDice1.roll()).thenReturn(sdRes1);
        when(stdDice2.roll()).thenReturn(sdRes2);

        Dice twoStdDice = new TwoStandardDices(stdDice1, stdDice2);
        DiceResult result = twoStdDice.roll();

        verify(stdDice1).roll();
        verify(stdDice2).roll();
        assertEquals(0, ((MultipleDiceResult) result).getResult().get(0).getResult());
        assertEquals(1, ((MultipleDiceResult) result).getResult().get(1).getResult());
    }

}
