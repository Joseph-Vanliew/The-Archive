package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.ArtCreateRequest;
import com.kenzie.appserver.controller.model.ArtUpdateRequest;
import com.kenzie.appserver.service.ArtService;
import com.kenzie.appserver.service.model.Art;
import com.kenzie.appserver.service.model.ArtType;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static com.kenzie.appserver.service.model.ArtType.getRandomMedium;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class ArtControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ArtService artService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getConcert_ConcertExists() throws Exception {
        // GIVEN
        String artId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String artistName = mockNeat.strings().valStr();
        String locationId = mockNeat.strings().valStr();
        ArtType type = getRandomMedium();
        boolean humiditySensitive = true;
        String timeStamp = LocalDate.now().toString();
        String history = mockNeat.strings().valStr();
        String timeSpentInStorage = mockNeat.strings().valStr();;

        Art art = new Art(artId, name, artistName, locationId, type, humiditySensitive, timeStamp);
        Art persistedArt = artService.addNewArt(art);

        // WHEN
        mvc.perform(get("/art/{artId}", persistedArt.getArtId())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(jsonPath("artId")
                        .value(is(artId)))
                .andExpect(jsonPath("name")
                        .value(is(name)))
                .andExpect(jsonPath("artistName")
                        .value(is(artistName)))
                .andExpect(jsonPath("locationId")
                        .value(is(locationId)))
                .andExpect(jsonPath("type")
                        .value(is(type)))
                .andExpect(jsonPath("humiditySensitive")
                        .value(is(humiditySensitive)))
                .andExpect(jsonPath("timeStamp")
                        .value(is(timeStamp)))
                .andExpect(jsonPath("history")
                        .value(is(history)))
                .andExpect(jsonPath("timeSpentInStorage")
                        .value(is(timeSpentInStorage)))
                .andExpect(status().isOk());
    }

    @Test
    public void getArt_ArtDoesNotExist() throws Exception {
        // GIVEN
        String artId = UUID.randomUUID().toString();
        // WHEN
        mvc.perform(get("/art/{artId}", artId)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());
    }

    @Test
    public void createArt_CreateSuccessful() throws Exception {
        // GIVEN
        String artId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String artistName = mockNeat.strings().valStr();
        String locationId = mockNeat.strings().valStr();
        ArtType type = getRandomMedium();
        boolean humiditySensitive = true;
        String timeStamp = LocalDate.now().toString();
        String history = mockNeat.strings().valStr();
        String timeSpentInStorage = mockNeat.strings().valStr();;

        ArtCreateRequest artCreateRequest = new ArtCreateRequest();
        artCreateRequest.setArtId(artId);
        artCreateRequest.setName(name);
        artCreateRequest.setArtistName(artistName);
        artCreateRequest.setLocationId(locationId);
        artCreateRequest.setType(type);
        artCreateRequest.setHumiditySensitive(humiditySensitive);
        artCreateRequest.setTimeStamp(LocalDate.parse(timeStamp));
        artCreateRequest.setHistory(history);
        artCreateRequest.setTimeSpentInStorage(timeSpentInStorage);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/art")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(artCreateRequest)))
                // THEN
                .andExpect(jsonPath("artId")
                        .exists())
                .andExpect(jsonPath("name")
                        .value(is(name)))
                .andExpect(jsonPath("artistName")
                        .value(is(artistName)))
                .andExpect(jsonPath("locationId")
                        .value(is(locationId)))
                .andExpect(jsonPath("type")
                        .value(is(type)))
                .andExpect(jsonPath("humiditySensitive")
                        .value(is(humiditySensitive)))
                .andExpect(jsonPath("timeStamp")
                        .value(is(timeStamp)))
                .andExpect(jsonPath("history")
                        .value(is(history)))
                .andExpect(jsonPath("timeSpentInStorage")
                        .value(is(timeSpentInStorage)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateArt_PutSuccessful() throws Exception {
        // GIVEN
        String artId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String artistName = mockNeat.strings().valStr();
        String locationId = mockNeat.strings().valStr();
        ArtType type = getRandomMedium();
        boolean humiditySensitive = true;
        String timeStamp = LocalDate.now().toString();
        String history = mockNeat.strings().valStr();
        String timeSpentInStorage = mockNeat.strings().valStr();;

        Art art = new Art(artId, name, artistName, locationId, type, humiditySensitive, timeStamp);
        Art persistedArt = artService.addNewArt(art);

        String newName = mockNeat.strings().valStr();
        String newLocationId = mockNeat.strings().valStr();

        ArtUpdateRequest artUpdateRequest = new ArtUpdateRequest();
        artUpdateRequest.setArtId(artId);
        artUpdateRequest.setName(newName);
        artUpdateRequest.setArtistName(artistName);
        artUpdateRequest.setLocationId(newLocationId);
        artUpdateRequest.setType(type);
        artUpdateRequest.setHumiditySensitive(humiditySensitive);
        artUpdateRequest.setTimeStamp(LocalDate.parse(timeStamp));
        artUpdateRequest.setHistory(history);
        artUpdateRequest.setTimeSpentInStorage(timeSpentInStorage);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(put("/art")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(artUpdateRequest)))
                // THEN
                .andExpect(jsonPath("artId")
                        .exists())
                .andExpect(jsonPath("name")
                        .value(is(newName)))
                .andExpect(jsonPath("artistName")
                        .value(is(artistName)))
                .andExpect(jsonPath("locationId")
                        .value(is(newLocationId)))
                .andExpect(jsonPath("type")
                        .value(is(type)))
                .andExpect(jsonPath("humiditySensitive")
                        .value(is(humiditySensitive)))
                .andExpect(jsonPath("timeStamp")
                        .value(is(timeStamp)))
                .andExpect(jsonPath("history")
                        .value(is(history)))
                .andExpect(jsonPath("timeSpentInStorage")
                        .value(is(timeSpentInStorage)))
                .andExpect(status().isOk());
    }

    @Test
    public void removeArt_RemovalSuccessful() throws Exception {
        // GIVEN
        String artId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String artistName = mockNeat.strings().valStr();
        String locationId = mockNeat.strings().valStr();
        ArtType type = getRandomMedium();
        boolean humiditySensitive = true;
        String timeStamp = LocalDate.now().toString();
        String history = mockNeat.strings().valStr();
        String timeSpentInStorage = mockNeat.strings().valStr();;

        Art art = new Art(artId, name, artistName, locationId, type, humiditySensitive, timeStamp);
        Art persistedArt = artService.addNewArt(art);

        // WHEN
        mvc.perform(delete("/art/{artId}", persistedArt.getArtId())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNoContent());
        assertThat(artService.findArtById(artId)).isNull();
    }
}
