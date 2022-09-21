package com.kenzie.appserver.service.model;

public class StorageUnit {

    private String unitId;
    private ArtType artType;
    private Boolean humiditySensitive;
    private int amountOfArtStored;

    public StorageUnit(String unitId, ArtType artType, Boolean humiditySensitive, int amountOfArtStored) {
        this.unitId = unitId;
        this.artType = artType;
        this.humiditySensitive = humiditySensitive;
        this.amountOfArtStored = amountOfArtStored;
    }

    public StorageUnit(String unitId) {
        this.unitId = unitId;
    }

    public StorageUnit() {

    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public ArtType getArtType() {
        return artType;
    }

    public void setArtType(ArtType artType) {
        this.artType = artType;
    }

    public Boolean getHumiditySensitive() {
        return humiditySensitive;
    }

    public void setHumiditySensitive(Boolean humiditySensitive) {
        this.humiditySensitive = humiditySensitive;
    }

    public int getAmountOfArtStored() {
        return amountOfArtStored;
    }

    public void setAmountOfArtStored(int amountOfArtStored) {
        this.amountOfArtStored = amountOfArtStored;
    }
}
