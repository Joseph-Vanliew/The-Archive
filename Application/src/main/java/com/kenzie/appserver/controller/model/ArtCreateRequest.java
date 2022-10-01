package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.ArtType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class ArtCreateRequest {

    @JsonProperty("name")
    String name;

    @JsonProperty("artistName")
    String artistName;

    @NotEmpty
    @JsonProperty("locationId")
    String locationId;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonProperty("type")
    ArtType type;

    @JsonProperty("humiditySensitive")
    boolean humiditySensitive;

    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("timeStamp")
    LocalDate timeStamp;

    @Min(0)
    @JsonProperty("price")
    Double price;

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

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
