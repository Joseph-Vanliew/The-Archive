package com.kenzie.appserver.controller.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class StorageUnitCreateRequest {

    @NotEmpty
    @JsonProperty("unitId")
    private String unitId;

    @JsonProperty("artType")
    private String artType;

    @JsonProperty("humiditySensitive")
    private Boolean humiditySensitive;

    @Min(0)
    @JsonProperty("amountOfArtStored")
    private int amountOfArtStored;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
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
