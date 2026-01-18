package game;

import java.util.Scanner;

public class Game {
    private Player hrac;
    private boolean beziHra;

    public Game() {
        this.hrac = new Player();
        this.beziHra = true;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- VÍTEJ VE HŘE: POSLEDNÍ NADĚJE (Zjednodušená verze) ---");
        System.out.println("Cíl: Dostat se do hangáru.");
        System.out.println("Příkazy: jdi <kam>, vezmi <co>, pouzij <co>, konec");

        while (beziHra) {
            System.out.print("\nCo uděláš? > ");
            String vstup = scanner.nextLine();
            zpracujPrikaz(vstup);
        }
    }

    private void zpracujPrikaz(String vstup) {
        String[] casti = vstup.split(" ");
        String akce = casti[0];
        String parametr = casti.length > 1 ? casti[1] : "";

        if (akce.equals("konec")) {
            beziHra = false;
        } else if (akce.equals("vezmi")) {
            // Vzor Factory: Vyrobíme předmět
            Item predmet = ItemFactory.createItem(parametr);
            System.out.println("Sebral jsi: " + predmet.getName());

            if (parametr.equals("klic")) {
                hrac.seberKlic();
            }
        } else if (akce.equals("pouzij")) {
            if (parametr.equals("uniforma")) {
                // Vzor State: Změna stavu
                hrac.setStav(new DisguisedState());
            } else {
                System.out.println("To teď nemůžeš použít.");
            }
        } else if (akce.equals("jdi")) {
            vyresPohyb(parametr);
        } else {
            System.out.println("Neznámý příkaz.");
        }
    }

    private void vyresPohyb(String kam) {
        // Vzor Strategy: Výběr strategie pohybu
        MovementStrategy strategie;

        if (kam.equals("podzemi")) {
            if (hrac.maKlic()) {
                strategie = new UndergroundMovement();
            } else {
                System.out.println("Nemáš klíč od podzemí! Musíš jít po povrchu.");
                strategie = new NormalMovement();
            }
        } else {
            strategie = new NormalMovement();
        }

        // Provedení strategie
        strategie.pohniSe(kam);

        // Kontrola hlídek (State Pattern)
        if (strategie instanceof NormalMovement) {
            if (hrac.getStav().muzeProjithlidkou()) {
                System.out.println("Stráž tě zahlédla, ale díky uniformě tě nechala projít.");
            } else {
                System.out.println("POZOR! Stráž tě viděla. Máš štěstí, že to bylo jen varování.");
            }
        }
    }
}
