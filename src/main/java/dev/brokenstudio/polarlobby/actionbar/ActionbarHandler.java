package dev.brokenstudio.polarlobby.actionbar;

import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.serialization.Serializer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ActionbarHandler {

    public static class ActionbarList extends ArrayList<String> {

        public static ActionbarList fromJson(String json){
            if(json.equals("empty"))
                return new ActionbarList();
            return Serializer.gson.fromJson(json, ActionbarList.class);
        }

        public String toJson(){
            return Serializer.gson.toJson(this);
        }

    }

    private final ActionbarList messages;
    private boolean send;
    private int currentIndex;

    public ActionbarHandler(ActionbarList messages) {
        this.messages = messages;
        currentIndex = 0;
        if(this.messages.size() > 0){
            send = true;
        }else{
            send = false;
        }
        startTask();
    }

    public int addActionbar(String message){
        messages.add(message);
        send = true;
        return messages.indexOf(message);
    }

    public void removeActionbar(int index){
        if(currentIndex == messages.size()-1){
            currentIndex = 0;
        }
        messages.remove(index);
        if(messages.size() == 0)
            send = false;
    }

    public ActionbarList getList(){
        return messages;
    }

    private void sendCurrentActionbar(){
        Bukkit.getOnlinePlayers().forEach(cr -> cr.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(messages.get(currentIndex))));
    }

    public void sendActionbarToOnePlayer(Player player){
        if(send){
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(messages.get(currentIndex)));
        }
    }

    private int calculateNextIndex(){
        return (currentIndex + 1) % this.messages.size();
    }

    private void startTask(){
        AtomicInteger run = new AtomicInteger(0);
        new BukkitRunnable(){
            @Override
            public void run() {
                if(send){
                    sendCurrentActionbar();
                    if(run.get() == 30){
                        run.set(0);
                        currentIndex = calculateNextIndex();
                    }
                    run.set(run.get() + 1);
                }
            }
        }.runTaskTimerAsynchronously(Lobby.getInstance(),0, 20);
    }

}