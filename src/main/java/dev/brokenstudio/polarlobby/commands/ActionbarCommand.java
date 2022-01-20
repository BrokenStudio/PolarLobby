package dev.brokenstudio.polarlobby.commands;

import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.actionbar.ActionbarHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ActionbarCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 1){

            ActionbarHandler.ActionbarList list = Lobby.getInstance().getActionbarHandler().getList();

            StringBuilder builder = new StringBuilder(Lobby.getPrefix() + "Actionbars§8:");

            list.forEach(cr -> {
                builder.append("\n").append(Lobby.getPrefix()).append("§8[")
                        .append("§d").append(list.indexOf(cr)).append("§8] ")
                        .append(cr);
            });

            sender.sendMessage(builder.toString());

        }else if(args.length == 2){

            if(args[0].equalsIgnoreCase("add")){

                String actionbar = args[1].replace("_", " ");
                actionbar = ChatColor.translateAlternateColorCodes('&', actionbar);

                sender.sendMessage(Lobby.getPrefix() + "Die Actionbar mit dem Index §d" + Lobby.getInstance().getActionbarHandler().addActionbar(actionbar) + " §7wurde erstellt§8.");

            }else if(args[0].equalsIgnoreCase("remove")){

                int index;

                try {
                    index = Integer.parseInt(args[1]);
                }catch (NumberFormatException ex){
                    sender.sendMessage(Lobby.getPrefix() + "Der Index muss eine Zahl sein§8.");
                    return true;
                }

                Lobby.getInstance().getActionbarHandler().removeActionbar(index);

                sender.sendMessage(Lobby.getPrefix() + "Die Actionbar mit dem Index §d" + index + " §7wurde entfernt§8.");

            }else{
                sender.sendMessage(Lobby.getPrefix() + "/actionbar <add,remove,list> (message, id)");
            }

        }else{
            sender.sendMessage(Lobby.getPrefix() + "/actionbar <add,remove,list> (message, id)");
            return true;
        }

        return true;
    }
}
