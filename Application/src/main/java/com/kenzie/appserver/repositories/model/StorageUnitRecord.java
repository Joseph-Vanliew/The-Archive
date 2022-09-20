package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.ArtType;

import java.util.Objects;

@DynamoDBTable(tableName = "StorageUnit")
public class StorageUnitRecord {

    private String unitId;
    private ArtType artType;
    private Boolean humiditySensitive;
    private int amountOfArtStored;


    @DynamoDBHashKey(attributeName = "Id")
    public String getUnitId() {
        return unitId;
    }
    @DynamoDBAttribute(attributeName = "ArtType")
    public ArtType getArtType() {
        return artType;
    }
    @DynamoDBAttribute(attributeName = "HumiditySensitive")
    public Boolean getHumiditySensitive() {
        return humiditySensitive;
    }
    @DynamoDBAttribute(attributeName = "AmountOfArt")
    public int getAmountOfArtStored() {
        return amountOfArtStored;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public void setArtType(ArtType artType) {
        this.artType = artType;
    }

    public void setHumiditySensitive(Boolean humiditySensitive) {
        this.humiditySensitive = humiditySensitive;
    }

    public void setAmountOfArtStored(int amountOfArtStored) {
        this.amountOfArtStored = amountOfArtStored;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageUnitRecord that = (StorageUnitRecord) o;
        return unitId.equals(that.unitId) && artType.equals(that.artType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitId, artType);
    }
}
