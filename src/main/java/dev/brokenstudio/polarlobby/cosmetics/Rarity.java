package dev.brokenstudio.polarlobby.cosmetics;

public enum Rarity {

    COMMON("Gewöhnlich","§f",50),RARE("Selten","§9",25),EPIC("Episch","§5",15),
    EXCLUSIVE("Exklusiv","§e",5),ULTIMATE("Ultimativ","§c",5);

    String name;
    String color;
    int percent;

    Rarity(String name, String color, int percent) {
        this.name = name;
        this.color = color;
        this.percent = percent;
    }

    public String displayname(){
        return color + name;
    }

    public String color() {
        return color;
    }
}
