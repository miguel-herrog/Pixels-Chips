package entities.rayman;

import entities.Boss;
import entities.Player;

public class MrDark extends Boss {

    public MrDark() {
        super("Mr. Dark", 600); // Jefe rico y confiado
    }

    @Override
    public int placeBet() {
        int bet = Math.max(50, getChips() / 4);
        return Math.min(bet, getChips());
    }

    @Override
    public String getBustDialogue() {
        return getRandomLine(
                "Mr. Dark: \"Impossible! The shadows do not lie!\"",
                "Mr. Dark: \"A mere setback in my grand design.\"",
                "Mr. Dark: \"Who rigged this deck?!\""
        );
    }

    @Override
    public String getWinDialogue() {
        return getRandomLine(
                "Mr. Dark: \"I am always one step ahead.\"",
                "Mr. Dark: \"The Glade of Dreams will be mine... along with your chips.\"",
                "Mr. Dark: \"Predictable.\""
        );
    }

    @Override
    public String getLossDialogue() {
        return getRandomLine(
                "Mr. Dark: \"Enjoy this fleeting moment.\"",
                "Mr. Dark: \"Darkness will eventually consume you all.\"",
                "Mr. Dark: \"Hmph. Pure luck.\""
        );
    }

    @Override
    public String getBankruptDialogue() {
        return getRandomLine(
                "Mr. Dark: \"My power... fading... I will return!\" (Vanishes in a cloud of smoke)",
                "Mr. Dark: \"You have won the battle, but not the war!\" (Disappears)",
                "Mr. Dark: \"Curse you all!\" (Transforms into a bat and flies away)"
        );
    }

    @Override
    public String reactToOtherBust(Player target) {
        if (target.getName().equals("Rayman") || target.getName().equals("Globox")) {
            return getRandomLine(
                    "Mr. Dark: \"Pathetic fools. You never stood a chance.\"",
                    "Mr. Dark: \"Your failure amuses me.\""
            );
        }
        return getRandomLine(
                "Mr. Dark: \"Weakness...\"",
                "Mr. Dark: \"Another soul falls.\""
        );
    }

    @Override
    public String reactToOtherWin(Player target) {
        if (target.getName().equals("Rayman")) {
            return getRandomLine(
                    "Mr. Dark: \"Do not test my patience, Rayman.\"",
                    "Mr. Dark: \"A lucky trick. Nothing more.\""
            );
        }
        return getRandomLine(
                "Mr. Dark: \"Your victory is meaningless, " + target.getName() + ".\"",
                "Mr. Dark: \"Enjoy your petty coins.\""
        );
    }
}