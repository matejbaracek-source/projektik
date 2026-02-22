package game;

import Command.*;
import Command.Use;

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

        // Give player starting items
        game.Item hackingDevice = world.findItem("item_hacking_device");
        if (hackingDevice != null) {
            player.addItem(hackingDevice);
        }

        // TODO pridat commands
        commands.put("pouzij", new Use(player, world));
        commands.put("jdi", new Movement(player, world));
        commands.put("vezmi", new PickUp(player, world));
        commands.put("seber", new PickUp(player, world));
        commands.put("prohledej", new Search(player));
        commands.put("mluv", new Talk(player, world));
        commands.put("prepnipohyb", new SwitchMode(player));
        commands.put("konec", new Exit());
        commands.put("ukoly", new Quests(world, player));
        commands.put("rozhledni_se", new CheckSafe(player, world));
        commands.put("dej", new Give(player, world));
        Help helpCommand = new Help(commands.keySet());
        commands.put("pomoc", helpCommand);
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

            // Check for setup car quest completion
            if (!player.isQuestCompleted("q_setup_car")) {
                if (player.hasItem("item_key") && player.hasItem("item_gas")) {
                    player.markQuestCompleted("q_setup_car");
                    System.out.println("\n[QUEST] Úkol splněn: Připrav auto pro odjezd z areálu!");
                    System.out.println("Máš klíče i palivo. Teď už jen stačí dojet ke bráně!\n");
                }
            }
        }
    }

    private boolean checkGameOver() {
        if (player.isCaught()) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("KONEC HRY: Byl jsi chycen ostrahou!");
            System.out.println("--------------------------------------------------\n");
            return true;
        }

        // Win condition check
        if (player.isQuestCompleted("q_setup_car") && GameState.isCodeGiven() &&
                player.getLocation() != null && player.getLocation().getId().equals("loc_mainGate") &&
                player.getState() instanceof DisguisedState) {

            System.out.println("\n==================================================");
            System.out.println("           GRATULUJEME! VYHRÁL JSI!");
            System.out.println("==================================================");
            System.out.println("S přístupovým kódem odeslaným Pepovi a připraveným");
            System.out.println("autem jsi projel hlavní branou dřív, než stihli");
            System.out.println("vyhlásit poplach.");
            System.out.println("\nKontejner je v bezpečí a ty jsi hrdinou dne!");
            System.out.println("Děkujeme za zahrání naší hry.");
            System.out.println("==================================================\n");
            return true;
        }

        return false;
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
