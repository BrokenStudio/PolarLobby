package dev.brokenstudio.polarlobby.player;

import dev.brokenstudio.polarlobby.inventorues.LobbyColor;
import dev.brokenstudio.polarlobby.player.handler.LobbySettingsHandler;

public class LobbySettings {

    private LobbyColor lobbyColor;

    public LobbyColor getColor() {
        return lobbyColor;
    }

    public void setColor(LobbyColor lobbyColor) {
        this.lobbyColor = lobbyColor;
    }

    public static LobbySettingsHandler handler(){
        return LobbySettingsHandler.getInstance();
    }

}
