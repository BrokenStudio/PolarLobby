package dev.brokenstudio.polarlobby.listener;

import dev.brokenstudio.cloud.cloudplayer.CloudPlayer;
import dev.brokenstudio.cloud.cloudplugin.CloudPlugin;
import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.utils.JsonLocation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        Lobby.getInstance().getPlayerUtils().join(player);

        CloudPlayer cloudPlayer = CloudPlugin.getInstance().getCloudPlayerHandler().getCloudPlayer(player.getUniqueId());

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

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        CloudPlayer cloudPlayer = CloudPlugin.getInstance().getCloudPlayerHandler().getCloudPlayer(player.getUniqueId());
        cloudPlayer.setProperty("lobby_last_location", new JsonLocation(player.getLocation()));
        CloudPlugin.getInstance().getCloudPlayerHandler().setCloudPlayer(cloudPlayer);

    }

}
