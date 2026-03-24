package core;

import entities.Player;
import entities.Boss;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println(ConsoleColors.CYAN + "=================================================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW_BOLD + " 🎰 WELCOME TO THE PIXELS & CHIPS CASINO 🎰 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "=================================================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD + "The only place where legends come to lose their money." + ConsoleColors.RESET);

        while (isRunning) {
            System.out.println("\n" + ConsoleColors.BLUE + "--- MAIN MENU ---" + ConsoleColors.RESET);
            System.out.println("1. Themed Tables (Play against Video Game Legends)");
            System.out.println("2. Casual Blackjack (Classic 1v1 against the House)");
            System.out.println("3. Exit the Club");
            System.out.print("\nWhere do you want to sit? > ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println(ConsoleColors.GREEN + "\n[ Entering the VIP Lounge... ]" + ConsoleColors.RESET);

                    Player player = new Player("Player", 500);
                    GameEngine engine = new GameEngine();

                    TableManager.showSagas(scanner, player, engine);
                    break;
                case "2":
                    System.out.println(ConsoleColors.GREEN + "\n[ Approaching the classic Blackjack table... ]" + ConsoleColors.RESET);
                    Player casualPlayer = new Player("Player", 500);
                    GameEngine casualEngine = new GameEngine();

                    casualEngine.startCasual(casualPlayer);
                    break;
                case "3":
                    System.out.println(ConsoleColors.CYAN + "\nCashing out. See you next time, Player." + ConsoleColors.RESET);
                    isRunning = false;
                    break;
                default:
                    System.out.println(ConsoleColors.RED_BOLD + "\n[!] The bouncer glares at you. Choose a valid option (1, 2, or 3)." + ConsoleColors.RESET);
            }
        }
        scanner.close();
    }
}