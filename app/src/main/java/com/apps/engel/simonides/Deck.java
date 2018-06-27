package com.apps.engel.simonides;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    public LinkedList<Card> cards = new LinkedList<>();

    public Deck() {
        for (Card.Suit suit: Card.Suit.values()) {
            for (Card.Value value: Card.Value.values()) {
                cards.add(new Card(suit,value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
