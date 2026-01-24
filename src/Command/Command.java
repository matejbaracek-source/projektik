package Command;

import game.Player;

public interface Command {
    String execute(String command);
    boolean exit();


}
