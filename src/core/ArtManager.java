package core;

import java.util.HashMap;
import java.util.Map;

public class ArtManager {

    // The dictionary that maps a character's name to their ASCII art
    private static final Map<String, String> gallery = new HashMap<>();

    static {
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