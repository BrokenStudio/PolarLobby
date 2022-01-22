package dev.brokenstudio.polarlobby.byteplayer;

import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.cosmetics.AbstractCosmetic;
import dev.brokenstudio.polarlobby.cosmetics.NoneCosmetic;
import dev.brokenstudio.polarlobby.serialization.Serializer;

import java.io.Serializable;
import java.util.UUID;

public class IBytePlayer {

    private static class IBytePlayerJson implements Serializable {
        private UUID uuid;
        private String helmet;
        private String cloth;
        private String particles;
        private String pet;
        private String gadget;

        public IBytePlayerJson(){

        }

        public IBytePlayerJson(UUID uuid, String helmet, String cloth, String particles, String pet, String gadget) {
            this.uuid = uuid;
            this.helmet = helmet;
            this.cloth = cloth;
            this.particles = particles;
            this.pet = pet;
            this.gadget = gadget;
        }
    }

    private UUID uuid;

    private AbstractCosmetic helmet;

    private AbstractCosmetic cloth;

    private AbstractCosmetic particles;

    private AbstractCosmetic pet;

    private AbstractCosmetic gadget;

    public IBytePlayer(UUID uuid) {
        this.uuid = uuid;
        this.helmet = NoneCosmetic.getNone();
        this.cloth = NoneCosmetic.getNone();
        this.particles = NoneCosmetic.getNone();
        this.pet = NoneCosmetic.getNone();
        this.gadget = NoneCosmetic.getNone();
    }

    private IBytePlayer(UUID uuid, String helmet, String cloth, String particles, String pet, String gadget) {
        this.uuid = uuid;
        this.helmet = Lobby.getInstance().getCosmeticHandler().getCosmetic(helmet);
        this.cloth = Lobby.getInstance().getCosmeticHandler().getCosmetic(cloth);;
        this.particles = Lobby.getInstance().getCosmeticHandler().getCosmetic(particles);;
        this.pet = Lobby.getInstance().getCosmeticHandler().getCosmetic(pet);;
        this.gadget = Lobby.getInstance().getCosmeticHandler().getCosmetic(gadget);;
    }

    public static IBytePlayerHandler handler(){
        return IBytePlayerHandler.getInstance();
    }

    public String toJson(){
        IBytePlayerJson j = new IBytePlayerJson(uuid, helmet.getName(), cloth.getName(), particles.getName(), pet.getName(), gadget.getName());
        return Serializer.gson.toJson(j);
    }

    public static IBytePlayer fromJson(String json){
        IBytePlayerJson j = Serializer.gson.fromJson(json, IBytePlayerJson.class);
        return new IBytePlayer(j.uuid, j.helmet, j.cloth, j.particles, j.pet, j.gadget);
    }

    public UUID getUuid() {
        return uuid;
    }

    public AbstractCosmetic getHelmet() {
        return helmet;
    }

    public void setHelmet(AbstractCosmetic helmet) {
        this.helmet = helmet;
    }

    public AbstractCosmetic getCloth() {
        return cloth;
    }

    public void setCloth(AbstractCosmetic cloth) {
        this.cloth = cloth;
    }

    public AbstractCosmetic getParticles() {
        return particles;
    }

    public void setParticles(AbstractCosmetic particles) {
        this.particles = particles;
    }

    public AbstractCosmetic getPet() {
        return pet;
    }

    public void setPet(AbstractCosmetic pet) {
        this.pet = pet;
    }

    public AbstractCosmetic getGadget() {
        return gadget;
    }

    public void setGadget(AbstractCosmetic gadget) {
        this.gadget = gadget;
    }
}
