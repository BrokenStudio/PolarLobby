package dev.brokenstudio.polarlobby.inventories;

import dev.brokenstudio.cloud.scoreboard.Prefix;
import dev.brokenstudio.polarinvs.ClickableItem;
import dev.brokenstudio.polarinvs.content.InventoryContents;
import dev.brokenstudio.polarinvs.content.InventoryProvider;
import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.player.LobbySettings;
import dev.brokenstudio.polarlobby.utils.PlayerHeadURLStorage;
import dev.brokenstudio.polarlobby.utils.PolarItem;
import dev.brokenstudio.polarlobby.utils.Skull;
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

            contents.set(3,2, new ClickableItem( new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/5e7899b4806858697e283f084d9173fe487886453774626b24bd8cfecc77b3f"))
                    .name("§8•● §dSprache §8●"), (event) -> {

            }));

            contents.set(3,5, new ClickableItem(new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/1b2869abdfc6a7b0c025ce29ff81ae11fdd3899b5e63ea8915e8ff315b59b0"))
                    .name("§8•● §dFreunde §8●•"),(event) -> {

            }));

            contents.set(3,8, new ClickableItem( new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/30becf2754bb793fc2f73f604cfd666dcc76e809618cbf397886846542bda785"))
                    .name("§8•● §dEinstellungen §8●•"), (event) -> {

            }));

            contents.set(4,4, new ClickableItem( new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/7c5fd3bd1f3b4be7aa886a6fb06cd916775c4d9e7a7283ac99cae171e971f1"))
                    .name("§8•● §dParty §8●•"), (event) -> {

            }));

            contents.set(4,6, new ClickableItem( new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/b6193c2830c52af546fe6d18000a4f27e48d22f2496dd42073b9f209c955fdb5"))
                    .name("§8•● §dClan §8●•"), (event) -> {

            }));

            contents.set(4,7, new ClickableItem( new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/a1d1cf289165fbb112d8921d47708e49fb70925739b1cbd1798daff422806e8a"))
                    .name("§8•● §dStats §8●•"), (event) -> {

            }));

            contents.set(4,3, new ClickableItem(new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/db3c3485cbd1e1162a7d9ec9db8b9c7ee4a3f983ec770c6aaafaf477f50b5"))
                    .name("§8•● §dBadges §8●•"), event -> {

            }));

        }
    }


}
