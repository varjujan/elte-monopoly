package monopoly.util.random;

import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class JavaRandomTest {

    @Test
    public void randomWithOneElementInterval() {
        Randomizer randomizer = new JavaRandom();

        final int generatedNumbers = 100;
        Stream<Integer> result = Stream.generate(() -> randomizer.random(1, 1))
                .limit(generatedNumbers);

        assertTrue(result.allMatch(i -> i.equals(1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void randomWithZeroElementInterval() {
        Randomizer randomizer = new JavaRandom();
        randomizer.random(1, 0);
    }

    @Test
    public void randomWithSeveralElementInterval() {
        Randomizer randomizer = new JavaRandom();

        final int generatedNumbers = 100;
        int sum = Stream.generate(() -> randomizer.random(1, 6))
                .limit(generatedNumbers)
                .reduce(0, (acc, e) -> acc + e);

        double avg = sum/generatedNumbers;

        assertTrue(avg == 3.0);
    }
}
