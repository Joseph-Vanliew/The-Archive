package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.ArtType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StorageUnitResponse {

    @JsonProperty("id")
    private String unitId;

    @JsonProperty("artType")
    private ArtType artType;

    @JsonProperty("humiditySensitive")
    private Boolean humiditySensitive;

    @JsonProperty("amountOfArtStored")
    private int amountOfArtStored;

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
