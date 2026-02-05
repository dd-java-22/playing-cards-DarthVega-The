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

  public record TrickResult(List<Card> blackPile, List<Card> redPile) {

    @Override
    public String toString() {
      long redInRedCount = redPile.stream().filter((Card card) -> card.getColor() == Color.RED).count();
      long blackInBlackCount = blackPile.stream().filter((Card card) -> card.getColor() == Color.BLACK).count();
      return "Red Pile: (" + redInRedCount + " red cards) "+ redPile +
          "\nBlack Pile (" + blackInBlackCount + " black cards): " + blackPile;
    }
  }

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
  }

  public int swap() {
    int maxSwap = Math.min(blackPile.size(), redPile.size());
    int numSwaps = rng.nextInt(1, maxSwap + 1);
    for (int i = 0; i < numSwaps; i++) {
      redPile.add(blackPile.removeFirst());
      blackPile.add(redPile.removeFirst());
    }
    return numSwaps;
  }

  public TrickResult getResult() {
    return new TrickResult(blackPile, redPile);
  }


}