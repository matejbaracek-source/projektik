# Projekt: Poslední naděje

Vítejte v textové hře Poslední naděje, kde je vaším úkolem infiltrovat střežený areál, splnit zadané úkoly a bezpečně uniknout.

## Přehled hry

Hráč se ocitá před hlavní bránou areálu s cílem provést sabotáž a ukrást střežený kontejner. K dispozici má různé předměty, interakce s NPC a dvě odlišné strategie pohybu. Dále je důležité spravovat riziko odhalení stráže, jinak mise nebude uspěšná

## Hlavní funkce

- **Příkazové ovládání**: Plnohodnotné textové rozhraní pro interakci se světem.
- **Dynamický systém rizika**: Každý pohyb v areálu zvyšuje nebo snižuje riziko odhalení strážemi.
- **Správa inventáře**: Sbírání a používání předmětů (klíče, uniformy, hacking zařízení).
- **Stavy hráče**: Možnost převlečení do uniformy stráží pro snížení rizika odhalení.
- **Strategie pohybu**:
    - **Normální pohyb**: Pomalejší a více sledovaný kamerami a strážemi.
    - **Podzemní pohyb**: Bezpečnější cesta tunely, vyžadující speciální klíč.
- **NPC a Questy**: Interakce s postavami v areálu, které zadávají úkoly nebo poskytují rady.

## Struktura projektu

- `src/`: Zdrojové kódy v Javě.
    - `game/`: Jádro hry, správa stavu, lokací a mechanik.
    - `Command/`: Implementace jednotlivých herních příkazů.
- `res/`: Datové soubory hry.
    - `GameData.json`: Definice lokací, předmětů, postav a questů.

## Jak spustit

1. Ujistěte se, že máte nainstalované JDK (Java Development Kit) verze 11 nebo novější.
2. Zkompilujte projekt (například v IntelliJ IDEA nebo pomocí `javac`).
3. Spusťte třídu `Main`.

## Seznam příkazů

- `pomoc`: Zobrazí nápovědu a dostupné příkazy.
- `jdi [lokace]`: Přesun do sousední lokace.
- `sebrat [předmět]`: Sebrání předmětu v aktuální lokaci.
- `pouzit [předmět]`: Použití předmětu z inventáře.
- `mluv [postava]`: Zahájení rozhovoru s NPC.
- `prohledat`: Prohledání aktuální lokace.
- `prepnipohyb`: Přepnutí mezi normálním a podzemním pohybem.
- `dej [postava] [předmět]`: Předání předmětu NPC.
- `rozhledni_se`: Kontrola aktuální úrovně rizika.
- `konec`: Ukončení hry.
