package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.StorageUnitCreateRequest;
import com.kenzie.appserver.controller.model.StorageUnitUpdateRequest;
import com.kenzie.appserver.service.StorageUnitService;

import com.kenzie.appserver.service.model.StorageUnit;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class StorageUnitControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    StorageUnitService storageUnitService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getStorageUnit_StorageUnitExists() throws Exception {
        // GIVEN
        String unitId = UUID.randomUUID().toString();
        String artType = mockNeat.strings().valStr();
        boolean humiditySensitive = true;
        int amountOfArtStored = 10;

        StorageUnit storageUnit = new StorageUnit(unitId, artType, humiditySensitive, amountOfArtStored);
        StorageUnit persistedStorageUnit = storageUnitService.addNewStorageUnit(storageUnit);

        // WHEN
        mvc.perform(get("/storageUnit/{unitId}", persistedStorageUnit.getUnitId())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(jsonPath("unitId")
                        .value(is(unitId)))
                .andExpect(jsonPath("artType")
                        .value(is(artType)))
                .andExpect(jsonPath("humiditySensitive")
                        .value(is(humiditySensitive)))
                .andExpect(jsonPath("amountOfArtStored")
                        .value(is(amountOfArtStored)))
                .andExpect(status().isOk());
    }

    @Test
    public void getStorageUnit_StorageUnitDoesNotExists() throws Exception {
        // GIVEN
        String unitId = UUID.randomUUID().toString();
        // WHEN
        mvc.perform(get("/storageUnit/{unitId}", unitId)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());
    }

    @Test
    public void createStorageUnit_CreateSuccessful() throws Exception {
        // GIVEN
        String unitId = UUID.randomUUID().toString();
        String artType = mockNeat.strings().valStr();
        boolean humiditySensitive = true;
        int amountOfArtStored = 10;

        StorageUnitCreateRequest storageUnitCreateRequest = new StorageUnitCreateRequest();
        storageUnitCreateRequest.setUnitId(unitId);
        storageUnitCreateRequest.setArtType(artType);
        storageUnitCreateRequest.setHumiditySensitive(humiditySensitive);
        storageUnitCreateRequest.setAmountOfArtStored(amountOfArtStored);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/storageUnit")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(storageUnitCreateRequest)))
                // THEN
                .andExpect(jsonPath("unitId")
                        .exists())
                .andExpect(jsonPath("artType")
                        .value(is(artType)))
                .andExpect(jsonPath("humiditySensitive")
                        .value(is(humiditySensitive)))
                .andExpect(jsonPath("amountOfArtStored")
                        .value(is(amountOfArtStored)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateStorageUnit_PutSuccessful() throws Exception {
        // GIVEN
        String unitId = UUID.randomUUID().toString();
        String artType = mockNeat.strings().valStr();
        boolean humiditySensitive = true;
        int amountOfArtStored = 10;

        StorageUnit storageUnit = new StorageUnit(unitId, artType, humiditySensitive, amountOfArtStored);
        storageUnitService.addNewStorageUnit(storageUnit);

        boolean newHumiditySensitive = false;
        int newAmountOfArtStored = 15;

        StorageUnitUpdateRequest storageUnitUpdateRequest = new StorageUnitUpdateRequest();
        storageUnitUpdateRequest.setUnitId(unitId);
        storageUnitUpdateRequest.setArtType(artType);
        storageUnitUpdateRequest.setHumiditySensitive(newHumiditySensitive);
        storageUnitUpdateRequest.setAmountOfArtStored(newAmountOfArtStored);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(put("/storageUnit")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(storageUnitUpdateRequest)))
                // THEN
                .andExpect(jsonPath("unitId")
                        .exists())
                .andExpect(jsonPath("artType")
                        .value(is(artType)))
                .andExpect(jsonPath("humiditySensitive")
                        .value(is(newHumiditySensitive)))
                .andExpect(jsonPath("amountOfArtStored")
                        .value(is(newAmountOfArtStored)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStorageUnit_DeleteSuccessful() throws Exception {
        // GIVEN
        String unitId = UUID.randomUUID().toString();
        String artType = mockNeat.strings().valStr();
        boolean humiditySensitive = true;
        int amountOfArtStored = 10;

        StorageUnit storageUnit = new StorageUnit(unitId, artType, humiditySensitive, amountOfArtStored);
        StorageUnit persistedStorageUnit = storageUnitService.addNewStorageUnit(storageUnit);

        // WHEN
        mvc.perform(delete("/storageUnit{unitId}", persistedStorageUnit.getUnitId())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNoContent());
        assertThat(storageUnitService.findStorageUnitById(unitId)).isNull();
    }
}
