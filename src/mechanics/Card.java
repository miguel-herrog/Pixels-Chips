package mechanics;

public class Card {
    private String rank; // Ej: "2", "10", "J", "Q", "K", "A"
    private int value;   // Ej: 2, 10, 10, 10, 10, 11
    private Suit suit;   // El enum que creamos antes

    public Card(String rank, int value, Suit suit) {
        this.rank = rank;
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }


    public String getRank() {
        return rank;
    }

    public String getSuitSymbol() {
        switch (this.suit) {
            case HEARTS: return "H";
            case DIAMONDS: return "D";
            case CLUBS: return "C";
            case SPADES: return "S";
            default: return "?";
        }
    }

    public String getShortRank() {
        if (this.rank.equals("10")) {
            return "10";
        }
        return this.rank.substring(0, 1).toUpperCase();
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
