package core;

import java.util.HashMap;
import java.util.Map;

public class ArtManager {

    // The dictionary that maps a character's name to their ASCII art
    private static final Map<String, String> gallery = new HashMap<>();

    static {
        gallery.put("BANNER",
                ConsoleColors.PURPLE_BOLD +
                        "  __  __       _ _   _                          \n" +
                        " |  \\/  |     | | | (_)                         \n" +
                        " | \\  / |_   _| | |_ ___   _____ _ __ ___  ___  \n" +
                        " | |\\/| | | | | | __| \\ \\ / / _ \\ '__/ __|/ _ \\ \n" +
                        " | |  | | |_| | | |_| |\\ V /  __/ |  \\__ \\  __/ \n" +
                        " |_|  |_|\\__,_|_|\\__|_| \\_/ \\___|_|  |___/\\___| \n" +
                        ConsoleColors.CYAN_BOLD +
                        "  ____  _            _    _            _        \n" +
                        " |  _ \\| |          | |  (_)          | |       \n" +
                        " | |_) | | __ _  ___| | ___  __ _  ___| | __    \n" +
                        " |  _ <| |/ _` |/ __| |/ / |/ _` |/ __| |/ /    \n" +
                        " | |_) | | (_| | (__|   <| | (_| | (__|   <     \n" +
                        " |____/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\    \n" +
                        "                        _/ |                    \n" +
                        "                       |__/                     \n" +
                        ConsoleColors.RESET
        );
    }

    /**
     * Retrieves the ASCII art for a given character name.
     * @param name The exact name of the character (e.g., "Mad Hatter").
     * @return The ASCII string, or a placeholder if not found.
     */
    public static String getArt(String name) {
        // We ask the gallery to get the art for 'name'.
        // If 'name' doesn't exist, it returns the default error string.
        return gallery.getOrDefault(name, "\n[ NO ART FOUND FOR " + name + " ]\n");
    }
}