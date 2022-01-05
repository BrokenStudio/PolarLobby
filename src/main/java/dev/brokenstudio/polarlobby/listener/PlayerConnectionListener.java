package dev.brokenstudio.polarlobby.listener;

import dev.brokenstudio.cloud.cloudplayer.CloudPlayer;
import dev.brokenstudio.cloud.cloudplugin.CloudPlugin;
import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.inventorues.LobbyColor;
import dev.brokenstudio.polarlobby.player.LobbySettings;
import dev.brokenstudio.polarlobby.utils.JsonLocation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerConnectionListener implements Listener {

    private final static HashMap<UUID, CloudPlayer> cpPlayerSave = new HashMap<>();

    @EventHandler
    public void onLog(AsyncPlayerPreLoginEvent e){
        cpPlayerSave.put(e.getUniqueId(), CloudPlugin.getInstance().getCloudPlayerHandler().getCloudPlayer(e.getUniqueId()));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        Lobby.getInstance().getPlayerUtils().join(player);

        CloudPlayer cloudPlayer = cpPlayerSave.get(player.getUniqueId());
        if(cloudPlayer.getProperty("lobby_spawn_last_location", Boolean.TYPE) != null){
            if(cloudPlayer.getProperty("lobby_spawn_last_location", Boolean.TYPE)){
                JsonLocation jsonLocation = cloudPlayer.getProperty("lobby_last_location",JsonLocation.class);
                if(jsonLocation != null){
                    player.teleport(jsonLocation.toLocation());
                }else{
                    player.teleport(Lobby.getInstance().getLocations().getLocation("spawn"));
                }
            }else{
                player.teleport(Lobby.getInstance().getLocations().getLocation("spawn"));
            }
        }else{
            player.teleport(Lobby.getInstance().getLocations().getLocation("spawn"));
        }

        LobbySettings settings = cloudPlayer.getProperty("lobby_settings", LobbySettings.class);
        if(settings == null){
            settings = new LobbySettings();
            settings.setColor(LobbyColor.DARK_GRAY);
        }
        LobbySettings.handler().setSettings(player, settings);
        cpPlayerSave.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        CloudPlayer cloudPlayer = CloudPlugin.getInstance().getCloudPlayerHandler().getCloudPlayer(player.getUniqueId());
        cloudPlayer.setProperty("lobby_last_location", new JsonLocation(player.getLocation()));
        cloudPlayer.setProperty("lobby_settings", LobbySettings.handler().getSettings(player));
        CloudPlugin.getInstance().getCloudPlayerHandler().setCloudPlayer(cloudPlayer);

    }

}
