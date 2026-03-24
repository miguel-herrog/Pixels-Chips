package entities;

import core.DisplayManager;
import mechanics.Deck;
import mechanics.Card;

public abstract class Boss extends Player {

    public Boss(String name, int startingChips) {
        super(name, startingChips);
    }

    /**
     * Override playTurn: The boss doesn't use a Scanner.
     * It plays automatically using basic Casino Dealer AI (hits until 17).
     */
    @Override
    public void playTurn(Deck deck) {
        DisplayManager.pause(1000);
        DisplayManager.type("\n--- " + getName().toUpperCase() + " PLAYS ---", 20);

        while (calculateScore() < 17) {
            DisplayManager.pause(1000);
            DisplayManager.type(getName() + " draws a card...", 20);

            Card drawnCard = deck.drawCard();
            addCardToHand(drawnCard);

            System.out.println("-> " + drawnCard.toString());
            DisplayManager.pause(500);
        }

        DisplayManager.pause(1000);
        int finalScore = calculateScore();

        if (finalScore > 21) {
            DisplayManager.type(getName() + " BUSTS with " + finalScore + "!", 30);
        } else {
            DisplayManager.type(getName() + " stands with " + finalScore + ".", 30);
        }
    }
}