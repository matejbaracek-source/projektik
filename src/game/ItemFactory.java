package game;

// Vzor Factory: Vytváří předměty
public class ItemFactory {

    public static Item createItem(String type) {
        if (type.equals("uniforma")) {
            return new Item("Uniforma");
        } else if (type.equals("klic")) {
            return new Item("Klíč");
        } else if (type.equals("hack")) {
            return new Item("Hackovací zařízení");
        } else {
            return new Item("Neznámý předmět");
        }
    }
}
