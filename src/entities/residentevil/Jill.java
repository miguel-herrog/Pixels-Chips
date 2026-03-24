package entities.residentevil;

import entities.Boss;
import entities.Player;

public class Jill extends Boss {

    public Jill() {
        super("Jill Valentine", 450); // Un punto intermedio entre Chris y Wesker
    }

    @Override
    public int placeBet() {
        // Apuesta táctica y calculada
        int bet = Math.max(20, getChips() / 8);
        if (bet > getChips()) bet = getChips();
        return bet;
    }

    @Override
    public String getBustDialogue() {
        return getRandomLine(
                "Jill: \"Damn, pushed my luck too far.\"",
                "Jill: \"Should have stood...\"",
                "Jill: \"I need a green herb after that hand.\""
        );
    }

    @Override
    public String getWinDialogue() {
        return getRandomLine(
                "Jill: \"Just like I planned.\"",
                "Jill: \"Master of unlocking... and Blackjack.\"",
                "Jill: \"That's how S.T.A.R.S. does it.\""
        );
    }

    @Override
    public String getLossDialogue() {
        return getRandomLine(
                "Jill: \"Dealer got lucky.\"",
                "Jill: \"Not my best read.\"",
                "Jill: \"I'll recover from this.\""
        );
    }

    @Override
    public String getBankruptDialogue() {
        return getRandomLine(
                "Jill: \"I'm tapped out. Watch your back, Chris.\" (Jill leaves the table)",
                "Jill: \"This casino is worse than Raccoon City... I'm out.\"",
                "Jill: \"I'm leaving. Don't die while I'm gone, boys.\""
        );
    }

    @Override
    public String reactToOtherBust(Player target) {
        if (target.getName().equals("Albert Wesker")) {
            return getRandomLine(
                    "Jill: \"You were saying about superior genetics, Albert?\"",
                    "Jill: \"Looks like the tyrant fell.\"",
                    "Jill: \"Not so smart now, are we?\""
            );
        } else if (target.getName().equals("Chris Redfield")) {
            return getRandomLine(
                    "Jill: \"Careful, Chris, don't lose your head.\"",
                    "Jill: \"Take a breath, Chris. We got this.\"",
                    "Jill: \"Play it safer next time, partner.\""
            );
        }
        return getRandomLine(
                "Jill: \"Tough break, " + target.getName() + ".\"",
                "Jill: \"Don't risk too much next time.\"",
                "Jill: \"Ouch. That hurts to watch.\""
        );
    }

    @Override
    public String reactToOtherWin(Player target) {
        if (target.getName().equals("Albert Wesker")) {
            return getRandomLine(
                    "Jill: \"You must be cheating, Wesker.\"",
                    "Jill: \"Enjoy it while it lasts.\"",
                    "Jill: \"I'm watching your hands, Albert.\""
            );
        } else if (target.getName().equals("Chris Redfield")) {
            return getRandomLine(
                    "Jill: \"Nice work, partner!\"",
                    "Jill: \"That's my teammate.\"",
                    "Jill: \"Show them how it's done, Chris.\""
            );
        }
        return getRandomLine(
                "Jill: \"Well played, " + target.getName() + ".\"",
                "Jill: \"Good read on the dealer.\"",
                "Jill: \"Not bad at all.\""
        );
    }
}