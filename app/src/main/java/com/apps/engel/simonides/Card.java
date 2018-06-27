package com.apps.engel.simonides;

public class Card {
    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS
    }
    public enum Value {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    public final Suit suit;
    public final Value value;

    public Card (Suit suit, Value value) {
        this.suit=suit;
        this.value=value;
    }
}
