package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.ArtType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class StorageUnitCreateRequest {

    @NotEmpty
    @JsonProperty("id")
    private String unitId;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonProperty("artType")
    private ArtType artType;

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
