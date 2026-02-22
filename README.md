# Projekt: Infiltrace (Textová Adventura)

Vítejte v textové adventuře, kde je vaším úkolem infiltrovat střežený areál, splnit zadané úkoly a bezpečně uniknout. Hra klade důraz na stealth, plánování a správu rizik.

## Přehled hry

Hráč se ocitá před hlavní bránou areálu s cílem provést sabotáž a extrahovat důležité materiály. K dispozici má různé předměty, interakce s NPC a dvě odlišné strategie pohybu.

## Hlavní funkce

- **Příkazové ovládání**: Plnohodnotné textové rozhraní pro interakci se světem.
- **Dynamický systém rizika**: Každý pohyb v areálu zvyšuje nebo snižuje riziko odhalení strážemi.
- **Správa inventáře**: Sbírání a používání předmětů (klíče, uniformy, hacking zařízení).
- **Stavy hráče**: Možnost převlečení do uniformy stráží pro snížení rizika odhalení.
- **Strategie pohybu**:
    - **Normální pohyb**: Rychlejší, ale více sledovaný kamerami a strážemi.
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

- `help`: Zobrazí nápovědu a dostupné příkazy.
- `go [lokace]`: Přesun do sousední lokace.
- `pickup [předmět]`: Sebrání předmětu v aktuální lokaci.
- `use [předmět]`: Použití předmětu z inventáře.
- `talk [postava]`: Zahájení rozhovoru s NPC.
- `search`: Prohledání aktuální lokace.
- `switch`: Přepnutí mezi normálním a podzemním pohybem.
- `give [postava] [předmět]`: Předání předmětu NPC.
- `checksafe`: Kontrola aktuální úrovně rizika.
- `exit`: Ukončení hry.
