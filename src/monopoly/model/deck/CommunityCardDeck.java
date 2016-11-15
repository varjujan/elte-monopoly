package monopoly.model.deck;

import monopoly.model.deck.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommunityCardDeck implements Deck {

    private List<Card> activeCommunityCards;
    private List<Card> usedCommunityCards = new ArrayList<>();

    public CommunityCardDeck(List<Card> activeCommunityCards) {
        fill(activeCommunityCards);
        shuffle(new Random());
    }

    public void fill (List<Card> activeCommunityCards) {
        this.activeCommunityCards = new ArrayList<>();

        for (int i=0; i<activeCommunityCards.size(); i++) {
            this.activeCommunityCards.add(activeCommunityCards.get(i));
        }
    }

    public void shuffle(Random rand) {
        List<Card> cardsToShuffle = new ArrayList<>();
        cardsToShuffle.addAll(usedCommunityCards);
        cardsToShuffle.addAll(activeCommunityCards);
        usedCommunityCards.clear();
        activeCommunityCards.clear();

        while(cardsToShuffle.size()>0) {
            int randomInt = rand.nextInt(cardsToShuffle.size());
            Card card = cardsToShuffle.remove(randomInt);
            activeCommunityCards.add(card);
        }
    }

    public Card draw() {
        if (activeCommunityCards.size() == 0) {
            shuffle(new Random());
        }
        Card card = activeCommunityCards.remove(0);

        if (card.getId() != 5) {
            usedCommunityCards.add(card);
        }
        return card;
    }

    public void putCardBack (Card c) {
        usedCommunityCards.add(c);
    }

    public Integer getActiveOrUsedCommunityCardsNumber (Cards cs) {
        if (cs == Cards.active)
            return this.activeCommunityCards.size();
        else
            return usedCommunityCards.size();
    }

    public Integer getActiveOrUsedCommunityCardIdAt(Cards cs, int i) {
        if (cs == Cards.active)
            return this.activeCommunityCards.get(i).getId();
        else
            return usedCommunityCards.get(i).getId();
    }
}
