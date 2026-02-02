package edu.cnm.deepdive.cards.model;

import java.util.Comparator;

public class RedFirstComparator implements Comparator<Card> {

  @Override
  public int compare(Card card1, Card card2) {
    int result = card2.getColor().compareTo(card1.getColor());
    if (result == 0) {
      result = card1.compareTo(card2);
    }
    return result;
  }


}
