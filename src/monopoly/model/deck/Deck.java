package monopoly.model.deck;

import monopoly.model.deck.card.Card;

import java.util.List;

public interface Deck {

    public void fill(List<Card> activeChanceCards);

    public void shuffle();

    public Card draw();
}
