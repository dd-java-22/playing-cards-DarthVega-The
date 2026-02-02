package edu.cnm.deepdive.cards.model.service;

import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.random.RandomGenerator;

public class Trick {

  private final Deck deck;
  private final RandomGenerator rng;
  private final List<Card> blackPile;
  private final List<Card> redPile;

  public Trick(Deck deck, RandomGenerator rng) {
    this.deck = deck;
    this.rng = rng;
    blackPile = new ArrayList<>();
    redPile = new ArrayList<>();
  }

  public void perform(boolean swap) {



    blackPile.clear();
    redPile.clear();
    deck.shuffle(rng);

    while(!deck.isEmpty()) {
      Card selector = deck.deal();
      Card nextCard = deck.deal();
      if(selector.getColor() == (Color.BLACK)) {
        blackPile.add(nextCard);
      } else {
        redPile.add(nextCard);
      }
    }

    if (swap) {
      int maxSwap = Math.min(blackPile.size(), redPile.size());
      int numSwaps = rng.nextInt(1, maxSwap + 1);
      for (int i = 0; i < numSwaps; i++) {
        redPile.add(blackPile.removeFirst());
        blackPile.add(redPile.removeFirst());
      }
    }

    blackPile.sort((card1, card2) -> {
        int result = card1.getColor().compareTo(card2.getColor());
        if (result == 0) {
          result = card1.compareTo(card2);
        }
        return result;
    });
    redPile.sort((card1, card2) -> {
      int result = card2.getColor().compareTo(card1.getColor());
      if (result == 0) {
        result = card1.compareTo(card2);
      }
      return result;
    });
  }

  public void reveal() {
    System.out.println(blackPile);
    System.out.println(redPile);
  }


}