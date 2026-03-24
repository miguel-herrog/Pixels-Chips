package core;

import entities.Player;
import entities.Boss;
import mechanics.Card;
import mechanics.Deck;

import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {
    private Scanner scanner;

    public GameEngine() {
        this.scanner = new Scanner(System.in);
    }

    public void startTable(Player player, ArrayList<Boss> npcs) {
        Player dealer = new Player("The House", 999999);

        DisplayManager.type("\n" + ConsoleColors.CYAN + "=== WELCOME TO THE TABLE ===" + ConsoleColors.RESET);
        for (Boss b : npcs) {
            DisplayManager.type(b.getName() + " is sitting at the table with " + b.getChips() + " chips.");
        }

        while (player.getChips() > 0 && !npcs.isEmpty()) {
            System.out.println("\n=====================================");
            DisplayManager.type(ConsoleColors.BLUE + "--- NEW ROUND ---" + ConsoleColors.RESET);

            // 1. FASE DE APUESTAS
            DisplayManager.type("Your chips: " + ConsoleColors.YELLOW_BOLD + player.getChips() + ConsoleColors.RESET);
            int playerBet = 0;
            boolean validBet = false;
            while (!validBet) {
                DisplayManager.type("Place your bet:");
                try {
                    playerBet = Integer.parseInt(scanner.nextLine().trim());
                    if (playerBet > 0 && playerBet <= player.getChips()) {
                        validBet = true;
                    } else {
                        DisplayManager.type(ConsoleColors.RED + "Invalid amount." + ConsoleColors.RESET);
                    }
                } catch (Exception e) {
                    DisplayManager.type(ConsoleColors.RED + "Enter a number." + ConsoleColors.RESET);
                }
            }

            int[] npcBets = new int[npcs.size()];
            for (int i = 0; i < npcs.size(); i++) {
                npcBets[i] = npcs.get(i).placeBet();
                DisplayManager.type(npcs.get(i).getName() + " bets " + ConsoleColors.YELLOW_BOLD + npcBets[i] + ConsoleColors.RESET + " chips.");
            }

            // 2. REPARTO DE CARTAS
            Deck deck = new Deck();
            deck.shuffle();
            player.clearHand();
            dealer.clearHand();
            for (Boss b : npcs) b.clearHand();

            player.addCardToHand(deck.drawCard());
            player.addCardToHand(deck.drawCard());
            dealer.addCardToHand(deck.drawCard());
            dealer.addCardToHand(deck.drawCard());
            for (Boss b : npcs) {
                b.addCardToHand(deck.drawCard());
                b.addCardToHand(deck.drawCard());
            }

            dealer.showFirstCard();

            // 3. TURNO DEL JUGADOR
            boolean isPlayerTurn = true;
            boolean firstAction = true; // Controlamos si es su primera decisión

            while (isPlayerTurn && player.calculateScore() < 21) {
                player.showHand();

                // Comprobamos si tiene saldo para doblar y si es su primera acción
                boolean canDouble = (firstAction && player.getChips() >= (playerBet * 2));

                if (canDouble) {
                    DisplayManager.type("Do you want to (H)it, (S)tand or (D)ouble Down?");
                } else {
                    DisplayManager.type("Do you want to (H)it or (S)tand?");
                }

                String choice = scanner.nextLine().toUpperCase();

                if (choice.equals("H")) {
                    Card drawn = deck.drawCard();
                    player.addCardToHand(drawn);
                    DisplayManager.type("You drew: " + drawn.toString());
                    DisplayManager.pause(1000);
                    firstAction = false; // Ya no puede doblar después de pedir

                } else if (choice.equals("S")) {
                    DisplayManager.type("You decide to stand.");
                    isPlayerTurn = false;

                } else if (choice.equals("D") && canDouble) {
                    playerBet *= 2;
                    DisplayManager.type(ConsoleColors.YELLOW_BOLD + "DOUBLE DOWN! Your bet is now " + playerBet + " chips!" + ConsoleColors.RESET);
                    DisplayManager.pause(1000);

                    // Roba exactamente una carta y se acaba el turno
                    Card drawn = deck.drawCard();
                    player.addCardToHand(drawn);
                    DisplayManager.type("You drew your final card: " + drawn.toString());
                    DisplayManager.pause(1500);
                    isPlayerTurn = false;

                } else {
                    DisplayManager.type(ConsoleColors.RED + "Invalid choice!" + ConsoleColors.RESET);
                }
            }

            player.showHand();

            if (player.calculateScore() > 21) {
                DisplayManager.type(ConsoleColors.RED_BOLD + "\nBUST! You went over 21!" + ConsoleColors.RESET);
                for (Boss b : npcs) {
                    DisplayManager.type(b.reactToOtherBust(player));
                }
            }

            // 4. TURNO DE LOS NPCS
            for (Boss b : npcs) {
                DisplayManager.type("\n" + ConsoleColors.BLUE + "--- " + b.getName().toUpperCase() + "'S TURN ---" + ConsoleColors.RESET);
                b.showHand();

                // IA Básica (pedir hasta 17)
                while (b.calculateScore() < 17) {
                    DisplayManager.pause(1000);
                    Card drawn = deck.drawCard();
                    b.addCardToHand(drawn);
                    DisplayManager.type(b.getName() + " draws: " + drawn.toString());
                    b.showHand();
                }

                if (b.calculateScore() > 21) {
                    DisplayManager.type(ConsoleColors.RED + b.getBustDialogue() + ConsoleColors.RESET);
                    for (Boss observer : npcs) {
                        if (observer != b) {
                            DisplayManager.type(observer.reactToOtherBust(b));
                        }
                    }
                } else {
                    DisplayManager.type(b.getName() + " stands with " + b.calculateScore() + ".");
                }
            }

            // 5. TURNO DEL CRUPIER
            DisplayManager.type("\n" + ConsoleColors.BLUE + "--- DEALER'S TURN ---" + ConsoleColors.RESET);
            dealer.showHand();
            while (dealer.calculateScore() < 17) {
                DisplayManager.pause(1000);
                Card drawn = deck.drawCard();
                dealer.addCardToHand(drawn);
                DisplayManager.type("Dealer draws: " + drawn.toString());
                dealer.showHand();
            }

            // 6. PAGOS, ELIMINACIONES Y REACCIONES DE FIN DE RONDA
            int dealerScore = dealer.calculateScore();
            DisplayManager.type("\n=== ROUND RESULTS ===");

            // Resolución del jugador
            if (player.calculateScore() <= 21 && (dealerScore > 21 || player.calculateScore() > dealerScore)) {
                DisplayManager.type(ConsoleColors.GREEN_BOLD + "You beat the Dealer! Won " + playerBet + " chips." + ConsoleColors.RESET);
                player.adjustChips(playerBet);
                for (Boss b : npcs) {
                    DisplayManager.type(b.reactToOtherWin(player));
                }
            } else if (player.calculateScore() > 21 || player.calculateScore() < dealerScore) {
                DisplayManager.type(ConsoleColors.RED + "You lost to the Dealer." + ConsoleColors.RESET);
                player.adjustChips(-playerBet);
            } else {
                DisplayManager.type(ConsoleColors.CYAN + "Push! You tied with the Dealer." + ConsoleColors.RESET);
            }

            // Resolución de los NPCs
            ArrayList<Boss> bankruptNPCs = new ArrayList<>();
            for (int i = 0; i < npcs.size(); i++) {
                Boss b = npcs.get(i);

                    // 1. El NPC Gana
                if (b.calculateScore() <= 21 && (dealerScore > 21 || b.calculateScore() > dealerScore)) {
                    DisplayManager.type(ConsoleColors.GREEN + b.getName() + " won their bet." + ConsoleColors.RESET);
                    b.adjustChips(npcBets[i]);
                    DisplayManager.type(b.getWinDialogue());

                    // 2. El NPC Empata
                } else if (b.calculateScore() <= 21 && b.calculateScore() == dealerScore) {
                    DisplayManager.type(ConsoleColors.CYAN + b.getName() + " tied with the Dealer (Push)." + ConsoleColors.RESET);

                    // 3. El NPC Pierde
                } else {
                    DisplayManager.type(ConsoleColors.RED + b.getName() + " lost their bet." + ConsoleColors.RESET);
                    b.adjustChips(-npcBets[i]);
                    if (b.calculateScore() <= 21) {
                        DisplayManager.type(b.getLossDialogue());
                    }
                }

                // Comprobar bancarrota
                if (b.getChips() <= 0) {
                    bankruptNPCs.add(b);
                }
            }

            // Eliminaciones
            for (Boss b : bankruptNPCs) {
                System.out.println("\n" + ConsoleColors.RED_BOLD + b.getBankruptDialogue() + ConsoleColors.RESET);
                npcs.remove(b);
            }
            if (player.getChips() > 0 && !npcs.isEmpty()) {
                System.out.println("\n" + ConsoleColors.CYAN + "[ Press ENTER to start the next round ]" + ConsoleColors.RESET);
                scanner.nextLine();
            }
        }

        // --- FIN DE LA MESA ---
        if (player.getChips() <= 0) {
            System.out.println(ConsoleColors.RED_BOLD + "\n*** BANKRUPT ***" + ConsoleColors.RESET);
            DisplayManager.type("You've been cleaned out. The bouncer escorts you out.");
        } else {
            System.out.println(ConsoleColors.GREEN_BOLD + "\n*** TABLE CLEARED! ***" + ConsoleColors.RESET);
            DisplayManager.type("You are the last one standing! You take the pot.");
        }
    }

    // ==========================================
    // MODO CASUAL (1v1 Clásico)
    // ==========================================
    public void startCasual(Player player) {
        Player dealer = new Player("The Dealer", 999999);
        boolean isPlaying = true;

        DisplayManager.type("\n" + ConsoleColors.CYAN + "=== CASUAL BLACKJACK ===" + ConsoleColors.RESET);
        DisplayManager.type("It's just you and the Dealer. Let's play.");

        while (player.getChips() > 0 && isPlaying) {
            System.out.println("\n" + ConsoleColors.BLUE + "=====================================" + ConsoleColors.RESET);
            DisplayManager.type("Your chips: " + ConsoleColors.YELLOW_BOLD + player.getChips() + ConsoleColors.RESET);

            // 1. Apuesta
            int bet = 0;
            boolean validBet = false;
            while (!validBet) {
                DisplayManager.type("Place your bet:");
                try {
                    bet = Integer.parseInt(scanner.nextLine().trim());
                    if (bet > 0 && bet <= player.getChips()) validBet = true;
                    else DisplayManager.type(ConsoleColors.RED + "Invalid amount." + ConsoleColors.RESET);
                } catch (Exception e) {
                    DisplayManager.type(ConsoleColors.RED + "Enter a valid number." + ConsoleColors.RESET);
                }
            }

            // 2. Reparto
            Deck deck = new Deck(); deck.shuffle();
            player.clearHand(); dealer.clearHand();

            player.addCardToHand(deck.drawCard());
            player.addCardToHand(deck.drawCard());
            dealer.addCardToHand(deck.drawCard());
            dealer.addCardToHand(deck.drawCard());

            dealer.showFirstCard();

            // 3. Turno del Jugador
            boolean isPlayerTurn = true;
            boolean firstAction = true;
            while (isPlayerTurn && player.calculateScore() < 21) {
                player.showHand();
                boolean canDouble = (firstAction && player.getChips() >= (bet * 2));

                DisplayManager.type(canDouble ? "Do you want to (H)it, (S)tand or (D)ouble Down?" : "Do you want to (H)it or (S)tand?");
                String choice = scanner.nextLine().toUpperCase();

                if (choice.equals("H")) {
                    Card drawn = deck.drawCard();
                    player.addCardToHand(drawn);
                    DisplayManager.type("You drew: " + drawn.toString());
                    DisplayManager.pause(1000);
                    firstAction = false;
                } else if (choice.equals("S")) {
                    isPlayerTurn = false;
                } else if (choice.equals("D") && canDouble) {
                    bet *= 2;
                    DisplayManager.type(ConsoleColors.YELLOW_BOLD + "DOUBLE DOWN! Bet increased to " + bet + " chips!" + ConsoleColors.RESET);
                    Card drawn = deck.drawCard();
                    player.addCardToHand(drawn);
                    DisplayManager.type("You drew: " + drawn.toString());
                    DisplayManager.pause(1000);
                    isPlayerTurn = false;
                } else {
                    DisplayManager.type(ConsoleColors.RED + "Invalid choice!" + ConsoleColors.RESET);
                }
            }

            player.showHand();
            if (player.calculateScore() > 21) {
                DisplayManager.type(ConsoleColors.RED_BOLD + "BUST! You went over 21." + ConsoleColors.RESET);
            } else {
                // 4. Turno del Crupier
                DisplayManager.type("\n" + ConsoleColors.BLUE + "--- DEALER'S TURN ---" + ConsoleColors.RESET);
                dealer.showHand();
                while (dealer.calculateScore() < 17) {
                    DisplayManager.pause(1000);
                    Card drawn = deck.drawCard();
                    dealer.addCardToHand(drawn);
                    DisplayManager.type("Dealer draws: " + drawn.toString());
                    dealer.showHand();
                }
            }

            // 5. Pagos
            int playerScore = player.calculateScore();
            int dealerScore = dealer.calculateScore();

            if (playerScore > 21) {
                DisplayManager.type(ConsoleColors.RED + "You lose " + bet + " chips." + ConsoleColors.RESET);
                player.adjustChips(-bet);
            } else if (dealerScore > 21 || playerScore > dealerScore) {
                DisplayManager.type(ConsoleColors.GREEN_BOLD + "You win! Payout: " + bet + " chips." + ConsoleColors.RESET);
                player.adjustChips(bet);
            } else if (playerScore == dealerScore) {
                DisplayManager.type(ConsoleColors.CYAN + "Push! It's a tie." + ConsoleColors.RESET);
            } else {
                DisplayManager.type(ConsoleColors.RED + "Dealer wins. You lose " + bet + " chips." + ConsoleColors.RESET);
                player.adjustChips(-bet);
            }

            // 6. Decisión de continuar
            if (player.getChips() > 0) {
                DisplayManager.type("\n" + ConsoleColors.CYAN + "Current balance: " + player.getChips() + " chips." + ConsoleColors.RESET);
                DisplayManager.type("Press ENTER to play another hand, or type 'QUIT' to cash out.");
                String nextAction = scanner.nextLine().toUpperCase();
                if (nextAction.equals("QUIT")) {
                    isPlaying = false;
                }
            }
        }

        if (player.getChips() <= 0) {
            DisplayManager.type(ConsoleColors.RED_BOLD + "\nYou're broke! The casino security escorts you out." + ConsoleColors.RESET);
        } else {
            DisplayManager.type(ConsoleColors.GREEN_BOLD + "\nYou cash out with " + player.getChips() + " chips. Well played!" + ConsoleColors.RESET);
        }
    }
}