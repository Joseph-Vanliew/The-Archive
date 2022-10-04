package com.kenzie.appserver.service;


import com.kenzie.appserver.repositories.StorageUnitRepository;
import com.kenzie.appserver.repositories.model.StorageUnitRecord;
import com.kenzie.appserver.service.model.StorageUnit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageUnitService {
    private final StorageUnitRepository storageUnitRepository;
    // Add cache store class for data management...

    public StorageUnitService(StorageUnitRepository storageUnitRepository) {
        this.storageUnitRepository = storageUnitRepository;
    }

    public List<StorageUnit> findAllStorageUnits() {

        List<StorageUnit> storageUnits = new ArrayList<>();
        Iterable<StorageUnitRecord> storageUnitRecordIterator = storageUnitRepository.findAll();

        for(StorageUnitRecord record : storageUnitRecordIterator){
        storageUnits.add(new StorageUnit(record.getUnitId(),
                record.getArtType(),
                record.getHumiditySensitive(),
                record.getAmountOfArtStored()));
        }

        return storageUnits;
    }

    public StorageUnit findStorageUnitById(String unitId) {
        //implement cache and cache check later...

        StorageUnit storageUnitFromRepository = storageUnitRepository.findById(unitId)
                .map(storageUnit -> new StorageUnit(storageUnit.getUnitId(),
                        storageUnit.getArtType(),
                        storageUnit.getHumiditySensitive(),
                        storageUnit.getAmountOfArtStored()))
                .orElse(null);

        //implement cache if found then cache logic.
        return storageUnitFromRepository;
    }

    public StorageUnit addNewStorageUnit(StorageUnit storageUnit) {
        StorageUnitRecord storageUnitRecord = new StorageUnitRecord();
        storageUnitRecord.setUnitId(storageUnit.getUnitId());
        storageUnitRecord.setArtType(storageUnit.getArtType());
        storageUnitRecord.setHumiditySensitive(storageUnit.getHumiditySensitive());
        storageUnitRecord.setAmountOfArtStored(storageUnit.getAmountOfArtStored());
        storageUnitRepository.save(storageUnitRecord);

        return storageUnit;
    }
    public void updateStorageUnit(StorageUnit storageUnit) {
        if(storageUnitRepository.existsById(storageUnit.getUnitId())) {
            StorageUnitRecord storageUnitRecord = new StorageUnitRecord();
            storageUnitRecord.setUnitId(storageUnit.getUnitId());
            storageUnitRecord.setArtType(storageUnit.getArtType());
            storageUnitRecord.setHumiditySensitive(storageUnit.getHumiditySensitive());
            storageUnitRecord.setAmountOfArtStored(storageUnit.getAmountOfArtStored());
            storageUnitRepository.save(storageUnitRecord);
        }
    }

    public void deleteStorageUnit(String unitId){
    storageUnitRepository.deleteById(unitId);
    }
}
