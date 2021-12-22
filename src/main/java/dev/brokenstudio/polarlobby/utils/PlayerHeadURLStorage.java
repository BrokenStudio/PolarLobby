package dev.brokenstudio.polarlobby.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class PlayerHeadURLStorage {

    private static PlayerHeadURLStorage instance;

    private static final HashMap<UUID, String> urlMap = new HashMap<>();

    public String getUrl(UUID uuid){

        String url = urlMap.get(uuid);

        if(url == null){
            try {
                url = Skull.getTextureUrlByUuid(uuid.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return url;

    }

    public String loadUrl(UUID uuid){

        String url = "";

        try {
            url = Skull.getTextureUrlByUuid(uuid.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        urlMap.put(uuid,url);

        return url;

    }

    public static PlayerHeadURLStorage getInstance() {
        if(instance == null){
            instance = new PlayerHeadURLStorage();
        }
        return instance;
    }

}
