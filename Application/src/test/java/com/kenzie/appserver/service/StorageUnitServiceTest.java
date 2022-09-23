package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ArtRepository;
import com.kenzie.appserver.repositories.StorageUnitRepository;
import com.kenzie.appserver.repositories.model.StorageUnitRecord;
import com.kenzie.appserver.repositories.model.StorageUnitRecord;
import com.kenzie.appserver.repositories.model.StorageUnitRecord;
import com.kenzie.appserver.service.model.Art;
import com.kenzie.appserver.service.model.StorageUnit;
import com.kenzie.appserver.service.model.ArtType;
import com.kenzie.appserver.service.model.StorageUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class StorageUnitServiceTest {
    private StorageUnitRepository storageUnitRepository;
    private StorageUnitService storageUnitService;


    @BeforeEach
    void setup() {
        storageUnitRepository = mock(StorageUnitRepository.class);
        storageUnitService = new StorageUnitService(storageUnitRepository);
    }

    @Test
    void findAllStorageUnitsTest() {
        StorageUnitRecord storageUnitRecord = new StorageUnitRecord();
        storageUnitRecord.setUnitId("storageUnitID");
        storageUnitRecord.setArtType(ArtType.INK);
        storageUnitRecord.setAmountOfArtStored(1);
        storageUnitRecord.setHumiditySensitive(true);

        StorageUnitRecord storageUnitRecord1 = new StorageUnitRecord();
        storageUnitRecord1.setUnitId("storageUnitID1");
        storageUnitRecord1.setArtType(ArtType.INK);
        storageUnitRecord1.setAmountOfArtStored(10);
        storageUnitRecord1.setHumiditySensitive(false);


        List<StorageUnitRecord> storageUnitRecords = new ArrayList<>();
        storageUnitRecords.add(storageUnitRecord1);
        storageUnitRecords.add(storageUnitRecord);

        when(storageUnitRepository.findAll()).thenReturn(storageUnitRecords);

        //When
        List<StorageUnit> storageUnitList = storageUnitService.findAllStorageUnits();

        //Then
        Assertions.assertNotNull(storageUnitList, "The list is not empty");
        Assertions.assertEquals(2, storageUnitList.size(), "the storage unit list size is 2");

        for (StorageUnit storageUnit : storageUnitList) {
            if (storageUnit.getUnitId() == storageUnitRecord1.getUnitId()) {
                Assertions.assertEquals(storageUnitRecord1.getAmountOfArtStored(), storageUnit.getAmountOfArtStored(), "The amount of art in storage matches");
                Assertions.assertEquals(storageUnitRecord1.getArtType(), storageUnit.getArtType(), "The storage art type matches");
                Assertions.assertEquals(storageUnitRecord1.getHumiditySensitive(), storageUnit.getHumiditySensitive(), "The humidity matches");

            } else if (storageUnit.getUnitId() == storageUnitRecord.getUnitId()) {
                Assertions.assertEquals(storageUnitRecord.getAmountOfArtStored(), storageUnit.getAmountOfArtStored(), "The amount of art in storage matches");
                Assertions.assertEquals(storageUnitRecord.getArtType(), storageUnit.getArtType(), "The storage art type matches");
                Assertions.assertEquals(storageUnitRecord.getHumiditySensitive(), storageUnit.getHumiditySensitive(), "The humidity matches");

            } else {
                Assertions.assertTrue(false, "Concert returned that was not in the records!");
            }
        }

    }

    @Test
    void findStorageUnitByIdTest() {
        String storageUnitId = randomUUID().toString();

        StorageUnitRecord storageUnitRecord = new StorageUnitRecord();
        storageUnitRecord.setUnitId(storageUnitId);
        storageUnitRecord.setHumiditySensitive(true);
        storageUnitRecord.setArtType(ArtType.OIL);
        storageUnitRecord.setAmountOfArtStored(20);

        when(storageUnitRepository.findById(storageUnitRecord.getUnitId())).thenReturn(Optional.of(storageUnitRecord));
        // WHEN
        StorageUnit storageUnit = storageUnitService.findStorageUnitById(storageUnitRecord.getUnitId());

        // THEN
        Assertions.assertNotNull(storageUnit, "The storage unit is returned");
        Assertions.assertEquals(storageUnitRecord.getUnitId(), storageUnit.getUnitId(), "The storage unit id matches");
        Assertions.assertEquals(storageUnitRecord.getArtType(), storageUnit.getArtType(), "The art type matches");
        Assertions.assertEquals(storageUnitRecord.getHumiditySensitive(), storageUnit.getHumiditySensitive(), "The humidity matches");
        Assertions.assertEquals(storageUnitRecord.getAmountOfArtStored(), storageUnit.getAmountOfArtStored(), "The amount of art stored matches");

    }

    @Test
    void addNewStorageUnitTest() {

        String storageUnitId = randomUUID().toString();

        StorageUnit storageUnit = new StorageUnit(storageUnitId);
        storageUnit.setAmountOfArtStored(10);

        ArgumentCaptor<StorageUnitRecord> storageUnitRecordArgumentCaptor = ArgumentCaptor.forClass(StorageUnitRecord.class);

        // WHEN
        StorageUnit returnedStorageUnit = storageUnitService.addNewStorageUnit(storageUnit);

        // THEN
        Assertions.assertNotNull(returnedStorageUnit);

        verify(storageUnitRepository).save(storageUnitRecordArgumentCaptor.capture());

        StorageUnitRecord record = storageUnitRecordArgumentCaptor.getValue();

        Assertions.assertNotNull(record, "The storage record is returned");
        Assertions.assertEquals(record.getUnitId(), storageUnit.getUnitId(), "The storage unit id matches");
        Assertions.assertEquals(record.getArtType(), storageUnit.getArtType(), "The art type matches");
        Assertions.assertEquals(record.getAmountOfArtStored(), storageUnit.getAmountOfArtStored(), "The amount of art stored matches");
        Assertions.assertEquals(record.getHumiditySensitive(), storageUnit.getHumiditySensitive(), "The humidity matches");

    }

    @Test
    void updateStorageUnitTest() {
        //Given
        String storageId = "storageId";

        StorageUnit storageUnit = new StorageUnit(storageId);
        storageUnit.setUnitId(storageId);

        StorageUnitRecord storageUnitRecord = new StorageUnitRecord();
        storageUnitRecord.setUnitId(storageUnit.getUnitId());


        when(storageUnitRepository.existsById(storageUnit.getUnitId())).thenReturn(true);

        //When
        storageUnitService.updateStorageUnit(storageUnit);

        //Then
        verify(storageUnitRepository, times(1)).existsById(storageUnitRecord.getUnitId());
    }

    @Test
    void deleteStorageUnitTest() {
        //Given then and when
        storageUnitService.deleteStorageUnit("storageUnitToRemove");

        verify(storageUnitRepository, times(1)).deleteById("storageUnitToRemove");
    }
}