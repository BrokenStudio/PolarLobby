package dev.brokenstudio.polarlobby.bits;

import dev.brokenstudio.cloud.cloudplayer.CloudPlayer;

import java.util.HashMap;
import java.util.UUID;

public class BitsHandler {

    private final static HashMap<UUID, Integer> bits = new HashMap<>();

    private static BitsHandler instance;

    public void loadPlayer(CloudPlayer cloudPlayer){
        if(cloudPlayer.getProperty("bits", Integer.TYPE) != null){
            BitsHandler.bits.put(cloudPlayer.getUUID(), cloudPlayer.getProperty("bits", Integer.TYPE));
        }else{
            setBits(cloudPlayer.getUUID(), 0);
        }
    }

    public void savePlayer(CloudPlayer cloudPlayer){
        cloudPlayer.setProperty("bits", BitsHandler.bits.get(cloudPlayer.getUUID()));
    }

    public int getBits(UUID uuid){
        return BitsHandler.bits.get(uuid);
    }

    public void setBits(UUID uuid, int bits){
        BitsHandler.bits.put(uuid, bits);
    }

    public void addBits(UUID uuid, int bits){
        setBits(uuid, getBits(uuid) + bits);
    }

    public void removeBits(UUID uuid, int bits){
        if(getBits(uuid) - bits < 0)return;
        setBits(uuid, getBits(uuid) - bits);
    }

    public static BitsHandler getInstance(){
        if(instance == null)
            instance = new BitsHandler();
        return instance;
    }

}
