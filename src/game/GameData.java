package game;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
            return data;

        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }

    }

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

    /**
     * Finds a specific location by its identifier.
     * 
     * @param id the identifier of the location to be found
     * @return the matching location
     */
    public Location findLocation(String id) {
        for (Location l : locations) {
            if (l.getId().equals(id)) {
                return l;
            }
        }
        throw new IllegalArgumentException("Neexistuje lokace s id: " + id);
    }

}
