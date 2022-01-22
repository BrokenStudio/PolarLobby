package dev.brokenstudio.polarlobby.byteplayer;

import java.util.HashMap;
import java.util.UUID;

public class IBytePlayerHandler {

    private final static HashMap<UUID, IBytePlayer> IBYTE_PLAYER_HASH_MAP = new HashMap<>();

    private static IBytePlayerHandler instance;

    public IBytePlayer getIBytePlayer(UUID uuid){
        return IBYTE_PLAYER_HASH_MAP.get(uuid);
    }

    public void setIBytePlayer(UUID uuid, IBytePlayer iBytePlayer){
        IBYTE_PLAYER_HASH_MAP.put(uuid, iBytePlayer);
    }

    public static IBytePlayerHandler getInstance() {
        if(instance == null) instance = new IBytePlayerHandler();
        return instance;
    }
}
