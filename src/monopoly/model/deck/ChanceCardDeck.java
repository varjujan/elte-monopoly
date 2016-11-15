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
        shuffle(new Random());
    }

    public void fill (List<Card> activeChanceCards) {
        this.activeChanceCards = new ArrayList<>();

        for (int i=0; i<activeChanceCards.size(); i++) {
            this.activeChanceCards.add(activeChanceCards.get(i));
        }
    }

    public void shuffle(Random rand) {
        List<Card> cardsToShuffle = new ArrayList<>();
        cardsToShuffle.addAll(usedChanceCards);
        cardsToShuffle.addAll(activeChanceCards);
        usedChanceCards.clear();
        activeChanceCards.clear();

        while(cardsToShuffle.size()>0) {
            int randomInt = rand.nextInt(cardsToShuffle.size());
            Card card = cardsToShuffle.remove(randomInt);
            activeChanceCards.add(card);
        }
    }

    public Card draw() {
        if (activeChanceCards.size() == 0) {
            shuffle(new Random());
        }
        Card card = activeChanceCards.remove(0);

        if (card.getId() != 7) {
            usedChanceCards.add(card);
        }
        return card;
    }

    public void putCardBack (Card c) {
        usedChanceCards.add(c);
    }

    public Integer getActiveOrUsedChanceCardsNumber (Cards cs) {
        if (cs == Cards.active)
            return this.activeChanceCards.size();
        else
            return usedChanceCards.size();
    }

    public Integer getActiveOrUsedChanceCardIdAt(Cards cs, int i) {
        if (cs == Cards.active)
            return this.activeChanceCards.get(i).getId();
        else
            return usedChanceCards.get(i).getId();
    }
}
