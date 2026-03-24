package entities.residentevil;

import entities.Boss;
import entities.Player;

public class Wesker extends Boss {

    public Wesker() {
        super("Albert Wesker", 500);
    }

    @Override
    public int placeBet() {
        int bet = Math.max(25, getChips() / 5);
        if (bet > getChips()) bet = getChips();
        return bet;
    }

    @Override
    public String getBustDialogue() {
        return getRandomLine(
                "Wesker: \"Complete... global... saturation... ruined by a card?!\"",
                "Wesker: \"Unacceptable. Who shuffled this deck?!\"",
                "Wesker: \"A minor miscalculation.\""
        );
    }

    @Override
    public String getWinDialogue() {
        return getRandomLine(
                "Wesker: \"Natural selection at work. I win.\"",
                "Wesker: \"You were doomed from the start.\"",
                "Wesker: \"Child's play.\""
        );
    }

    @Override
    public String getLossDialogue() {
        return getRandomLine(
                "Wesker: \"Poor performance, indeed. You'll pay for this.\"",
                "Wesker: \"Enjoy your brief moment of triumph.\"",
                "Wesker: \"I will not forget this humiliation.\""
        );
    }

    @Override
    public String getBankruptDialogue() {
        return getRandomLine(
                "Wesker: \"Seven minutes... is all I had. I'll be back.\" (Leaves the table)",
                "Wesker: \"You haven't seen the last of me!\" (Storms out angrily)",
                "Wesker: \"This changes nothing!\" (Disappears into the shadows)"
        );
    }

    @Override
    public String reactToOtherBust(Player target) {
        if (target.getName().equals("Chris Redfield")) {
            return getRandomLine(
                    "Wesker: \"Oh, Chris... your incompetence never ceases to amuse me.\"",
                    "Wesker: \"As clumsy as ever, Redfield.\"",
                    "Wesker: \"Did you really think you could win, Chris?\""
            );
        } else if (target.getName().equals("Jill Valentine")) {
            return getRandomLine(
                    "Wesker: \"You've always been a disappointment, Jill.\"",
                    "Wesker: \"Too slow, Valentine. Just like in the mansion.\"",
                    "Wesker: \"You should have stayed out of my way, Jill.\""
            );
        }
        return getRandomLine(
                "Wesker: \"Natural selection at work, " + target.getName() + ".\"",
                "Wesker: \"Pathetic.\"",
                "Wesker: \"A predictable failure from a lesser being.\""
        );
    }

    @Override
    public String reactToOtherWin(Player target) {
        if (target.getName().equals("Chris Redfield")) {
            return getRandomLine(
                    "Wesker: \"Beginner's luck, Chris. Don't get used to it.\"",
                    "Wesker: \"Don't flatter yourself, Redfield.\"",
                    "Wesker: \"A statistical anomaly.\""
            );
        } else if (target.getName().equals("Jill Valentine")) {
            return getRandomLine(
                    "Wesker: \"A lucky break, Valentine.\"",
                    "Wesker: \"Impressive... for a S.T.A.R.S. reject.\"",
                    "Wesker: \"Don't let it go to your head, Jill.\""
            );
        }
        return getRandomLine(
                "Wesker: \"Enjoy your temporary victory, " + target.getName() + ".\"",
                "Wesker: \"I let you win that one.\"",
                "Wesker: \"Don't get used to it, human.\""
        );
    }
}