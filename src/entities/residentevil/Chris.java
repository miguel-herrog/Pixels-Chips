package entities.residentevil;

import entities.Boss;
import entities.Player;

public class Chris extends Boss {

    public Chris() {
        super("Chris Redfield", 400);
    }

    @Override
    public int placeBet() {
        int bet = Math.max(15, getChips() / 10);
        if (bet > getChips()) bet = getChips();
        return bet;
    }

    @Override
    public String getBustDialogue() {
        return getRandomLine(
                "Chris: \"Damn it! Lost focus.\"",
                "Chris: \"Need to be more careful...\"",
                "Chris: \"This is worse than the Spencer Mansion...\""
        );
    }

    @Override
    public String getWinDialogue() {
        return getRandomLine(
                "Chris: \"Target neutralized. I mean... good hand.\"",
                "Chris: \"Just like training.\"",
                "Chris: \"Got 'em.\""
        );
    }

    @Override
    public String getLossDialogue() {
        return getRandomLine(
                "Chris: \"Taking a hit here.\"",
                "Chris: \"Regrouping...\"",
                "Chris: \"Can't win 'em all, I guess.\""
        );
    }

    @Override
    public String getBankruptDialogue() {
        return getRandomLine(
                "Chris: \"I'm out of ammo... I mean, chips. Good luck out there.\" (Leaves)",
                "Chris: \"Mission failed. I'm pulling out.\"",
                "Chris: \"I need backup... and more money.\" (Walks away)"
        );
    }

    @Override
    public String reactToOtherBust(Player target) {
        if (target.getName().equals("Albert Wesker")) {
            return getRandomLine(
                    "Chris: \"Looks like your 'superior genetics' can't count to 21, Wesker.\"",
                    "Chris: \"Not so smart now, are you?\"",
                    "Chris: \"Karma, Wesker.\""
            );
        } else if (target.getName().equals("Jill Valentine")) {
            return getRandomLine(
                    "Chris: \"Shake it off, Jill. We'll get the next one.\"",
                    "Chris: \"Don't worry about it, partner.\"",
                    "Chris: \"Stay focused, Jill.\""
            );
        }
        return getRandomLine(
                "Chris: \"Stay sharp, " + target.getName() + ". We can't afford mistakes.\"",
                "Chris: \"Don't let it get to you.\"",
                "Chris: \"Shake it off, we got the next one.\""
        );
    }

    @Override
    public String reactToOtherWin(Player target) {
        if (target.getName().equals("Albert Wesker")) {
            return getRandomLine(
                    "Chris: \"Don't get cocky, Wesker.\"",
                    "Chris: \"You're counting cards, I know it.\"",
                    "Chris: \"Whatever.\""
            );
        } else if (target.getName().equals("Jill Valentine")) {
            return getRandomLine(
                    "Chris: \"That's my partner!\"",
                    "Chris: \"Great job, Jill!\"",
                    "Chris: \"Show Wesker how it's done!\""
            );
        }
        return getRandomLine(
                "Chris: \"Nice hand, " + target.getName() + ".\"",
                "Chris: \"Good job!\"",
                "Chris: \"Keep it up!\""
        );
    }
}