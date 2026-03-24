package entities;

import core.DisplayManager;
import core.ConsoleColors;
import mechanics.Deck;
import mechanics.Card;

public abstract class Boss extends Player {

    public Boss(String name, int startingChips) {
        super(name, startingChips);
    }

    // ==========================================
    // IA DE APUESTAS
    // ==========================================
    /**
     * Calcula cuánto apuesta el NPC en su turno.
     * Por defecto, apuesta el 10% de sus fichas (mínimo 10).
     */
    public int placeBet() {
        int bet = Math.max(10, getChips() / 10);
        if (bet > getChips()) {
            bet = getChips();
        }
        return bet;
    }

    // ==========================================
    // HERRAMIENTA DE ALEATORIEDAD
    // ==========================================
    protected String getRandomLine(String... lines) {
        return lines[new java.util.Random().nextInt(lines.length)];
    }

    // ==========================================
    // SISTEMA DE DIÁLOGOS Y REACCIONES
    // ==========================================
    public String getBustDialogue() {
        return getName() + " curses under their breath.";
    }

    public String getWinDialogue() {
        return getName() + " smiles arrogantly.";
    }

    public String getLossDialogue() {
        return getName() + " glares at the dealer.";
    }

    public String getBankruptDialogue() {
        return getName() + " slams the table and storms out of the casino!";
    }

    // ==========================================
    // REACCIONES A OTROS JUGADORES
    // ==========================================
    public String reactToOtherBust(Player target) {
        return getName() + " shakes their head at " + target.getName() + "'s failure.";
    }

    public String reactToOtherWin(Player target) {
        return getName() + " glares at " + target.getName() + " with envy.";
    }

    // ==========================================
    // TURNO DE JUEGO (IA Clásica)
    // ==========================================
    @Override
    public void playTurn(Deck deck) {
        DisplayManager.pause(1000);
        DisplayManager.type("\n" + ConsoleColors.BLUE + "--- " + getName().toUpperCase() + "'S TURN ---" + ConsoleColors.RESET, 20);

        while (calculateScore() < 17) {
            DisplayManager.pause(1000);
            DisplayManager.type(getName() + " draws a card...", 20);

            Card drawnCard = deck.drawCard();
            addCardToHand(drawnCard);

            System.out.println("-> " + getName() + " draws: " + drawnCard.toString());
            DisplayManager.pause(500);
        }

        DisplayManager.pause(1000);
        int finalScore = calculateScore();

        if (finalScore > 21) {
            DisplayManager.type(ConsoleColors.RED + getBustDialogue() + ConsoleColors.RESET, 30);
            System.out.println("(BUSTS with " + finalScore + ")");
        } else {
            DisplayManager.type(getName() + " stands with " + finalScore + ".", 30);
        }
    }
}