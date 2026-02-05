package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.service.Trick;
import edu.cnm.deepdive.cards.model.service.Trick.TrickResult;
import java.util.random.RandomGenerator;

public class View {

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
    TrickResult result = trick.getResult();
    
    System.out.println("here is the result:");
    System.out.println(result);

  }
}