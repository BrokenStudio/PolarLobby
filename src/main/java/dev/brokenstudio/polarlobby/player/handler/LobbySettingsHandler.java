package dev.brokenstudio.polarlobby.player.handler;

import dev.brokenstudio.polarlobby.player.LobbySettings;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LobbySettingsHandler {

    private static final HashMap<Player, LobbySettings> settings = new HashMap<>();
    private static LobbySettingsHandler instance;

    public LobbySettings getSettings(Player player){
        return settings.get(player);
    }

    public void setSettings(Player player, LobbySettings lobbySettings){
        settings.put(player, lobbySettings);
    }

    public void removeSettings(Player player){
        settings.remove(player);
    }

    public static LobbySettingsHandler getInstance() {
        if (instance == null)
            instance = new LobbySettingsHandler();
        return instance;
    }

}
