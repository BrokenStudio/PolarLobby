package dev.brokenstudio.polarlobby.badges;

import dev.brokenstudio.cloud.cloudplayer.CloudPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

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

        Scoreboard scoreboard = player.getScoreboard();
        loadTeams(scoreboard);
        playerBadges.forEach((uuid, badgeName)->{
            scoreboard.getTeam(badgeName).addEntry(Bukkit.getPlayer(uuid).getName());
        });

        if(cloudPlayer.getProperty("badge", String.class) == null)return;
        String badge = cloudPlayer.getProperty("badge", String.class);
        if(badge.equalsIgnoreCase("none"))return;

        playerBadges.put(cloudPlayer.getUUID(), badge);
        Bukkit.getOnlinePlayers().forEach(cr -> cr.getScoreboard().getTeam(badge).addEntry(player.getName()));
    }

    public void unloadPlayer(Player player, CloudPlayer cloudPlayer){
        if(playerBadges.containsKey(player.getUniqueId()))
            Bukkit.getOnlinePlayers().forEach(cr -> cr.getScoreboard().getTeam(playerBadges.get(cloudPlayer.getUUID())).removeEntry(player.getName()));
        cloudPlayer.setProperty("badge", playerBadges.getOrDefault(cloudPlayer.getUUID(),"none"));
    }

    public void removePlayer(Player player){
        playerBadges.remove(player.getUniqueId());
    }

    private void loadTeams(Scoreboard scoreboard){
        badges.forEach((name, badge)->{
            Team team = scoreboard.registerNewTeam(name);
            team.setSuffix(" " + badge.color + badge.icon);
        });
    }

    public void changeBadge(Player player, String newBadge){
        String badge = playerBadges.getOrDefault(player.getUniqueId(),"none");
        if(!badge.equals("none")){
            String finalBadge1 = badge;
            Bukkit.getOnlinePlayers().forEach(cr -> cr.getScoreboard().getTeam(finalBadge1).removeEntry(player.getName()));
        }

        badge = newBadge;
        playerBadges.put(player.getUniqueId(), badge);

        String finalBadge = badge;
        Bukkit.getOnlinePlayers().forEach(cr -> cr.getScoreboard().getTeam(finalBadge).addEntry(player.getName()));

    }

    public static BadgesHandler getInstance() {
        if(instance == null)
            instance = new BadgesHandler();
        return instance;
    }
}
