package monopoly.model.deck;

import monopoly.model.deck.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChanceCardDeck implements Deck{

    private List<Card> activeChanceCards;
    private List<Card> usedChanceCards = new ArrayList<>();

    public ChanceCardDeck(List<Card> activeChanceCards) {
        fill(activeChanceCards);
        shuffle();
    }

    public void fill (List<Card> activeChanceCards) {
        this.activeChanceCards = new ArrayList<>();
        this.activeChanceCards.addAll(activeChanceCards);
    }

    public void shuffle() {
        List<Card> cardsToShuffle = new ArrayList<>();
        cardsToShuffle.addAll(usedChanceCards);
        cardsToShuffle.addAll(activeChanceCards);
        usedChanceCards.clear();
        activeChanceCards.clear();

        Random rand = new Random();

        while(cardsToShuffle.size()>0) {
            int randomInt = rand.nextInt(cardsToShuffle.size());
            Card card = cardsToShuffle.remove(randomInt);
            activeChanceCards.add(card);
        }
    }

    public Card draw() {
        if (activeChanceCards.size() == 0) {
            shuffle();
        }
        Card card = activeChanceCards.remove(0);

        if (card.getId() != 7) {
            usedChanceCards.add(card);
        }
        return card;
    }
}
