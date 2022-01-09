package dev.brokenstudio.polarlobby.inventories;

import dev.brokenstudio.polarinvs.ClickableItem;
import dev.brokenstudio.polarinvs.content.InventoryContents;
import dev.brokenstudio.polarinvs.content.InventoryProvider;
import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.player.LobbySettings;
import dev.brokenstudio.polarlobby.utils.PolarItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class InventoryProviders {

    public static class NavigatorProvider implements InventoryProvider {

        @Override
        public void init(Player player, InventoryContents contents) {
            contents.fillRow(1, LobbySettings.handler().getSettings(player).getColor().glass());
            contents.fillRow(5, LobbySettings.handler().getSettings(player).getColor().glass());
            contents.set(3, 2, new ClickableItem(new PolarItem(Material.IRON_SWORD).name("§8•● §4The§cHunt §8●•").lore("§8§oHorror & Survival").hideAttributes(),(event) -> {
                teleport(player, Lobby.getInstance().getLocations().getLocation("thehunt"));
            }));
            contents.set(3, 4, new ClickableItem(new PolarItem(Material.GRASS_BLOCK).name("§8•● §2Free§aBuild §8●•").lore("§8§oOldschool").hideAttributes(),(event) -> {
                teleport(player, Lobby.getInstance().getLocations().getLocation("freebuild"));
            }));
            contents.set(3, 6, new ClickableItem(new PolarItem(Material.STICK).name("§8•● §6Switch§eFFA §8●•").lore("§8§oSolo & PvP").hideAttributes(),(event) -> {
                teleport(player, Lobby.getInstance().getLocations().getLocation("switchffa"));
            }));
            contents.set(3, 8, new ClickableItem(new PolarItem(Material.DIAMOND_BOOTS).name("§8•● §3Jump§bCity §8●•").lore("§8§oSolo & Parkour").hideAttributes(),(event) -> {
                teleport(player, Lobby.getInstance().getLocations().getLocation("jumpcity"));
            }));
            contents.set(5, 3, new ClickableItem(new PolarItem(Material.PRISMARINE_SHARD).name("§8•● §5Coin§dSystem §8●•").lore().hideAttributes(),(event) -> {
                teleport(player, Lobby.getInstance().getLocations().getLocation("coinsystem"));
            }));
            contents.set(5, 5, new ClickableItem(new PolarItem(Material.TOTEM_OF_UNDYING).name("§8•● §5Spawn §8●•").lore().hideAttributes(),(event) -> {
                teleport(player, Lobby.getInstance().getLocations().getLocation("spawn"));
            }));
            contents.set(5, 7, new ClickableItem(new PolarItem(Material.RESPAWN_ANCHOR).name("§8•● §5Mystery§dCrates §8●•").lore().hideAttributes(),(event) -> {
                teleport(player, Lobby.getInstance().getLocations().getLocation("crates"));
            }));
        }

        private void teleport(Player player, Location location){

            if(LobbySettings.handler().getSettings(player).isTeleportAnimation()){
                //TODO implement the animation
                player.teleport(location);
            }else{
                player.teleport(location);
            }

        }

    }

    public static class HiderProvider implements InventoryProvider {

        @Override
        public void init(Player player, InventoryContents contents) {
            LobbySettings settings = LobbySettings.handler().getSettings(player);
            contents.fillRow(1, settings.getColor().glass());
            contents.fillRow(3, settings.getColor().glass());

            contents.set(2,2, new ClickableItem(new PolarItem(Material.LIME_DYE).name("§8•● §aAlle Spieler §8●•")
                    .glow(settings.getHiderState() == LobbySettings.HiderState.ALL),(event)->{
                settings.setHiderState(LobbySettings.HiderState.ALL);
                settings.getHiderState().getPlayerList().forEach(cr -> player.showPlayer(Lobby.getInstance(), cr));
                player.closeInventory();
            }));

            contents.set(2,3, new ClickableItem(new PolarItem(Material.PURPLE_DYE).name("§8•● §5VIP Spieler §8●•")
                    .glow(settings.getHiderState() == LobbySettings.HiderState.VIP),(event)->{
                settings.setHiderState(LobbySettings.HiderState.VIP);
                settings.getHiderState().getPlayerList().forEach(cr -> player.showPlayer(Lobby.getInstance(), cr));
                player.closeInventory();
            }));

            contents.set(2,5, new ClickableItem(new PolarItem(Material.ORANGE_DYE).name("§8•● §6Freunde §8●•")
                    .glow(settings.getHiderState() == LobbySettings.HiderState.FRIENDS),(event)->{
                settings.setHiderState(LobbySettings.HiderState.FRIENDS);
                settings.getHiderState().getPlayerList().forEach(cr -> player.showPlayer(Lobby.getInstance(), cr));
                player.closeInventory();
            }));

            contents.set(2,7, new ClickableItem(new PolarItem(Material.RED_DYE).name("§8•● §cTeam §8●•")
                    .glow(settings.getHiderState() == LobbySettings.HiderState.TEAM),(event)->{
                settings.setHiderState(LobbySettings.HiderState.TEAM);
                settings.getHiderState().getPlayerList().forEach(cr -> player.showPlayer(Lobby.getInstance(), cr));
                player.closeInventory();
            }));

            contents.set(2,8, new ClickableItem(new PolarItem(Material.LIGHT_GRAY_DYE).name("§8•● §7Keine Spieler §8●•")
                    .glow(settings.getHiderState() == LobbySettings.HiderState.NOONE),(event)->{
                settings.setHiderState(LobbySettings.HiderState.NOONE);
                settings.getHiderState().getPlayerList().forEach(cr -> player.showPlayer(Lobby.getInstance(), cr));
                player.closeInventory();
            }));


        }
    }


}
