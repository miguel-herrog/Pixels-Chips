package entities.rayman;

import entities.Boss;
import entities.Player;

public class Globox extends Boss {

    public Globox() {
        super("Globox", 300); // Empieza con menos, es un desastre
    }

    @Override
    public int placeBet() {
        // Apuestas totalmente erráticas (entre 1 y 50)
        int bet = new java.util.Random().nextInt(50) + 1;
        return Math.min(bet, getChips());
    }

    @Override
    public String getBustDialogue() {
        return getRandomLine(
                "Globox: \"WAAAAH! The cards are scary!\"",
                "Globox: \"I just wanted some plum juice...\"",
                "Globox: \"My belly hurts from losing!\""
        );
    }

    @Override
    public String getWinDialogue() {
        return getRandomLine(
                "Globox: \"YAY! I did it! Did you see me, Rayman?\"",
                "Globox: \"More money for plum juice! HAHA!\"",
                "Globox: \"I'm a genius!\""
        );
    }

    @Override
    public String getLossDialogue() {
        return getRandomLine(
                "Globox: \"Oh no... Uglette is going to be so mad at me.\"",
                "Globox: \"*Sad frog noises*\"",
                "Globox: \"Why is the dealer so mean?\""
        );
    }

    @Override
    public String getBankruptDialogue() {
        return getRandomLine(
                "Globox: \"I have no more shiny chips! WAAAAH!\" (Runs away crying)",
                "Globox: \"I'm going home to my babies!\" (Hides in a barrel)",
                "Globox: \"Rayman, can I borrow some money? No? Okay...\""
        );
    }

    @Override
    public String reactToOtherBust(Player target) {
        if (target.getName().equals("Mr. Dark")) {
            return getRandomLine(
                    "Globox: \"Haha! The scary man lost!\"",
                    "Globox: \"Take that, meanie!\""
            );
        }
        return getRandomLine(
                "Globox: \"Oh no, poor " + target.getName() + "!\"",
                "Globox: \"Do you want a hug?\""
        );
    }

    @Override
    public String reactToOtherWin(Player target) {
        if (target.getName().equals("Rayman")) {
            return getRandomLine(
                    "Globox: \"YAY RAYMAN! YOU'RE THE BEST!\"",
                    "Globox: \"My best friend is winning!\""
            );
        }
        return getRandomLine(
                "Globox: \"Wow, " + target.getName() + " is so smart!\"",
                "Globox: \"Teach me how to play!\""
        );
    }
}