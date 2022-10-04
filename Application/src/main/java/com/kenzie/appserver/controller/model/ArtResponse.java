package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtResponse {
    @JsonProperty("artId")
    String artId;

    @JsonProperty("name")
    String name;

    @JsonProperty("artistName")
    String artistName;

    @JsonProperty("locationId")
    String locationId;

    @JsonProperty("type")
    String type;

    @JsonProperty("humiditySensitive")
    boolean humiditySensitive;

    @JsonProperty("timeStamp")
    String timeStamp;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHumiditySensitive() {
        return humiditySensitive;
    }

    public void setHumiditySensitive(boolean humiditySensitive) {
        this.humiditySensitive = humiditySensitive;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
