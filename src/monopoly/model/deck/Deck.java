package monopoly.model.deck;

import monopoly.model.deck.card.Card;

import java.util.List;
import java.util.Random;

public interface Deck {
    public enum Cards {active, used}

    public void fill(List<Card> activeChanceCards);

    public void shuffle(Random rand);

    public Card draw();

    public void putCardBack (Card c);
}
