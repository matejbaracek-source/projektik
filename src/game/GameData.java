package game;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Container for all game-related data loaded from external resources.
 * Includes lists of items, characters, locations, and quests.
 */
public class GameData {
    public ArrayList<Item> items;
    public ArrayList<GameCharacter> characters;
    public ArrayList<Location> locations;
    public ArrayList<Quest> quests;

    /**
     * Loads game data from a JSON file.
     * 
     * @param resourcePath path to the resource file
     * @return a game.GameData object filled with the loaded data
     */
    public static GameData loadGameDataFromResources(String resourcePath) {
        // Vytvoření objektu pro práci s JSON souborem
        Gson gson = new Gson();

        // Načtení souboru gamedata.json, musí být ve složce res/resources, ta musí být
        // označena jako resource složka projektu
        try (InputStream is = GameData.class.getResourceAsStream(resourcePath)) {

            // Zde ověřujeme, zdali soubor existuje
            if (is == null) {
                throw new IllegalStateException("Nenalezen resource: " + resourcePath +
                        " (zkontrolujte, že soubor je v src/main/resources).");
            }

            // Přečte celý JSON a vytvoří instanci game.GameData, naplní vlastnosti podle
            // názvů klíčů v JSONU, vrátí se hotová třída game.GameData
            GameData data = gson.fromJson(
                    new InputStreamReader(is, StandardCharsets.UTF_8),
                    GameData.class);
            data.initLocationItems();
            data.initLocationCharacters();
            return data;

        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }

    }

    // getting items from each location
    /**
     * Initializes items within each location based on their loot tables.
     */
    private void initLocationItems() {
        if (locations == null)
            return;
        for (Location location : locations) {
            if (location.getLootTable() != null) {
                for (String itemId : location.getLootTable()) {
                    Item item = findItem(itemId);
                    if (item != null) {
                        location.addItem(item);
                    }
                }
            }
        }
    }

    // adding each character to their home location
    /**
     * Adds each character to their designated home location.
     */
    private void initLocationCharacters() {
        if (characters == null)
            return;
        for (GameCharacter character : characters) {
            if (character.getHomeLocationId() != null && !character.getHomeLocationId().equals("none")) {
                Location loc = findLocation(character.getHomeLocationId());
                if (loc != null) {
                    loc.addCharacter(character);
                }
            }
        }
    }

    // finding right item by their id
    /**
     * Finds an item by its unique identifier.
     *
     * @param id The ID of the item to find.
     * @return The Item object if found, null otherwise.
     */
    public Item findItem(String id) {
        if (items == null)
            return null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    // finding right location by their id
    /**
     * Finds a location by its unique identifier.
     *
     * @param id The ID of the location to find.
     * @return The Location object if found.
     * @throws IllegalArgumentException if no location with the given ID exists.
     */
    public Location findLocation(String id) {
        for (Location l : locations) {
            if (l.getId().equals(id)) {
                return l;
            }
        }
        throw new IllegalArgumentException("Neexistuje lokace s id: " + id);
    }

    /**
     * Finds a character by its unique identifier.
     *
     * @param id The ID of the character to find.
     * @return The GameCharacter object if found, null otherwise.
     */
    public GameCharacter findCharacter(String id) {
        if (characters == null)
            return null;
        for (GameCharacter c : characters) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

}
