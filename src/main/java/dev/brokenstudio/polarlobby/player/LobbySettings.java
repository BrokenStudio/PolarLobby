package dev.brokenstudio.polarlobby.player;

import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.inventories.LobbyColor;
import dev.brokenstudio.polarlobby.player.handler.LobbySettingsHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class LobbySettings {

    public static enum HiderState{
        ALL(0),VIP(1),FRIENDS(2),TEAM(3),NOONE(4);

        private int id;

        HiderState(int id){
            this.id = id;
        }

        public ArrayList<Player> getPlayerList(){

            final ArrayList<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());

            if(id == 0){
                list.removeAll(Bukkit.getOnlinePlayers());
            }else if(id == 1){
                Bukkit.getOnlinePlayers().forEach(cr -> {
                    if(cr.hasPermission("polarlobby.hider") ||
                            cr.hasPermission("polarlobby.team")/*TODO: is friend*/){
                        list.remove(cr);
                    }
                });
            }else if(id == 2){
                Bukkit.getOnlinePlayers().forEach(cr -> {
                    //TODO remove if player is friend
                });
            }else if(id == 3){
                Bukkit.getOnlinePlayers().forEach(cr -> {
                    if(cr.hasPermission("polarlobby.team")){
                        list.remove(cr);
                    }
                });
            }
            return list;
        }

        public void handlePLayer(Player target, Player player){
            if(id == 1){
                if(!target.hasPermission("polarlobby.hider") &&
                        !target.hasPermission("polarlobby.team")/*TODO: is no friend*/){
                    player.hidePlayer(Lobby.getInstance(), target);
                }
            }else if(id == 2){
                //TODO hide player if not friend
            }else if(id == 3){
                if(!target.hasPermission("polarlobby.team")){
                    player.hidePlayer(Lobby.getInstance(), target);;
                }
            }else if(id == 4){
                player.hidePlayer(Lobby.getInstance(), target);
            }
        }

    }

    private LobbyColor lobbyColor;
    private boolean teleportAnimation;
    private HiderState hiderState;

    public LobbyColor getColor() {
        return lobbyColor;
    }

    public void setColor(LobbyColor lobbyColor) {
        this.lobbyColor = lobbyColor;
    }

    public static LobbySettingsHandler handler(){
        return LobbySettingsHandler.getInstance();
    }

    public boolean isTeleportAnimation() {
        return teleportAnimation;
    }

    public void setTeleportAnimation(boolean teleportAnimation) {
        this.teleportAnimation = teleportAnimation;
    }

    public HiderState getHiderState() {
        return hiderState;
    }

    public void setHiderState(HiderState hiderState) {
        this.hiderState = hiderState;
    }
}
