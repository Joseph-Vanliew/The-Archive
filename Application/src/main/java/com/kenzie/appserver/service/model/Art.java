package com.kenzie.appserver.service.model;

public class Art {
    String artId;
    String name;
    String artistName;
    String locationId;
    ArtType type;
    boolean humiditySensitive;
    String timeStamp;
    Double price;

    public Art() {
    }

    public Art(String artId, String name, String artistName, String locationId, ArtType type,
               boolean humiditySensitive, String timeStamp) {
        this.artId = artId;
        this.name = name;
        this.artistName = artistName;
        this.locationId = locationId;
        this.type = type;
        this.humiditySensitive = humiditySensitive;
        this.timeStamp = timeStamp;
        this.price = price;
    }

    public Art(String artId) {
        this.artId = artId;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public ArtType getType() {
        return type;
    }

    public void setType(ArtType type) {
        this.type = type;
    }

    public boolean isHumiditySensitive() {
        return humiditySensitive;
    }

    public void setHumiditySensitive(boolean humiditySensitive) {
        this.humiditySensitive = humiditySensitive;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
