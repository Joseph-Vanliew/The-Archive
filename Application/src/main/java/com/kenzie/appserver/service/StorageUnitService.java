package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.StorageUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageUnitService {
    private StorageUnitRepository storageUnitRepository;
    // Add cache store class for data management...


    public StorageUnitService(StorageUnitRepository storageUnitRepository) {
        this.storageUnitRepository = storageUnitRepository;
    }

    /* methods to add include
    * findAllStorageUnits
    * findByStorageUnitId
    * addNewStorageUnit
    * updateStorageUnit
    * deleteStorageUnit */
}
