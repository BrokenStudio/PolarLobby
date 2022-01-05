package dev.brokenstudio.polarlobby.player;

import dev.brokenstudio.polarlobby.inventories.LobbyColor;
import dev.brokenstudio.polarlobby.player.handler.LobbySettingsHandler;

public class LobbySettings {

    private LobbyColor lobbyColor;
    private boolean teleportAnimation;

    public LobbyColor getColor() {
        return lobbyColor;
    }

    public void setColor(LobbyColor lobbyColor) {
        this.lobbyColor = lobbyColor;
    }

    public static LobbySettingsHandler handler(){
        return LobbySettingsHandler.getInstance();
    }

    public boolean isTeleportAnimation() {
        return teleportAnimation;
    }

    public void setTeleportAnimation(boolean teleportAnimation) {
        this.teleportAnimation = teleportAnimation;
    }
}
