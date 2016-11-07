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
        shuffle();
    }

    public void fill (List<Card> activeCommunityCards) {
        this.activeCommunityCards = new ArrayList<>();
        this.activeCommunityCards.addAll(activeCommunityCards);
    }

    public void shuffle() {
        List<Card> cardsToShuffle = new ArrayList<>();
        cardsToShuffle.addAll(usedCommunityCards);
        cardsToShuffle.addAll(activeCommunityCards);
        usedCommunityCards.clear();
        activeCommunityCards.clear();

        Random rand = new Random();

        while(cardsToShuffle.size()>0) {
            int randomInt = rand.nextInt(cardsToShuffle.size());
            Card card = cardsToShuffle.remove(randomInt);
            activeCommunityCards.add(card);
        }
    }

    public Card draw() {
        if (activeCommunityCards.size() == 0) {
            shuffle();
        }
        Card card = activeCommunityCards.remove(0);

        if (card.getId() != 5) {
            usedCommunityCards.add(card);
        }
        return card;
    }
}
