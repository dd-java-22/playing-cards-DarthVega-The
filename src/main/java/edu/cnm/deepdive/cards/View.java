package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import edu.cnm.deepdive.cards.model.service.Trick;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class View {

  private static final Comparator<Card> BLACK_FISRT_COMPARATOR =
      Comparator.comparing(Card::getColor)
          .thenComparing(Comparator.naturalOrder());
  private static final Comparator<Card> RED_FIRST_COMPARATOR =
      Comparator.comparing(Card::getColor)
          .reversed()
          .thenComparing(Comparator.naturalOrder());

  void perform() {
    System.out.println("Are you ready for a trick?");
    Deck deck = new Deck();
    System.out.println("Here is the deck we are starting out with");
    System.out.println(deck);
    RandomGenerator rng = RandomGenerator.getDefault();
    Trick trick = new Trick(deck, rng);
    trick.perform();
    System.out.println("Now we'll all swap cards");
    int numSwaps = trick.swap();
    System.out.println("Swapped " + numSwaps + " cards between our piles");
    Map<Color, List<Card>> result = trick.getResult();
    TrickResult representation = new TrickResult(result.get(Color.BLACK), result.get(Color.RED));
    
    System.out.println("here is the result:");
    System.out.println(representation);

  }

  public record TrickResult(List<Card> blackPile, List<Card> redPile) {

    @Override
    public String toString() {
      long redInRedCount = redPile
          .stream()
          .filter((Card card) -> card.getColor() == Color.RED)
          .count();
      long blackInBlackCount = blackPile
          .stream()
          .filter((Card card) -> card.getColor() == Color.BLACK)
          .count();
      return "Red Pile: (" + redInRedCount + " red cards) "+ redPile +
          "\nBlack Pile (" + blackInBlackCount + " black cards): " + blackPile;
    }
  }
}