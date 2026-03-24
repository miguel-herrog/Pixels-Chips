package core;

import entities.Player;
import entities.Boss;
import mechanics.Deck;
import java.util.Scanner;

public class GameEngine {
    private Scanner scanner;

    public GameEngine() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia un enfrentamiento hasta la bancarrota de uno de los dos.
     * Retorna true si el jugador gana, false si pierde.
     */
    public boolean startEncounter(Player player, Boss boss) {

        DisplayManager.type("\n*** A new challenger sits at the table: " + boss.getName() + "! ***");
        System.out.println(core.ArtManager.getArt(boss.getName()));

        while (player.getChips() > 0 && boss.getChips() > 0) {
            System.out.println("\n=====================================");
            DisplayManager.type("--- NEW ROUND VS " + boss.getName().toUpperCase() + " ---");
            DisplayManager.type("Your current chips: " + player.getChips());
            DisplayManager.type(boss.getName() + "'s chips: " + boss.getChips());
            DisplayManager.type("How many chips do you dare to bet?");

            // --- THE BETTING PHASE ---
            int bet = 0;
            boolean validBet = false;

            while (!validBet) {
                try {
                    String input = scanner.nextLine();
                    bet = Integer.parseInt(input);

                    if (bet > player.getChips() || bet <= 0) {
                        DisplayManager.type("You can't bet what you don't have (or zero).");
                        DisplayManager.type("You have " + player.getChips() + " chips. Try again:");
                    } else {
                        validBet = true;
                    }
                } catch (NumberFormatException e) {
                    DisplayManager.type(boss.getName() + " smirks: 'That's not a number!'");
                    DisplayManager.type("Please, enter a valid number of chips to bet:");
                }
            }

            // --- RESETTING THE TABLE ---
            player.clearHand();
            boss.clearHand();
            Deck deck = new Deck();
            deck.shuffle();

            // --- THE INITIAL DEAL ---
            player.addCardToHand(deck.drawCard());
            player.addCardToHand(deck.drawCard());

            boss.addCardToHand(deck.drawCard());
            boss.addCardToHand(deck.drawCard());

            player.showHand();
            boss.showFirstCard();

            // --- PLAYER'S TURN ---
            boolean isPlayerTurn = true;
            while (isPlayerTurn && player.calculateScore() < 21) {
                DisplayManager.type("Do you want to (H)it or (S)tand?");
                String choice = scanner.nextLine().toUpperCase();

                switch (choice) {
                    case "H" -> {
                        DisplayManager.type(player.getName() + " draws a card from the deck...");
                        player.addCardToHand(deck.drawCard());
                        player.showHand();
                    }
                    case "S" -> {
                        DisplayManager.type(player.getName() + " decides to stand.");
                        isPlayerTurn = false;
                    }
                    default -> DisplayManager.type("Invalid choice! Type H or S!");
                }
            }

            // Check if player busts
            if (player.calculateScore() > 21) {
                DisplayManager.type("BUST! You went over 21. " + boss.getName() + " takes your chips!");
                player.adjustChips(-bet);
                boss.adjustChips(bet);
                DisplayManager.pause(2000);
                continue; // Skip boss turn
            }

            DisplayManager.pause(2000);

            // --- THE BOSS'S TURN ---
            DisplayManager.type("\n" + boss.getName() + " flips their hidden card!");
            boss.showHand();
            DisplayManager.pause(1500);

            boss.playTurn(deck);

            DisplayManager.type("\n--- FINAL TABLE ---");
            boss.showHand();
            DisplayManager.pause(1000);

            // --- THE VERDICT ---
            int playerScore = player.calculateScore();
            int bossScore = boss.calculateScore();

            System.out.println("\n*** FINAL RESULTS ***");
            DisplayManager.type(player.getName() + "'s Score: " + playerScore);
            DisplayManager.type(boss.getName() + "'s Score: " + bossScore);

            DisplayManager.pause(1500);

            // Payout logic
            if (bossScore > 21) {
                DisplayManager.type(boss.getName() + " busts! YOU WIN THE ROUND!");
                player.adjustChips(bet);
                boss.adjustChips(-bet);
            } else if (playerScore > bossScore) {
                DisplayManager.type("You beat " + boss.getName() + "'s score! YOU WIN THE ROUND!");
                player.adjustChips(bet);
                boss.adjustChips(-bet);
            } else if (playerScore == bossScore) {
                DisplayManager.type("It's a tie! PUSH. Your bet is returned.");
            } else {
                DisplayManager.type(boss.getName() + " wins! You lose your bet!");
                player.adjustChips(-bet);
                boss.adjustChips(bet);
            }
        }

        // --- END OF THE ENCOUNTER ---
        if (player.getChips() <= 0) {
            System.out.println("\n*** BANKRUPT ***");
            DisplayManager.type("You've been cleaned out. The bouncer escorts you out of the club.");
            return false;
        } else {
            System.out.println("\n*** OPPONENT BANKRUPT! ***");
            DisplayManager.type("You cleaned out " + boss.getName() + "! They storm off the table.");
            return true;
        }
    }
}