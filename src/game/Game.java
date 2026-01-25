package game;

import Command.*;

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
        commands.put("jdi", new Movement(player, world));
    }

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
