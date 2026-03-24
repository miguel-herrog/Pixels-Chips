package entities;

import core.DisplayManager;
import mechanics.Card;
import mechanics.Deck;
import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int chips;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.chips = 100;
    }

    public Player(String name, int startingChips) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.chips = startingChips;
    }

    public String getName() { return name; }
    public int getChips() { return chips; }
    public void setChips(int chips) { this.chips = chips; }
    public void adjustChips(int amount) { this.chips += amount; }
    public void addCardToHand(Card card) { hand.add(card); }
    public void clearHand() { this.hand.clear(); }

    public int calculateScore() {
        int score = 0, aces = 0;
        for (Card h : hand){
            score += h.getValue();
            if (h.getRank().equals("A")){
                aces += 1;
            }
        }
        while (score > 21 && aces > 0){
            score -= 10;
            aces -= 1;
        }
        return score;
    }

    public void showHand() {
        DisplayManager.type("\n--- " + name.toUpperCase() + "'s Hand ---", 10);
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();
        StringBuilder line4 = new StringBuilder();
        StringBuilder line5 = new StringBuilder();

        for (Card c : hand) {
            String r = c.getShortRank();
            String s = c.getSuitSymbol();
            String topRank = r.length() == 1 ? r + " " : r;
            String botRank = r.length() == 1 ? " " + r : r;

            line1.append("┌───────┐ ");
            line2.append("│ ").append(topRank).append("    │ ");
            line3.append("│   ").append(s).append("   │ ");
            line4.append("│    ").append(botRank).append(" │ ");
            line5.append("└───────┘ ");
        }

        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
        System.out.println(line5);
        DisplayManager.type("Current Score: " + calculateScore(), 10);
        System.out.println("-------------------");
    }

    public void showFirstCard() {
        DisplayManager.type("\n--- " + name.toUpperCase() + "'s Hand ---", 10);
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();
        StringBuilder line4 = new StringBuilder();
        StringBuilder line5 = new StringBuilder();

        Card firstCard = hand.get(0);
        String r = firstCard.getShortRank();
        String s = firstCard.getSuitSymbol();
        String topRank = r.length() == 1 ? r + " " : r;
        String botRank = r.length() == 1 ? " " + r : r;

        line1.append("┌───────┐ ");
        line2.append("│ ").append(topRank).append("    │ ");
        line3.append("│   ").append(s).append("   │ ");
        line4.append("│    ").append(botRank).append(" │ ");
        line5.append("└───────┘ ");

        line1.append("┌───────┐ ");
        line2.append("│ ░░░░░ │ ");
        line3.append("│ ░ ? ░ │ ");
        line4.append("│ ░░░░░ │ ");
        line5.append("└───────┘ ");

        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
        System.out.println(line5);
        System.out.println("-------------------");
    }

    public void playTurn(Deck deck) { }
}