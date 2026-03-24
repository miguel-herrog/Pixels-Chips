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

            String color = (s.equals("♥") || s.equals("♦") || s.equals("H") || s.equals("D"))
                    ? core.ConsoleColors.RED_BOLD : core.ConsoleColors.WHITE_BOLD;
            String reset = core.ConsoleColors.RESET;

            line1.append(color).append("┌───────┐ ").append(reset);
            line2.append(color).append("│ ").append(topRank).append("    │ ").append(reset);
            line3.append(color).append("│   ").append(s).append("   │ ").append(reset);
            line4.append(color).append("│    ").append(botRank).append(" │ ").append(reset);
            line5.append(color).append("└───────┘ ").append(reset);
        }

        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
        System.out.println(line5);
        DisplayManager.type(core.ConsoleColors.CYAN + "Current Score: " + calculateScore() + core.ConsoleColors.RESET, 10);
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

        String color = (s.equals("♥") || s.equals("♦") || s.equals("H") || s.equals("D"))
                ? core.ConsoleColors.RED_BOLD : core.ConsoleColors.WHITE_BOLD;
        String reset = core.ConsoleColors.RESET;

        line1.append(color).append("┌───────┐ ").append(reset);
        line2.append(color).append("│ ").append(topRank).append("    │ ").append(reset);
        line3.append(color).append("│   ").append(s).append("   │ ").append(reset);
        line4.append(color).append("│    ").append(botRank).append(" │ ").append(reset);
        line5.append(color).append("└───────┘ ").append(reset);

        String hiddenColor = core.ConsoleColors.YELLOW;
        line1.append(hiddenColor).append("┌───────┐ ").append(reset);
        line2.append(hiddenColor).append("│ ░░░░░ │ ").append(reset);
        line3.append(hiddenColor).append("│ ░ ? ░ │ ").append(reset);
        line4.append(hiddenColor).append("│ ░░░░░ │ ").append(reset);
        line5.append(hiddenColor).append("└───────┘ ").append(reset);

        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
        System.out.println(line5);
        System.out.println("-------------------");
    }

    public void playTurn(Deck deck) { }
}