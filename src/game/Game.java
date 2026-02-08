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
        commands.put("konec", new Exit());
        commands.put("exit", new Exit());
        commands.put("ukoly", new Quests(world, player));
        commands.put("quests", new Quests(world, player));
        commands.put("rozhledni_se", new CheckSafe(player, world));
        commands.put("check_safe", new CheckSafe(player, world));
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
                Command c = commands.get(commandName);
                System.out.println(c.execute(cmd));
                if (c.exit()) {
                    break;
                }
            } else {
                System.out.println("Neznámý příkaz.");
            }

            if (checkGameOver()) {
                break;
            }

            checkQuests();
        }
    }

    private boolean checkGameOver() {
        if (player.isCaught()) {
            return true;
        }
        return false;
    }

    private void checkQuests() {
        if (world.quests == null)
            return;
        for (Quest q : world.quests) {
            if (!player.isQuestCompleted(q.getId())) {
                boolean completed = false;
                switch (q.getId()) {
                    case "q_deacitivate_cameras":
                        // Logic: Have hacking device and be in security room? Or better: Use hacking
                        // device.
                        // For now, let's say: If player is in "loc_security" and has
                        // "item_hacking_device".
                        if (player.getLocation().getId().equals("loc_security") && hasItem("item_hacking_device")) {
                            completed = true;
                        }
                        break;
                    case "q_open_underground_path":
                        // Logic: Have key.
                        if (hasItem("item_UnderGroundKey")) {
                            completed = true;
                        }
                        break;
                    case "q_setup_car":
                        // Logic: Have key and gas.
                        if (hasItem("item_key") && hasItem("item_gas")) {
                            completed = true;
                        }
                        break;
                }
                if (completed) {
                    player.markQuestCompleted(q.getId());
                    System.out.println("\n[SPLYNUO] Úkol dokončen: " + q.getTitle() + "\n");
                }
            }
        }
    }

    private boolean hasItem(String itemId) {
        for (game.Item item : player.getInventory()) {
            if (item.getId().equals(itemId)) {
                return true;
            }
        }
        return false;
    }

}
