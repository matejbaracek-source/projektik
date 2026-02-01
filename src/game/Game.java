package game;

import Command.*;
import Command.Item;

import java.util.HashMap;

public class Game {

    private GameData world;
    private Player player;
    private HashMap<String, Command> commands;

    public void inicialization() {
        commands = new HashMap<>();
        world = GameData.loadGameDataFromResources("/GameData.json");

        player = new Player();
        if (!world.locations.isEmpty()) {
            player.setLocation(world.locations.get(0));
        }

        // TODO pridat commands
        commands.put("pouzij", new Item(player, world));
        commands.put("jdi", new Movement(player, world));
        commands.put("vezmi", new PickUp(player, world));
        commands.put("seber", new PickUp(player, world));
        commands.put("prohledej", new Search(player));
        commands.put("prozkoumej", new Search(player));
        commands.put("mluv", new Talk(player, world));
        commands.put("prepnipohyb", new SwitchMode(player));
    }

    // TODO rozdelit do vice metod
    public void start() {
        inicialization();

        java.util.Scanner sc = new java.util.Scanner(System.in);

        while (true) {
            Location loc = player.getLocation();
            if (loc == null) {
                System.out.println("Chyba: Hráč nemá nastavenou lokaci.");
                break;
            }

            System.out.println("Jsi v lokaci: " + loc.getName());
            System.out.println(loc.getDescription());

            // Seznam NPC v jednotlivých lokacích
            if (loc.getCharacters() != null && !loc.getCharacters().isEmpty()) {
                java.util.StringJoiner chars = new java.util.StringJoiner(", ");
                for (GameCharacter c : loc.getCharacters()) {
                    chars.add(c.getName());
                }
                System.out.println("Postavy: " + chars.toString());
            }

            java.util.StringJoiner exits = new java.util.StringJoiner(", ");
            if (loc.getNeighbors() != null) {
                for (String neighborId : loc.getNeighbors()) {
                    Location neighbor = world.findLocation(neighborId);
                    if (neighbor != null) {
                        exits.add(neighbor.getName());
                    }
                }
            }
            System.out.println("Sousední lokace: " + exits.toString());

            // Items are now hidden and must be found using "prohledej"
            /*
             * if (loc.getItems() != null && !loc.getItems().isEmpty()) {
             * java.util.StringJoiner items = new java.util.StringJoiner(", ");
             * for (game.Item item : loc.getItems()) {
             * items.add(item.getName());
             * }
             * System.out.println("Předměty: " + items.toString());
             * }
             */

            // Display Inventory
            java.util.ArrayList<game.Item> inventory = player.getInventory();
            if (inventory.isEmpty()) {
                System.out.println("Inventář: prázdný");
            } else {
                java.util.StringJoiner invItems = new java.util.StringJoiner(", ");
                for (game.Item item : inventory) {
                    invItems.add(item.getName());
                }
                System.out.println("Inventář (" + inventory.size() + "/3): " + invItems.toString());
            }

            System.out.print("==> ");
            String cmd = sc.nextLine().trim();

            String[] parts = cmd.split(" ", 2);
            String commandName = parts[0].toLowerCase();

            if (commands.containsKey(commandName)) {
                System.out.println(commands.get(commandName).execute(cmd));
            } else {
                System.out.println("Neznámý příkaz.");
            }
        }
    }

}
