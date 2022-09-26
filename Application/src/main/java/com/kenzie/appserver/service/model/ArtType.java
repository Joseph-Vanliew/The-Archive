package com.kenzie.appserver.service.model;
import java.util.Random;


public enum ArtType {
    SCULPTURE,
    OIL,
    ACRYLIC,
    WATERCOLOR,
    GOUACHE,
    INK;

    public static ArtType getRandomMedium() {
        Random random = new Random();
        ArtType[] artTypes = values();
        return values()[random.nextInt(values().length)];
    }
}
