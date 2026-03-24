package core;

import entities.*;
import entities.residentevil.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TableManager {

    public static void showSagas(Scanner scanner, Player player, GameEngine engine) {
        boolean inSagas = true;

        while (inSagas) {
            System.out.println("\n" + ConsoleColors.CYAN + "=== SELECT A FRANCHISE ===" + ConsoleColors.RESET);
            System.out.println("1. Resident Evil");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nChoose an option > ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    showResidentEvilTables(scanner, player, engine);
                    break;
                case "0":
                    inSagas = false;
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
            }
        }
    }

    private static void showResidentEvilTables(Scanner scanner, Player player, GameEngine engine) {
        boolean inRE = true;

        while (inRE) {
            System.out.println("\n" + ConsoleColors.RED_BOLD + "=== RESIDENT EVIL TABLES ===" + ConsoleColors.RESET);
            System.out.println("1. The Spencer Mansion (Albert Wesker vs Chris Redfield)");
            System.out.println("0. Go Back");
            System.out.print("\nChoose a table > ");

            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                System.out.println(ConsoleColors.GREEN + "\n[ Sitting at The Spencer Mansion table... ]" + ConsoleColors.RESET);

                ArrayList<Boss> spencerMansionNPCs = new ArrayList<>();

                spencerMansionNPCs.add(new Wesker());
                spencerMansionNPCs.add(new Chris());
                spencerMansionNPCs.add(new Jill());

                engine.startTable(player, spencerMansionNPCs);

                if (player.getChips() <= 0) {
                    inRE = false;
                }
            } else {
                System.out.println(ConsoleColors.RED + "Invalid table." + ConsoleColors.RESET);
            }
        }
    }
}