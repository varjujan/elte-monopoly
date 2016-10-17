package monopoly.util.random;

import java.util.Random;

public class JavaRandom implements Randomizer {

    private Random rand;

    public JavaRandom() {
        this.rand = new Random();
    }

    @Override
    public int random(int from, int to) {
        return rand.nextInt(to-from+1) + from;
    }
}
