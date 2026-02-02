package edu.cnm.deepdive.cards.model.service;

import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import java.util.ArrayList;
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

  public void perform() {
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

    //TODO 2/2/26 Add logic to swap cards between piles

    blackPile.sort(null);
    redPile.sort(null);
  }

  public void reveal() {
    System.out.println(blackPile);
    System.out.println(redPile);
  }

}
