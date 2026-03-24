package entities.rayman;

import entities.Boss;
import entities.Player;

public class Razorbeard extends Boss {

    public Razorbeard() {
        super("Razorbeard", 600); // Jefe rico y confiado
    }

    @Override
    public int placeBet() {
        int bet = Math.max(50, getChips() / 4);
        return Math.min(bet, getChips());
    }

    @Override
    public String getBustDialogue() {
        return getRandomLine(
                "Razorbeard: \"Impossible! The shadows do not lie!\"",
                "Razorbeard: \"A mere setback in my grand design.\"",
                "Razorbeard: \"Who rigged this deck?!\""
        );
    }

    @Override
    public String getWinDialogue() {
        return getRandomLine(
                "Razorbeard: \"I am always one step ahead.\"",
                "Razorbeard: \"The Glade of Dreams will be mine... along with your chips.\"",
                "Razorbeard: \"Predictable.\""
        );
    }

    @Override
    public String getLossDialogue() {
        return getRandomLine(
                "Razorbeard: \"Enjoy this fleeting moment.\"",
                "Razorbeard: \"Darkness will eventually consume you all.\"",
                "Razorbeard: \"Hmph. Pure luck.\""
        );
    }

    @Override
    public String getBankruptDialogue() {
        return getRandomLine(
                "Razorbeard: \"My power... fading... I will return!\" (Vanishes in a cloud of smoke)",
                "Razorbeard: \"You have won the battle, but not the war!\" (Disappears)",
                "Razorbeard: \"Curse you all!\" (Transforms into a bat and flies away)"
        );
    }

    @Override
    public String reactToOtherBust(Player target) {
        if (target.getName().equals("Rayman") || target.getName().equals("Globox")) {
            return getRandomLine(
                    "Razorbeard: \"Pathetic fools. You never stood a chance.\"",
                    "Razorbeard: \"Your failure amuses me.\""
            );
        }
        return getRandomLine(
                "Razorbeard: \"Weakness...\"",
                "Razorbeard: \"Another soul falls.\""
        );
    }

    @Override
    public String reactToOtherWin(Player target) {
        if (target.getName().equals("Rayman")) {
            return getRandomLine(
                    "Razorbeard: \"Do not test my patience, Rayman.\"",
                    "Razorbeard: \"A lucky trick. Nothing more.\""
            );
        }
        return getRandomLine(
                "Razorbeard: \"Your victory is meaningless, " + target.getName() + ".\"",
                "Razorbeard: \"Enjoy your petty coins.\""
        );
    }
}