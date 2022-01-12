package dev.brokenstudio.polarlobby.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerListNameChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final String playerListName;
    private final Player player;

    public PlayerListNameChangeEvent(String playerListName, Player player) {
        this.playerListName = playerListName;
        this.player = player;
    }

    public String getPlayerListName() {
        return playerListName;
    }

    public Player getPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
