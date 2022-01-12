package dev.brokenstudio.polarlobby.badges;

import dev.brokenstudio.cloud.cloudplayer.CloudPlayer;
import dev.brokenstudio.cloud.scoreboard.Prefix;
import dev.brokenstudio.polarlobby.events.PlayerListNameChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BadgesHandler {

    public record Badge(ChatColor color, String icon) {

        public ChatColor getColor() {
            return color;
        }

        public String getIcon() {
            return icon;
        }
    }

    private final static HashMap<String, Badge> badges = new HashMap<>();
    private final static HashMap<UUID, String> playerBadges = new HashMap<>();

    public static BadgesHandler instance;


    public static void loadBadges(){
        badges.put("team", new Badge(ChatColor.RED,"☕"));
    }

    public void loadPlayer(CloudPlayer cloudPlayer){
        Player player = Bukkit.getPlayer(cloudPlayer.getUUID());

        if(cloudPlayer.getProperty("badge", String.class) == null)return;
        String badge = cloudPlayer.getProperty("badge", String.class);
        if(badge.equalsIgnoreCase("none"))return;

        playerBadges.put(cloudPlayer.getUUID(), badge);
        Badge b = badges.get(badge);
        String playerListName = Prefix.getPrefix(player).get() + player.getName() + " §8[" + b.color + b.icon + "§8]";
        player.setPlayerListName(playerListName);
        Bukkit.getPluginManager().callEvent(new PlayerListNameChangeEvent(playerListName, player));
    }

    public void unloadPlayer(Player player, CloudPlayer cloudPlayer){
        cloudPlayer.setProperty("badge", playerBadges.getOrDefault(cloudPlayer.getUUID(),"none"));
        player.setPlayerListName(player.getName());
    }

    public void removePlayer(Player player){
        playerBadges.remove(player.getUniqueId());
    }

    public void changeBadge(Player player, String badge){
        playerBadges.put(player.getUniqueId(), badge);
        Badge b = badges.get(badge);
        String playerListName = Prefix.getPrefix(player).get() + player.getName() + " §8[" + b.color + b.icon + "§8]";
        player.setPlayerListName(playerListName);
        Bukkit.getPluginManager().callEvent(new PlayerListNameChangeEvent(playerListName, player));
    }

    public static BadgesHandler getInstance() {
        if(instance == null)
            instance = new BadgesHandler();
        return instance;
    }

}
