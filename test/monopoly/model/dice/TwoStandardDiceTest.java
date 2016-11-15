package monopoly.model.dice;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TwoStandardDiceTest {

    @Test
    public void roll() {
        StandardDice stdDice1 = mock(StandardDice.class);
        StandardDice stdDice2 = mock(StandardDice.class);
        SingleDiceResult sdRes1 = mock(SingleDiceResult.class);
        SingleDiceResult sdRes2 = mock(SingleDiceResult.class);
        List<Integer> integerList1 = mock(List.class);
        List<Integer> integerList2 = mock(List.class);

        when(integerList1.get(0)).thenReturn(1);
        when(integerList1.toArray()).thenReturn(new Integer[]{1});
        when(integerList2.get(0)).thenReturn(2);
        when(integerList2.toArray()).thenReturn(new Integer[]{2});

        when(sdRes1.getResult()).thenReturn(integerList1);
        when(sdRes2.getResult()).thenReturn(integerList2);
        when(stdDice1.roll()).thenReturn(sdRes1);
        when(stdDice2.roll()).thenReturn(sdRes2);

        Dice twoStdDice = new TwoStandardDices(stdDice1, stdDice2);
        DiceResult result = twoStdDice.roll();

        verify(stdDice1).roll();
        verify(stdDice2).roll();
        assertEquals(1, result.getResult().get(0).intValue());
        assertEquals(2, result.getResult().get(1).intValue());
    }

}
