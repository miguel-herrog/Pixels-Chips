package entities.rayman;

import entities.Boss;
import entities.Player;

public class Rayman extends Boss {

    public Rayman() {
        super("Rayman", 400);
    }

    @Override
    public int placeBet() {
        int bet = Math.max(20, getChips() / 10);
        return Math.min(bet, getChips());
    }

    @Override
    public String getBustDialogue() {
        return getRandomLine(
                "Rayman: \"Whoops! My hands slipped... literally!\"",
                "Rayman: \"Yeah... I pushed that too far.\"",
                "Rayman: \"Need to keep my head attached to my shoulders!\""
        );
    }

    @Override
    public String getWinDialogue() {
        return getRandomLine(
                "Rayman: \"YEAH! No limbs, no problem!\"",
                "Rayman: \"That's how we do it in the Glade of Dreams!\"",
                "Rayman: \"Magic fist bumps for everyone!\""
        );
    }

    @Override
    public String getLossDialogue() {
        return getRandomLine(
                "Rayman: \"Ouch. That one hurt.\"",
                "Rayman: \"Not my best jump.\"",
                "Rayman: \"I'll bounce back!\""
        );
    }

    @Override
    public String getBankruptDialogue() {
        return getRandomLine(
                "Rayman: \"Game over for me! See ya!\" (Helicopter hair flies away)",
                "Rayman: \"Out of continues! Good luck, guys!\"",
                "Rayman: \"I'm completely broke... Time to collect more Lums.\""
        );
    }

    @Override
    public String reactToOtherBust(Player target) {
        if (target.getName().equals("Globox")) {
            return getRandomLine(
                    "Rayman: \"Don't worry buddy, it happens to the best of us!\"",
                    "Rayman: \"It's okay Globox, just take a deep breath!\"",
                    "Rayman: \"Maybe less plum juice next time, big guy?\""
            );
        } else if (target.getName().equals("Mr. Dark")) {
            return getRandomLine(
                    "Rayman: \"Ha! Your dark magic can't fix your terrible math!\"",
                    "Rayman: \"Looks like the shadows clouded your judgment.\""
            );
        }
        return getRandomLine(
                "Rayman: \"Tough luck, " + target.getName() + "!\"",
                "Rayman: \"You'll get the next one!\"",
                "Rayman: \"Yikes, that was close.\""
        );
    }

    @Override
    public String reactToOtherWin(Player target) {
        if (target.getName().equals("Globox")) {
            return getRandomLine(
                    "Rayman: \"Way to go, Globox! Awesome!\"",
                    "Rayman: \"High five, buddy! Wait...\""
            );
        } else if (target.getName().equals("Mr. Dark")) {
            return getRandomLine(
                    "Rayman: \"You definitely cheated.\"",
                    "Rayman: \"I'm keeping an eye on your sleeves, Dark.\""
            );
        }
        return getRandomLine(
                "Rayman: \"Great job, " + target.getName() + "!\"",
                "Rayman: \"Amazing hand!\"",
                "Rayman: \"You're on fire!\""
        );
    }
}