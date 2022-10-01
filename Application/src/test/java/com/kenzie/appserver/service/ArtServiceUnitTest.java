package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ArtRepository;
import com.kenzie.appserver.repositories.model.ArtRecord;
import com.kenzie.appserver.service.model.Art;
import com.kenzie.appserver.service.model.ArtType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class ArtServiceUnitTest {
    private ArtRepository artRepository;
    private ArtService artService;
    private Art art;

    @BeforeEach
    void setup() {
        artRepository = mock(ArtRepository.class);
        artService = new ArtService(artRepository);
        art = new Art();
    }

    @Test
    void findByArtId() {
        // GIVEN
        String artId = randomUUID().toString();

        ArtRecord artRecord = new ArtRecord();
        artRecord.setArtId(artId);
        artRecord.setName("artName");
        artRecord.setType(ArtType.OIL);
        artRecord.setHistory("artHistory");
        artRecord.setHumiditySensitive(true);
        when(artRepository.findById(artRecord.getArtId())).thenReturn(Optional.of(artRecord));
        // WHEN
        Art art = artService.findArtById(artRecord.getArtId());

        // THEN
        Assertions.assertNotNull(art, "The concert is returned");
        Assertions.assertEquals(artRecord.getArtId(), art.getArtId(), "The art id matches");
        Assertions.assertEquals(artRecord.getName(), art.getName(), "The art name matches");
        Assertions.assertEquals(artRecord.getType(), art.getType(), "The art type matches");
        Assertions.assertEquals(artRecord.isHumiditySensitive(), art.isHumiditySensitive(), "The art humidity matches");
    }

    @Test
    void findAllArt_withTwoIds() {
        //Given
        ArtRecord artRecord = new ArtRecord();
        artRecord.setArtId("randomArt");
        artRecord.setArtistName("Joe");
        artRecord.setHistory("Made in 1942");
        artRecord.setHumiditySensitive(true);
        artRecord.setName("VanLouis");
        artRecord.setLocationId("Bahamas, BAHA");
        artRecord.setType(ArtType.OIL);
        artRecord.setTimeStamp("09/08/1994");
        artRecord.setTimeSpentInStorage("2100 days");

        ArtRecord artRecord1 = new ArtRecord();
        artRecord.setArtId("randomArt1");
        artRecord.setArtistName("Jimmy");
        artRecord.setHistory("Made in 2022");
        artRecord.setHumiditySensitive(false);
        artRecord.setName("Chooch Da Arteest");
        artRecord.setLocationId("Shreveport, LA");
        artRecord.setType(ArtType.ACRYLIC);
        artRecord.setTimeStamp("09/21/2022");
        artRecord.setTimeSpentInStorage("1 day");

        List<ArtRecord> artRecordList = new ArrayList<>();
        artRecordList.add(artRecord);
        artRecordList.add(artRecord1);

        when(artRepository.findAll()).thenReturn(artRecordList);

        //When
        List<Art> artList = artService.findAllArt();

        //Then
        Assertions.assertNotNull(artList, "The list is not empty");
        Assertions.assertEquals(2, artList.size(), "the art list size is 2");

        for (Art art : artList) {
            if (art.getArtId() == artRecord.getArtId()) {
                Assertions.assertEquals(artRecord.getArtId(), art.getArtId(), "The art id matches");
                Assertions.assertEquals(artRecord.getArtistName(), art.getArtistName(), "The artist name matches");
                Assertions.assertEquals(artRecord.getName(), art.getName(), "The art name matches");
                Assertions.assertEquals(artRecord.getLocationId(), art.getLocationId(), "The art location matches");
                Assertions.assertEquals(artRecord.getType(), art.getType(), "The art type matches");
                Assertions.assertEquals(artRecord.getTimeStamp(), art.getTimeStamp(), "The art timestamp matches");
            } else if (art.getArtId() == artRecord1.getArtId()) {
                Assertions.assertEquals(artRecord1.getArtId(), art.getArtId(), "The art id matches");
                Assertions.assertEquals(artRecord1.getArtistName(), art.getArtistName(), "The artist name matches");
                Assertions.assertEquals(artRecord1.getName(), art.getName(), "The art name matches");
                Assertions.assertEquals(artRecord1.getLocationId(), art.getLocationId(), "The art location matches");
                Assertions.assertEquals(artRecord1.getType(), art.getType(), "The art type matches");
                Assertions.assertEquals(artRecord1.getTimeStamp(), art.getTimeStamp(), "The art timestamp matches");
            } else {
                Assertions.assertTrue(false, "Concert returned that was not in the records!");
            }
        }
    }

    @Test
    void addNewArt() {
        // GIVEN
        String artId = randomUUID().toString();

        Art art = new Art(artId);

        ArgumentCaptor<ArtRecord> artRecordCaptor = ArgumentCaptor.forClass(ArtRecord.class);

        // WHEN
        Art returnedArt = artService.addNewArt(art);

        // THEN
        Assertions.assertNotNull(returnedArt);

        verify(artRepository).save(artRecordCaptor.capture());

        ArtRecord record = artRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The art record is returned");
        Assertions.assertEquals(record.getArtId(), art.getArtId(), "The art id matches");
        Assertions.assertEquals(record.getName(), art.getName(), "The art name matches");
        Assertions.assertEquals(record.getArtistName(), art.getArtistName(), "The artist name matches");
        Assertions.assertEquals(record.getLocationId(), art.getLocationId(), "The art location matches");

    }

    @Test
    void removeArt() {
        //Given
        artService.removeArt("artToRemove");

        verify(artRepository, times(1)).deleteById("artToRemove");
    }

    @Test
    void updateArt() {
        //Given
        String artId = "art";
        String name = "artName";
        String artistName = "vanRandall";
        String locationId = "Russia";
        ArtType type = ArtType.OIL;
        boolean humiditySensitive = true;
        String timeStamp = "now";
        String history = "history";
        String timeSpentInStorage = "1000 days";

        Art art = new Art(artId, name, artistName, locationId, type, humiditySensitive, timeStamp);

        ArtRecord artRecord = new ArtRecord();
        artRecord.setArtId(art.getArtId());
        artRecord.setName(art.getName());
        artRecord.setArtistName(art.getArtistName());
        artRecord.setLocationId(art.getLocationId());
        artRecord.setType(art.getType());
        artRecord.setTimeStamp(art.getTimeStamp());


        when(artRepository.existsById(art.getArtId())).thenReturn(true);

        //When
        artService.updateArt(art);

        //Then
        verify(artRepository, times(1)).existsById(artRecord.getArtId());
    }

    @Test
    void findByLocationId() {



    }

}

