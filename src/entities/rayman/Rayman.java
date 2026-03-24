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
                "Rayman: \"That's a lotta math to just end up broke.\"",
                "Rayman: \"I think this card's a dud. Any chance I can switch it out?\""
        );
    }

    @Override
    public String getWinDialogue() {
        return getRandomLine(
                "Rayman: \"And for my next trick, I'll make all of your chips disappear… Ta-da!\"",
                "Rayman: \"No offense, but these chips look happier next to me.\"",
                "Rayman: \"I win! And you know I didn't cheat cause I don't have any sleeves.\""
        );
    }

    @Override
    public String getLossDialogue() {
        return getRandomLine(
                "Rayman: \"Oh, I lost my hand! Oh, wait, there it is. Oh, I lost my chips!\"",
                "Rayman: \"You know, I'm starting to notice a trend when it comes to loosing my hands.\"",
                "Rayman: \"Goodbye chips, gone but not forgotten!\""
        );
    }

    @Override
    public String getBankruptDialogue() {
        return getRandomLine(
                "Rayman: \"Well, it was fun while it lasted. I'm gonna go before I bet my torso again.\"",
                "Rayman: \"I'm outta lums pals. See you around.\""
        );
    }

    @Override
    public String reactToOtherBust(Player target) {
        if (target.getName().equals("Globox")) {
            return getRandomLine(
                    "Rayman: \"You do have a habit of biting more than you can chew.\"",
                    "Rayman: \"Maybe less plum juice next time, big guy?\""
            );
        } else if (target.getName().equals("Razorbeard")) {
            return getRandomLine(
                    "Rayman: \"Looks like you came up a little short, Admiral. Story of your life, huh?\"",
                    "Rayman: \"Wow, that hand went south fast. Reminds me of your ship! Remember the 'kaboom' part? Classic.\"",
                    "Rayman: \"Eyes on the cards, sir... or eye. However many you can direct to the table.\""
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
                    "Rayman: \"Way to go, buddy!\"",
                    "Rayman: \"Yeah! I knew you could do it!\"",
                    "Rayman: \"Alright! Just don't swallow the chips yet.\""
            );
        } else if (target.getName().equals("Razorbeard")) {
            return getRandomLine(
                    "Rayman: \"Careful, don't let the chips stack too high. I wouldn't want you to lose sight of the table.\"",
                    "Rayman: \"Fine, take those chips, you'll need them. I'm sure the Circus isn't going very well.\"",
                    "Rayman: \"Nice win, Captain! Which eye was on the deck for that one?\""
            );
        }
        return getRandomLine(
                "Rayman: \"Great job, " + target.getName() + "!\"",
                "Rayman: \"Amazing hand!\"",
                "Rayman: \"You're on fire!\""
        );
    }
}