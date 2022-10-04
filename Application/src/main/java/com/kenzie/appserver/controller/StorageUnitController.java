package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.service.StorageUnitService;
import com.kenzie.appserver.service.model.Art;
import com.kenzie.appserver.service.model.StorageUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/storageUnit")
public class StorageUnitController {

    private final StorageUnitService storageUnitService;

    StorageUnitController(StorageUnitService storageUnitService) {
        this.storageUnitService = storageUnitService;
    }

    @GetMapping("/{unitId}")
    public ResponseEntity<StorageUnitResponse> get(@PathVariable("unitId") String unitId) {

        StorageUnit storageUnit = storageUnitService.findStorageUnitById(unitId);
        if (storageUnit == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(storageResponse(storageUnit));
    }

    @PostMapping
    public ResponseEntity<StorageUnitResponse> addNewStorageUnit(@RequestBody StorageUnitCreateRequest storageUnitCreateRequest) {
        StorageUnit storageUnit = new StorageUnit(storageUnitCreateRequest.getUnitId());
        storageUnitService.addNewStorageUnit(storageUnit);

        StorageUnitResponse storageUnitResponse = new StorageUnitResponse();
        storageUnitResponse.setUnitId(storageUnit.getUnitId());
        storageUnitResponse.setArtType(storageUnit.getArtType());
        storageUnitResponse.setHumiditySensitive(storageUnit.getHumiditySensitive());
        storageUnitResponse.setAmountOfArtStored(storageUnit.getAmountOfArtStored());

        return ResponseEntity.created(URI.create("/storageUnit/" + storageUnitResponse.getUnitId())).body(storageUnitResponse);
    }

    @GetMapping
    public ResponseEntity<List<StorageUnitResponse>> getAllUnits() {
        List<StorageUnit> storageUnits = storageUnitService.findAllStorageUnits();
        if (storageUnits == null || storageUnits.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<StorageUnitResponse> responseUnits = new ArrayList<>();

        for (StorageUnit unit : storageUnits) {
            responseUnits.add(storageResponse(unit));
        }

        return ResponseEntity.ok(responseUnits);
    }

    @PutMapping
    public ResponseEntity<StorageUnitResponse> updateStorageUnit(@RequestBody StorageUnitUpdateRequest storageUnitUpdateRequest) {
        StorageUnit storageUnit = new StorageUnit(storageUnitUpdateRequest.getUnitId(),
                storageUnitUpdateRequest.getArtType(),
                storageUnitUpdateRequest.getHumiditySensitive(),
                storageUnitUpdateRequest.getAmountOfArtStored());
        storageUnitService.updateStorageUnit(storageUnit);

        StorageUnitResponse storageUnitResponse = storageResponse(storageUnit);

        return ResponseEntity.ok(storageUnitResponse);
    }

    @DeleteMapping("/{unitId}")
    public ResponseEntity removeArt(@PathVariable("unitId") String unitId) {
        storageUnitService.deleteStorageUnit(unitId);
        return ResponseEntity.noContent().build();
    }
    private StorageUnitResponse storageResponse(StorageUnit storageUnit) {
        StorageUnitResponse response = new StorageUnitResponse();
        response.setUnitId(storageUnit.getUnitId());
        response.setArtType(storageUnit.getArtType());
        response.setHumiditySensitive(storageUnit.getHumiditySensitive());
        response.setAmountOfArtStored(storageUnit.getAmountOfArtStored());
        return response;
    }
}
