package dev.brokenstudio.polarlobby.inventories;

import dev.brokenstudio.polarinvs.PolarInventory;

public class InventoryHandler {

    private PolarInventory navigator;
    private PolarInventory hider;
    private PolarInventory profile;
    private PolarInventory settings;
    private PolarInventory settingsLobby;

    public InventoryHandler() {
        _initialize();
    }

    private void _initialize(){
        navigator = PolarInventory.builder().id("navigator").size(5,9).title("§8•● §5Navigator §8●•").provider(new InventoryProviders.NavigatorProvider()).build();
        hider = PolarInventory.builder().id("hider").size(3,9).title("§8•● §5Spieler §dverstecken §8●•").provider(new InventoryProviders.HiderProvider()).build();
        profile = PolarInventory.builder().id("profile").size(6,9).title("§8•● §5Profil §8▎ §dStartseite §8●•").provider(new InventoryProviders.ProfileProvider()).build();
        settings = PolarInventory.builder().id("settings").size(5,9).title("§8•● §5Profil §8▎ §dEinstellungen §8●•").provider(new InventoryProviders.Settings.MainProvider()).parent(profile).build();
        settingsLobby = PolarInventory.builder().id("settingsLobby").size(6,9).title("§8•● §5Profil §8▎ §dEinstellungen §8▎ §dLobby §8●•").provider(new InventoryProviders.Settings.LobbyProvider()).parent(settings).build();
    }

    public PolarInventory getNavigator() {
        return navigator;
    }

    public PolarInventory getHider() {
        return hider;
    }

    public PolarInventory getProfile() {
        return profile;
    }

    public PolarInventory getSettings() {
        return settings;
    }

    public PolarInventory getSettingsLobby() {
        return settingsLobby;
    }
}
