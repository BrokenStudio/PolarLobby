package dev.brokenstudio.polarlobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class JsonLocation {

    private final String world;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    public JsonLocation(String world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public JsonLocation(Location location){
        this.world = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public Location toLocation(){
        Location location = new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z);
        location.setPitch(this.pitch);
        location.setYaw(this.yaw);
        return location;
    }

}
