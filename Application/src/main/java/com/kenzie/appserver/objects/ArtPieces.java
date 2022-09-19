package com.kenzie.appserver.objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "ArtPieces")
public class ArtPieces {

    String artId;
    String name;
    String artistName;
    String locationId;
    String medium;
    String type;
    boolean humiditySensitive;
    String dateStored;
    String history;
    String timeSpentInStorage;

    public ArtPieces(String artId, String name, String artistName, String locationId, String medium, String type,
                     boolean humiditySensitive, String dateStored, String history, String timeSpentInStorage) {
        this.artId = artId;
        this.name = name;
        this.artistName = artistName;
        this.locationId = locationId;
        this.medium = medium;
        this.type = type;
        this.humiditySensitive = humiditySensitive;
        this.dateStored = dateStored;
        this.history = history;
        this.timeSpentInStorage = timeSpentInStorage;
    }

    public ArtPieces(String artId) {
        this.artId = artId;

    }

    public ArtPieces() {
    }

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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
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

    public String getDateStored() {
        return dateStored;
    }

    public void setDateStored(String dateStored) {
        this.dateStored = dateStored;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

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
                ", medium='" + medium + '\'' +
                ", type='" + type + '\'' +
                ", humiditySensitive=" + humiditySensitive +
                ", dateStored='" + dateStored + '\'' +
                ", history='" + history + '\'' +
                ", timeSpentInStorage='" + timeSpentInStorage + '\'' +
                '}';
    }
}
