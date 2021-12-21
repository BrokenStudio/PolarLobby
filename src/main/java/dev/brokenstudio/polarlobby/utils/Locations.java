package dev.brokenstudio.polarlobby.utils;

import com.google.common.annotations.Beta;
import com.google.common.reflect.TypeToken;
import dev.brokenstudio.polarlobby.serialization.Serializer;
import org.bukkit.Location;

import java.lang.reflect.Type;
import java.util.HashMap;

@Beta
public class Locations {

    private static final Type mapType = new TypeToken<HashMap<String, JsonLocation>>() {}.getType();

    private final HashMap<String, Location> locations;

    public Locations(){
        locations = new HashMap<>();
    }

    public Locations(HashMap<String, Location> locs){
        locations = locs;
    }

    public void setLocation(String name, Location location){
        locations.put(name, location);
    }

    public Location getLocation(String name){
        return locations.get(name);
    }

    public String toJson(){
        HashMap<String, JsonLocation> locs = new HashMap<>();
        locations.keySet().forEach(cr -> locs.put(cr, new JsonLocation(locations.get(cr))));
        return Serializer.prettyGson.toJson(locs);
    }

    public static Locations fromJson(String json){
        HashMap<String, JsonLocation> jsonLocs = Serializer.gson.fromJson(json, mapType);
        HashMap<String, Location> locs = new HashMap<>();
        jsonLocs.keySet().forEach(cr -> locs.put(cr, jsonLocs.get(cr).toLocation()));
        return new Locations(locs);
    }

}
