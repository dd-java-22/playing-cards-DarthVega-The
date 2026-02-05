package edu.cnm.deepdive.cards.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckTest {
  private Deck deck = new Deck ();


  @Test
void size_has_52_cards() {
  assertEquals(52, deck.size());
}

@Test
  void shuffle_sort() {
    deck.shuffle(RandomGenerator.getDefault());
    assertEquals(52, deck.size());
    deck.sort(Comparator.naturalOrder());
    Card c1 = deck.deal();
    assertEquals(Suit.CLUBS, c1.getRank());
    assertEquals(51, deck.size());
  }

//  @Test
//  void deal_full_deck() {
//    Card card = null;
//    for (int i = 0; i < 52; i++) {
//      card = deck.deal();
//    }
//    assertEquals(Suit.SPADES, card.getSuit());
//    assertEquals(Rank);
//  }
}
