package dev.brokenstudio.polarlobby.scoreboard;

import dev.brokenstudio.cloud.cloudplayer.CloudPlayer;
import dev.brokenstudio.cloud.scoreboard.Prefix;
import dev.brokenstudio.polarlobby.bits.BitsHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardHandler {

    public enum ScoreboardState {
        ONE(1, " §b♚ §8▎ ","§7Twitter","     §8» ","§b@ByteFoxNet"),TWO(2, " §9✎ §8▎ ","§7Discord","     §8» §9Byte","§9Fox.net/discord"),
        THREE(3, " §c✯ §8▎ ","§7TeamSpeak","     §8» ","§cts.bytefox.net"),FOUR(4, " §6✿ §8▎ ","§7Forum","     §8» ","§6ByteFox.net");

        private int id;
        private String topOne;
        private String topTwo;
        private String bottomOne;
        private String bottomTwo;

        ScoreboardState(int id, String topOne, String topTwo, String bottomOne, String bottomTwo) {
            this.topOne = topOne;
            this.topTwo = topTwo;
            this.bottomOne = bottomOne;
            this.bottomTwo = bottomTwo;
        }

        public int getId() {
            return id;
        }

        public String getTopOne() {
            return topOne;
        }

        public String getTopTwo() {
            return topTwo;
        }

        public String getBottomOne() {
            return bottomOne;
        }

        public String getBottomTwo() {
            return bottomTwo;
        }
    }

    private BukkitTask task;
    private ScoreboardState currentState;
    private boolean firstPage;

    public ScoreboardHandler(){
        currentState = ScoreboardState.ONE;
        firstPage = true;
    }

    public void setSidebar(Player player, CloudPlayer cloudPlayer){

        Scoreboard scoreboard = player.getScoreboard();

        Objective objective = scoreboard.registerNewObjective("lobby","dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§8•● §5Byte§dFox §8●• §7Lobby");

        Team line = scoreboard.registerNewTeam("line");
        line.setPrefix("§8§m                    ");
        line.addEntry(ChatColor.DARK_GRAY + "" + ChatColor.WHITE);
        line.addEntry(ChatColor.DARK_RED + "" + ChatColor.WHITE);

        Team rankOne = scoreboard.registerNewTeam("rankOne");
        rankOne.setPrefix(" §5✌ §8▎ §7Rang");
        rankOne.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);

        Team rankTwo = scoreboard.registerNewTeam("rankTwo");
        rankTwo.setPrefix("     §8» ");
        String rankString = Prefix.getPrefix(player).get();
        rankTwo.setSuffix(rankString.substring(0, rankString.length()-7));
        rankTwo.addEntry(ChatColor.BLUE + "" + ChatColor.WHITE);

        Team bitsClanOne = scoreboard.registerNewTeam("bitsClanOne");
        Team bitsClanTwo = scoreboard.registerNewTeam("bitsClanTwo");
        Team playtimeFriendOne = scoreboard.registerNewTeam("pTFO");
        Team playtimeFriendTwo = scoreboard.registerNewTeam("pTFT");

        bitsClanOne.addEntry(ChatColor.YELLOW + "" + ChatColor.WHITE);
        bitsClanTwo.addEntry(ChatColor.WHITE + "" + ChatColor.WHITE);
        playtimeFriendOne.addEntry(ChatColor.GOLD + "" + ChatColor.WHITE);
        playtimeFriendTwo.addEntry(ChatColor.DARK_PURPLE + "" + ChatColor.WHITE);

        if(firstPage){
            bitsClanOne.setPrefix(" §5➓ §8▎ §7Bits");
            bitsClanTwo.setPrefix("     §8» §7" + BitsHandler.getInstance().getBits(player.getUniqueId()));
            playtimeFriendOne.setPrefix(" §6✈ §8▎ §7Spielzeit");
            playtimeFriendTwo.setPrefix("     §8» §70h");
        }else{
            bitsClanOne.setPrefix(" §5☕ §8▎ §7Clan");
            bitsClanTwo.setPrefix("     §8» §c✘");
            playtimeFriendOne.setPrefix(" §6❤ §8▎ §Freunde");
            playtimeFriendTwo.setPrefix("     §8» §d§l0/0");
        }

        Team linkType = scoreboard.registerNewTeam("linkType");
        linkType.setPrefix(currentState.topOne);
        linkType.setSuffix(currentState.topTwo);
        linkType.addEntry(ChatColor.LIGHT_PURPLE + "" + ChatColor.WHITE);

        Team link = scoreboard.registerNewTeam("link");
        link.setPrefix(currentState.bottomOne);
        link.setSuffix(currentState.bottomTwo);
        link.addEntry(ChatColor.GRAY + "" + ChatColor.WHITE);

        Team emptyLine = scoreboard.registerNewTeam("emptyline");
        emptyLine.setPrefix(ChatColor.YELLOW +  " ");
        emptyLine.addEntry(ChatColor.DARK_AQUA + "" + ChatColor.BLACK);
        emptyLine.addEntry(ChatColor.DARK_BLUE + "" + ChatColor.BLACK);
        emptyLine.addEntry(ChatColor.DARK_GREEN + "" + ChatColor.BLACK);
        emptyLine.addEntry(ChatColor.DARK_PURPLE + "" + ChatColor.BLACK);

        objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.WHITE).setScore(14);
        objective.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(13);
        objective.getScore(ChatColor.BLUE + "" + ChatColor.WHITE).setScore(12);
        objective.getScore(ChatColor.DARK_AQUA + "" + ChatColor.BLACK).setScore(11);
        objective.getScore(ChatColor.YELLOW + "" + ChatColor.WHITE).setScore(10);
        objective.getScore(ChatColor.WHITE + "" + ChatColor.WHITE).setScore(9);
        objective.getScore(ChatColor.DARK_BLUE + "" + ChatColor.BLACK).setScore(8);
        objective.getScore(ChatColor.GOLD + "" + ChatColor.WHITE).setScore(7);
        objective.getScore(ChatColor.DARK_PURPLE + "" + ChatColor.WHITE).setScore(6);
        objective.getScore(ChatColor.DARK_RED + "" + ChatColor.WHITE).setScore(5);
        objective.getScore(ChatColor.DARK_GREEN + "" + ChatColor.BLACK).setScore(4);
        objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.WHITE).setScore(3);
        objective.getScore(ChatColor.GRAY + "" + ChatColor.WHITE).setScore(2);
        objective.getScore(ChatColor.DARK_PURPLE + "" + ChatColor.BLACK).setScore(1);

    }

}
