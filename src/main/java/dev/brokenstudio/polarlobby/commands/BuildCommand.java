package dev.brokenstudio.polarlobby.commands;

import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.database.building.BuildHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player))
            return true;

        Player player = (Player) commandSender;

        if(BuildHandler.getInstance().isBuilding(player)){
            BuildHandler.getInstance().setBuilding(player, true);
            player.sendMessage(Lobby.getPrefix() + "Du kannst nun bauen.");
        }else{
            BuildHandler.getInstance().setBuilding(player, false);
            player.sendMessage(Lobby.getPrefix() + "Du kannst nun nicht mehr bauen.");
        }

        return true;
    }
}
