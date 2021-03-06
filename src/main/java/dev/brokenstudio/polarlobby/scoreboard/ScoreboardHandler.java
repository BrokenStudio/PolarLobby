package dev.brokenstudio.polarlobby.scoreboard;

import dev.brokenstudio.cloud.cloudplayer.CloudPlayer;
import dev.brokenstudio.cloud.scoreboard.Prefix;
import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.bits.BitsHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardHandler {

    public enum ScoreboardState {
        ONE(1, " §b♚ §8▎ ","§7Twitter","     §8» ","§b@ByteFoxNet"),TWO(2, " §9✎ §8▎ ","§7Discord","     §8» §9dc.byte","§9fox.net"),
        THREE(3, " §c✯ §8▎ ","§7TeamSpeak","     §8» ","§cts.bytefox.net"),FOUR(4, " §6✿ §8▎ ","§7Forum","     §8» ","§6ByteFox.net");

        private final int id;
        private final String topOne;
        private final String topTwo;
        private final String bottomOne;
        private final String bottomTwo;

        ScoreboardState(int id, String topOne, String topTwo, String bottomOne, String bottomTwo) {
            this.id = id;
            this.topOne = topOne;
            this.topTwo = topTwo;
            this.bottomOne = bottomOne;
            this.bottomTwo = bottomTwo;
        }

        public static ScoreboardState getStateById(int id){
            switch (id){
                case 0:
                    return ScoreboardState.FOUR;
                case 2:
                    return ScoreboardState.TWO;
                case 3:
                    return ScoreboardState.THREE;
                default:
                    return ScoreboardState.ONE;
            }
        }

    }

    private BukkitTask task;
    private ScoreboardState currentState;
    private boolean firstPage;

    public ScoreboardHandler(){
        currentState = ScoreboardState.ONE;
        firstPage = true;
        runAnimation();
    }

    public void setSidebar(Player player, CloudPlayer cloudPlayer){

        Scoreboard scoreboard = player.getScoreboard();

        Objective objective = scoreboard.registerNewObjective("lobby","dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§8•● §5Byte§dFox §8●• §7Lobby");

        Team line = scoreboard.registerNewTeam("line");
        line.setPrefix("§8§m                         ");
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
            playtimeFriendOne.setPrefix(" §5✈ §8▎ §7Spielzeit");
            playtimeFriendTwo.setPrefix("     §8» §70h");
        }else{
            bitsClanOne.setPrefix(" §5☕ §8▎ §7Clan");
            bitsClanTwo.setPrefix("     §8» §c✘");
            playtimeFriendOne.setPrefix(" §5❤ §8▎ §7Freunde");
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

    private void updateSidebar(Player player){
        Scoreboard scoreboard = player.getScoreboard();
        Team bitsClanOne = scoreboard.getTeam("bitsClanOne");
        Team bitsClanTwo = scoreboard.getTeam("bitsClanTwo");
        Team playtimeFriendOne = scoreboard.getTeam("pTFO");
        Team playtimeFriendTwo = scoreboard.getTeam("pTFT");

        if(firstPage){
            bitsClanOne.setPrefix(" §5➓ §8▎ §7Bits");
            bitsClanTwo.setPrefix("     §8» §7" + BitsHandler.getInstance().getBits(player.getUniqueId()));
            playtimeFriendOne.setPrefix(" §5✈ §8▎ §7Spielzeit");
            playtimeFriendTwo.setPrefix("     §8» §70h");
        }else{
            bitsClanOne.setPrefix(" §5☕ §8▎ §7Clan");
            bitsClanTwo.setPrefix("     §8» §c✘");
            playtimeFriendOne.setPrefix(" §5❤ §8▎ §7Freunde");
            playtimeFriendTwo.setPrefix("     §8» §d§l0/0");
        }

        Team linkType = scoreboard.getTeam("linkType");
        linkType.setPrefix(currentState.topOne);
        linkType.setSuffix(currentState.topTwo);

        Team link = scoreboard.getTeam("link");
        link.setPrefix(currentState.bottomOne);
        link.setSuffix(currentState.bottomTwo);
    }

    private void runAnimation(){
        task = new BukkitRunnable(){

            @Override
            public void run() {
                ScoreboardState newState = ScoreboardState.getStateById((currentState.id+1)%4);
                currentState = newState;
                firstPage = !firstPage;
                Bukkit.getOnlinePlayers().forEach(cr -> updateSidebar(cr));


            }
        }.runTaskTimer(Lobby.getInstance(),100,100);
    }

}
