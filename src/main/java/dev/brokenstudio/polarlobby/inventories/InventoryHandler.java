package dev.brokenstudio.polarlobby.inventories;

import dev.brokenstudio.polarinvs.PolarInventory;

public class InventoryHandler {

    private PolarInventory navigator;
    private PolarInventory hider;

    public InventoryHandler() {
        _initialize();
    }

    private void _initialize(){
        navigator = PolarInventory.builder().id("navigator").size(5,9).title("§8•● §5Navigator §8●•").provider(new InventoryProviders.NavigatorProvider()).build();
        hider = PolarInventory.builder().id("hider").size(3,9).title("§8•● §5Spielder §dverstecken §8●•").provider(new InventoryProviders.HiderProvider()).build();
    }

    public PolarInventory getNavigator() {
        return navigator;
    }

    public PolarInventory getHider() {
        return hider;
    }
}
