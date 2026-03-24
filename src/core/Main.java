package core;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("=================================================");
        System.out.println(" 🎰 WELCOME TO THE PIXELS & CHIPS CASINO 🎰 ");
        System.out.println("=================================================");
        System.out.println("The only place where legends come to lose their money.");

        while (isRunning) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Themed Tables (Play against Video Game Legends)");
            System.out.println("2. Casual Blackjack (Classic 1v1 against the House)");
            System.out.println("3. Exit the Club");
            System.out.print("\nWhere do you want to sit? > ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("\n[ Entering the VIP Lounge... ]");
                    // TODO: Llamar al gestor de mesas temáticas (Ej: TableManager.showTables())
                    break;
                case "2":
                    System.out.println("\n[ Approaching the classic Blackjack table... ]");
                    // TODO: Iniciar partida clásica sin objetos ni poderes
                    break;
                case "3":
                    System.out.println("\nCashing out. See you next time, Player.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("\n[!] The bouncer glares at you. Choose a valid option (1, 2, or 3).");
            }
        }
        scanner.close();
    }
}