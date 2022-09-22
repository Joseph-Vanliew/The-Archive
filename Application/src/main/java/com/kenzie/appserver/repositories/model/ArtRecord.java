package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.ArtType;

@DynamoDBTable(tableName = "art")
public class ArtRecord {

    String artId;
    String name;
    String artistName;
    String locationId;
    ArtType type;
    boolean humiditySensitive;
    String timeStamp;
    String history;
    String timeSpentInStorage;


    @DynamoDBHashKey(attributeName = "artId")
    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "artistName")
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @DynamoDBAttribute(attributeName = "locationId")
    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @DynamoDBAttribute(attributeName = "type")
    public ArtType getType() {
        return type;
    }

    public void setType(ArtType type) {
        this.type = type;
    }

    @DynamoDBAttribute(attributeName = "humiditySensitive")
    public boolean isHumiditySensitive() {
        return humiditySensitive;
    }

    public void setHumiditySensitive(boolean humiditySensitive) {
        this.humiditySensitive = humiditySensitive;
    }

    @DynamoDBAttribute(attributeName = "timeStamp")
    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @DynamoDBAttribute(attributeName = "history")
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @DynamoDBAttribute(attributeName = "timeSpentInStorage")
    public String getTimeSpentInStorage() {
        return timeSpentInStorage;
    }

    public void setTimeSpentInStorage(String timeSpentInStorage) {
        this.timeSpentInStorage = timeSpentInStorage;
    }

    @Override
    public String toString() {
        return "ArtPieces{" +
                "artId='" + artId + '\'' +
                ", name='" + name + '\'' +
                ", artistName='" + artistName + '\'' +
                ", locationId='" + locationId + '\'' +
                ", type='" + type + '\'' +
                ", humiditySensitive=" + humiditySensitive +
                ", timeStamp='" + timeStamp + '\'' +
                ", history='" + history + '\'' +
                ", timeSpentInStorage='" + timeSpentInStorage + '\'' +
                '}';
    }


}

