package dev.brokenstudio.polarlobby.commands;

import dev.brokenstudio.polarlobby.Lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player))
            return true;

        if(args.length != 1){
            sender.sendMessage(Lobby.getPrefix() + "/set <name>");
            return true;
        }

        Lobby.getInstance().getLocations().setLocation(args[0], ((Player) sender).getLocation());
        sender.sendMessage(Lobby.getPrefix() + "Location §d" + args[0] + " §7was set§8.");

        return true;
    }
}
