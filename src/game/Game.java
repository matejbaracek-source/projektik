package game;

import Command.*;


import java.util.HashMap;

public class Game {

    private GameData world;
    private Player player;
    private HashMap<String, Command> commands;

    public void inicialization(){
        commands = new HashMap<>();
        world = GameData.loadGameDataFromResources("/gamedata.json");
        //TODO pridat commands
        commands.put("pohyb", new Movement(player, world));

    }

    public void start(){
        inicialization();
        //zde bude herni smycka
    }

}



}
