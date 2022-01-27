package dev.brokenstudio.polarlobby.inventories;

import dev.brokenstudio.cloud.cloudplayer.CloudPlayer;
import dev.brokenstudio.cloud.cloudplugin.CloudPlugin;
import dev.brokenstudio.cloud.scoreboard.Prefix;
import dev.brokenstudio.polarinvs.ClickableItem;
import dev.brokenstudio.polarinvs.InventoryManager;
import dev.brokenstudio.polarinvs.PolarInventory;
import dev.brokenstudio.polarinvs.content.InventoryContents;
import dev.brokenstudio.polarinvs.content.InventoryProvider;
import dev.brokenstudio.polarinvs.content.SlotPos;
import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.glow.GlowAPI;
import dev.brokenstudio.polarlobby.player.LobbySettings;
import dev.brokenstudio.polarlobby.utils.PlayerHeadURLStorage;
import dev.brokenstudio.polarlobby.utils.PolarItem;
import dev.brokenstudio.polarlobby.utils.Skull;
import dev.brokenstudio.polarlobby.utils.SkullStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
                Bukkit.getOnlinePlayers().forEach(cr -> player.showPlayer(Lobby.getInstance(), cr));
                settings.getHiderState().getPlayerList().forEach(cr -> player.hidePlayer(Lobby.getInstance(), cr));
                player.closeInventory();
            }));

            contents.set(2,3, new ClickableItem(new PolarItem(Material.PURPLE_DYE).name("§8•● §5VIP Spieler §8●•")
                    .glow(settings.getHiderState() == LobbySettings.HiderState.VIP),(event)->{
                settings.setHiderState(LobbySettings.HiderState.VIP);
                Bukkit.getOnlinePlayers().forEach(cr -> player.showPlayer(Lobby.getInstance(), cr));
                settings.getHiderState().getPlayerList().forEach(cr -> player.hidePlayer(Lobby.getInstance(), cr));
                player.closeInventory();
            }));

            contents.set(2,5, new ClickableItem(new PolarItem(Material.ORANGE_DYE).name("§8•● §6Freunde §8●•")
                    .glow(settings.getHiderState() == LobbySettings.HiderState.FRIENDS),(event)->{
                settings.setHiderState(LobbySettings.HiderState.FRIENDS);
                Bukkit.getOnlinePlayers().forEach(cr -> player.showPlayer(Lobby.getInstance(), cr));
                settings.getHiderState().getPlayerList().forEach(cr -> player.hidePlayer(Lobby.getInstance(), cr));
                player.closeInventory();
            }));

            contents.set(2,7, new ClickableItem(new PolarItem(Material.RED_DYE).name("§8•● §cTeam §8●•")
                    .glow(settings.getHiderState() == LobbySettings.HiderState.TEAM),(event)->{
                settings.setHiderState(LobbySettings.HiderState.TEAM);
                Bukkit.getOnlinePlayers().forEach(cr -> player.showPlayer(Lobby.getInstance(), cr));
                settings.getHiderState().getPlayerList().forEach(cr -> player.hidePlayer(Lobby.getInstance(), cr));
                player.closeInventory();
            }));

            contents.set(2,8, new ClickableItem(new PolarItem(Material.LIGHT_GRAY_DYE).name("§8•● §7Keine Spieler §8●•")
                    .glow(settings.getHiderState() == LobbySettings.HiderState.NOONE),(event)->{
                settings.setHiderState(LobbySettings.HiderState.NOONE);
                Bukkit.getOnlinePlayers().forEach(cr -> player.showPlayer(Lobby.getInstance(), cr));
                settings.getHiderState().getPlayerList().forEach(cr -> player.hidePlayer(Lobby.getInstance(), cr));
                player.closeInventory();
            }));


        }
    }

    public static class ProfileProvider implements InventoryProvider {

        @Override
        public void init(Player player, InventoryContents contents) {
            contents.fillRow(1, LobbySettings.handler().getSettings(player).getColor().glass());
            contents.fillRow(6, LobbySettings.handler().getSettings(player).getColor().glass());

            contents.set(1,5,  new PolarItem(Skull.getCustomSkull(PlayerHeadURLStorage.getInstance().getUrl(player.getUniqueId())))
                    .name("§8•● " + Prefix.getPrefix(player).get() + player.getName() + " §8●•"));

            contents.set(3,2, new ClickableItem(SkullStorage.getStorage().language, (event) -> {

            }));

            contents.set(3,5, new ClickableItem(SkullStorage.getStorage().friends,(event) -> {

            }));

            contents.set(3,8, new ClickableItem(SkullStorage.getStorage().settings, (event) -> {
                Lobby.getInstance().getInventoryHandler().getSettings().open(player);
            }));

            contents.set(4,4, new ClickableItem(SkullStorage.getStorage().party, (event) -> {

            }));

            contents.set(4,6, new ClickableItem(SkullStorage.getStorage().clan, (event) -> {

            }));

            contents.set(4,7, new ClickableItem(SkullStorage.getStorage().stats, (event) -> {

            }));

            contents.set(4,3, new ClickableItem(SkullStorage.getStorage().badges, event -> {

            }));

        }
    }

    public static class Settings{

        public static class MainProvider implements InventoryProvider {

            @Override
            public void init(Player player, InventoryContents contents) {
                contents.fillRow(1, LobbySettings.handler().getSettings(player).getColor().glass());
                contents.fillRow(5, LobbySettings.handler().getSettings(player).getColor().glass());

                contents.set(3,1, new ClickableItem( new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/11b3188fd44902f72602bd7c2141f5a70673a411adb3d81862c69e536166b"))
                        .name("§8•● §dLobby §8●•"), event -> {
                        Lobby.getInstance().getInventoryHandler().getSettingsLobby().open(player);
                }));

                contents.set(3,3, new ClickableItem(new PolarItem(Material.REPEATING_COMMAND_BLOCK)
                        .name("§8•● §dNetzwerk §8●•"), event -> {

                }));

                contents.set(3, 5, new ClickableItem(new PolarItem(Material.IRON_SWORD).name("§8•● §4The§cHunt §8●•").hideAttributes(),(event) -> {

                }));

                contents.set(3, 7, new ClickableItem(new PolarItem(Material.STICK).name("§8•● §6Switch§eFFA §8●•").hideAttributes(),(event) -> {

                }));
                contents.set(3, 9, new ClickableItem(new PolarItem(Material.DIAMOND_BOOTS).name("§8•● §3Jump§bCity §8●•").hideAttributes(),(event) -> {

                }));

                contents.set(5,9, new ClickableItem(new PolarItem(Material.BARRIER).name("§8•● §cZurück §8●•"),event -> {
                    InventoryManager.getDefaultManager().getInventory(player).flatMap(PolarInventory::getParent).ifPresent(cx -> cx.open(player));
                }));

            }
        }

        public static class LobbyProvider implements InventoryProvider {

            @Override
            public void init(Player player, InventoryContents contents) {

                ItemStack active = new PolarItem(Material.LIME_DYE).name("§8•● §aAktiviert §8●•");
                ItemStack inactive = new PolarItem(Material.RED_DYE).name("§8•● §cDeaktiviert §8●•");

                contents.fillRow(1, LobbySettings.handler().getSettings(player).getColor().glass());
                contents.fillRow(6, LobbySettings.handler().getSettings(player).getColor().glass());

                contents.set(3,2, new ClickableItem(new PolarItem(Material.GLOWSTONE_DUST)
                        .name("§8•● §dGlow §8●•").lore("§7Verügbar ab §6Fox§8."), event -> {
                }));

                contents.set(4,2, new ClickableItem(GlowAPI.isGlowing(player) ? active : inactive, event -> {
                    if(player.hasPermission("bytefox.glow")){

                        GlowAPI.changeGlow(player);
                        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);

                        if(GlowAPI.isGlowing(player)){
                            player.getOpenInventory().setItem(28, active);
                            contents.get(4,2).ifPresent(cr -> cr.setItemStack(active));
                            player.updateInventory();
                        }else{
                            player.getOpenInventory().setItem(28, inactive);
                            contents.get(4,2).ifPresent(cr -> cr.setItemStack(inactive));
                            player.updateInventory();
                        }

                    }else{
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                    }
                }));

                contents.set(6,9, new ClickableItem(new PolarItem(Material.BARRIER).name("§8•● §cZurück §8●•"),event -> {
                    InventoryManager.getDefaultManager().getInventory(player).flatMap(PolarInventory::getParent).ifPresent(cx -> cx.open(player));
                }));

            }

            @Override
            public void update(Player player, InventoryContents contents) {
            }
        }

        public static boolean getBooleanFromCloudPlayer(CloudPlayer player, String key, boolean defaultValue){
            if(player.getProperty(key, Boolean.TYPE) != null){
                return player.getProperty(key, Boolean.TYPE);
            }
            return defaultValue;
        }

    }


}
