package game;

public class Player {
    private PlayerState stav; // Vzor States
    private boolean maKlicOdPodzemi;

    public Player() {
        this.stav = new NormalState(); // Začínáš jako normální
        this.maKlicOdPodzemi = false;
    }

    public void setStav(PlayerState stav) {
        this.stav = stav;
        System.out.println("Změna stavu:");
        this.stav.popisStavu();
    }

    public PlayerState getStav() {
        return stav;
    }

    public void seberKlic() {
        this.maKlicOdPodzemi = true;
        System.out.println("Sebral jsi klíč od podzemí!");
    }

    public boolean maKlic() {
        return maKlicOdPodzemi;
    }
}
