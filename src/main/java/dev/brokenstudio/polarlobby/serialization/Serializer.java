package dev.brokenstudio.polarlobby.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializer {

    public static final Gson gson = new GsonBuilder().create();
    public static final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

}
