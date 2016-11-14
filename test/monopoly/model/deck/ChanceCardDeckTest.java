package monopoly.model.deck;

import monopoly.model.deck.card.Card;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ChanceCardDeckTest {
    @Test
    public void fill () {
        List<Card> activeChanceCards = mock(List.class);
        Card cardOne = mock(Card.class);
        Card cardTwo = mock(Card.class);

        when(cardOne.getId()).thenReturn(8);
        when(cardTwo.getId()).thenReturn(9);

        when(activeChanceCards.get(0)).thenReturn(cardOne);
        when(activeChanceCards.get(1)).thenReturn(cardTwo);
        when(activeChanceCards.size()).thenReturn(2);

        ChanceCardDeck chanceCardDeck = new ChanceCardDeck(new ArrayList<>());

        chanceCardDeck.fill(activeChanceCards);
        assertEquals(2, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.active).intValue());
        assertEquals(0, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.used).intValue());
        assertEquals(cardOne.getId(), chanceCardDeck.getActiveOrUsedChanceCardIdAt(ChanceCardDeck.Cards.active, 0));
        assertEquals(cardTwo.getId(), chanceCardDeck.getActiveOrUsedChanceCardIdAt(ChanceCardDeck.Cards.active, 1));
    }

    @Test
    public void draw () {
        List<Card> activeChanceCards = mock(List.class);
        Card cardOne = mock(Card.class);
        Card cardTwo = mock(Card.class);

        when(cardOne.getId()).thenReturn(8);
        when(cardTwo.getId()).thenReturn(7);

        when(activeChanceCards.get(0)).thenReturn(cardOne);
        when(activeChanceCards.get(1)).thenReturn(cardTwo);
        when(activeChanceCards.size()).thenReturn(2);

        ChanceCardDeck chanceCardDeck = new ChanceCardDeck(new ArrayList<>());

        chanceCardDeck.fill(activeChanceCards);

        assertEquals(cardOne.getId(), chanceCardDeck.draw().getId());

        assertEquals(1, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.active).intValue());
        assertEquals(1, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.used).intValue());

        assertEquals(cardTwo.getId(), chanceCardDeck.draw().getId());
        assertEquals(0, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.active).intValue());
        assertEquals(1, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.used).intValue());
    }

    @Test
    public void putCardBack () {
        Card cardOne = mock(Card.class);

        when(cardOne.getId()).thenReturn(8);

        ChanceCardDeck chanceCardDeck = new ChanceCardDeck(new ArrayList<>());

        chanceCardDeck.putCardBack(cardOne);

        assertEquals(cardOne.getId(),chanceCardDeck.getActiveOrUsedChanceCardIdAt(ChanceCardDeck.Cards.used, 0));

        assertEquals(0, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.active).intValue());
        assertEquals(1, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.used).intValue());
    }

    @Test
    public void shuffle () {
        List<Card> activeChanceCards = mock(List.class);
        Card cardOne = mock(Card.class);
        Card cardTwo = mock(Card.class);
        Card cardThree = mock(Card.class);
        Random rand = mock(Random.class);

        when(cardOne.getId()).thenReturn(8);
        when(cardTwo.getId()).thenReturn(9);
        when(cardThree.getId()).thenReturn(10);

        when(activeChanceCards.get(0)).thenReturn(cardOne);
        when(activeChanceCards.get(1)).thenReturn(cardTwo);
        when(activeChanceCards.get(2)).thenReturn(cardThree);
        when(activeChanceCards.size()).thenReturn(3);

        when(rand.nextInt(anyInt())).thenReturn(1).thenReturn(1).thenReturn(0);

        ChanceCardDeck chanceCardDeck = new ChanceCardDeck(new ArrayList<>());

        chanceCardDeck.fill(activeChanceCards);

        assertEquals(cardOne.getId(), chanceCardDeck.draw().getId());
        assertEquals(cardTwo.getId(), chanceCardDeck.draw().getId());

        assertEquals(1, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.active).intValue());
        assertEquals(2, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.used).intValue());

        chanceCardDeck.shuffle(rand);

        assertEquals(3, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.active).intValue());
        assertEquals(0, chanceCardDeck.getActiveOrUsedChanceCardsNumber(ChanceCardDeck.Cards.used).intValue());

        assertEquals(cardTwo.getId(), chanceCardDeck.getActiveOrUsedChanceCardIdAt(ChanceCardDeck.Cards.active, 0));
        assertEquals(cardThree.getId(), chanceCardDeck.getActiveOrUsedChanceCardIdAt(ChanceCardDeck.Cards.active, 1));
        assertEquals(cardOne.getId(), chanceCardDeck.getActiveOrUsedChanceCardIdAt(ChanceCardDeck.Cards.active, 2));
    }

}
