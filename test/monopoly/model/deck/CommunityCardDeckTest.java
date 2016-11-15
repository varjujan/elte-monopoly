package monopoly.model.deck;

import monopoly.model.deck.card.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CommunityCardDeckTest {
    @Test
    public void fill () {
        List<Card> activeCommunityCards = mock(List.class);
        Card cardOne = mock(Card.class);
        Card cardTwo = mock(Card.class);

        when(cardOne.getId()).thenReturn(8);
        when(cardTwo.getId()).thenReturn(9);

        when(activeCommunityCards.get(0)).thenReturn(cardOne);
        when(activeCommunityCards.get(1)).thenReturn(cardTwo);
        when(activeCommunityCards.size()).thenReturn(2);

        CommunityCardDeck communityCardDeck = new CommunityCardDeck(new ArrayList<>());

        communityCardDeck.fill(activeCommunityCards);
        assertEquals(2, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.active).intValue());
        assertEquals(0, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.used).intValue());
        assertEquals(cardOne.getId(), communityCardDeck.getActiveOrUsedCommunityCardIdAt(CommunityCardDeck.Cards.active, 0));
        assertEquals(cardTwo.getId(), communityCardDeck.getActiveOrUsedCommunityCardIdAt(CommunityCardDeck.Cards.active, 1));
    }

    @Test
    public void draw () {
        List<Card> activeCommunityCards = mock(List.class);
        Card cardOne = mock(Card.class);
        Card cardTwo = mock(Card.class);

        when(cardOne.getId()).thenReturn(8);
        when(cardTwo.getId()).thenReturn(5);

        when(activeCommunityCards.get(0)).thenReturn(cardOne);
        when(activeCommunityCards.get(1)).thenReturn(cardTwo);
        when(activeCommunityCards.size()).thenReturn(2);

        CommunityCardDeck communityCardDeck = new CommunityCardDeck(new ArrayList<>());

        communityCardDeck.fill(activeCommunityCards);

        assertEquals(cardOne.getId(), communityCardDeck.draw().getId());

        assertEquals(1, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.active).intValue());
        assertEquals(1, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.used).intValue());

        assertEquals(cardTwo.getId(), communityCardDeck.draw().getId());
        assertEquals(0, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.active).intValue());
        assertEquals(1, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.used).intValue());
    }

    @Test
    public void putCardBack () {
        Card cardOne = mock(Card.class);

        when(cardOne.getId()).thenReturn(8);

        CommunityCardDeck communityCardDeck = new CommunityCardDeck(new ArrayList<>());

        communityCardDeck.putCardBack(cardOne);

        assertEquals(cardOne.getId(),communityCardDeck.getActiveOrUsedCommunityCardIdAt(CommunityCardDeck.Cards.used, 0));

        assertEquals(0, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.active).intValue());
        assertEquals(1, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.used).intValue());
    }

    @Test
    public void shuffle () {
        List<Card> activeCommunityCards = mock(List.class);
        Card cardOne = mock(Card.class);
        Card cardTwo = mock(Card.class);
        Card cardThree = mock(Card.class);
        Random rand = mock(Random.class);

        when(cardOne.getId()).thenReturn(8);
        when(cardTwo.getId()).thenReturn(9);
        when(cardThree.getId()).thenReturn(10);

        when(activeCommunityCards.get(0)).thenReturn(cardOne);
        when(activeCommunityCards.get(1)).thenReturn(cardTwo);
        when(activeCommunityCards.get(2)).thenReturn(cardThree);
        when(activeCommunityCards.size()).thenReturn(3);

        when(rand.nextInt(anyInt())).thenReturn(1).thenReturn(1).thenReturn(0);

        CommunityCardDeck communityCardDeck = new CommunityCardDeck(new ArrayList<>());

        communityCardDeck.fill(activeCommunityCards);

        assertEquals(cardOne.getId(), communityCardDeck.draw().getId());
        assertEquals(cardTwo.getId(), communityCardDeck.draw().getId());

        assertEquals(1, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.active).intValue());
        assertEquals(2, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.used).intValue());

        communityCardDeck.shuffle(rand);

        assertEquals(3, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.active).intValue());
        assertEquals(0, communityCardDeck.getActiveOrUsedCommunityCardsNumber(CommunityCardDeck.Cards.used).intValue());

        assertEquals(cardTwo.getId(), communityCardDeck.getActiveOrUsedCommunityCardIdAt(CommunityCardDeck.Cards.active, 0));
        assertEquals(cardThree.getId(), communityCardDeck.getActiveOrUsedCommunityCardIdAt(CommunityCardDeck.Cards.active, 1));
        assertEquals(cardOne.getId(), communityCardDeck.getActiveOrUsedCommunityCardIdAt(CommunityCardDeck.Cards.active, 2));
    }

}
