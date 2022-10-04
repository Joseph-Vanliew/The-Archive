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

    @BeforeEach
    void setup() {
        artRepository = mock(ArtRepository.class);
        artService = new ArtService(artRepository);
    }

    @Test
    void findByArtId() {
        // GIVEN
        String artId = randomUUID().toString();

        ArtRecord artRecord = new ArtRecord();
        artRecord.setArtId(artId);
        artRecord.setName("artName");
        artRecord.setArtistName("artistName");
        artRecord.setLocationId(randomUUID().toString());
        artRecord.setType(ArtType.OIL);
        artRecord.setHumiditySensitive(true);
        artRecord.setTimeStamp("2022-03-10");
        artRecord.setPrice(10.00);

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
        artRecord.setHumiditySensitive(true);
        artRecord.setName("VanLouis");
        artRecord.setLocationId("Bahamas, BAHA");
        artRecord.setType(ArtType.OIL);
        artRecord.setTimeStamp("09/08/1994");
        artRecord.setPrice(1000.00);

        ArtRecord artRecord1 = new ArtRecord();
        artRecord1.setArtId("randomArt1");
        artRecord1.setArtistName("Jimmy");
        artRecord1.setHumiditySensitive(false);
        artRecord1.setName("Chooch Da Arteest");
        artRecord1.setLocationId("Shreveport, LA");
        artRecord1.setType(ArtType.ACRYLIC);
        artRecord1.setTimeStamp("09/21/2022");
        artRecord1.setPrice(1000.00);

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
                Assertions.assertEquals(artRecord.getPrice(), art.getPrice(), "The art prices match");
            } else if (art.getArtId() == artRecord1.getArtId()) {
                Assertions.assertEquals(artRecord1.getArtId(), art.getArtId(), "The art id matches");
                Assertions.assertEquals(artRecord1.getArtistName(), art.getArtistName(), "The artist name matches");
                Assertions.assertEquals(artRecord1.getName(), art.getName(), "The art name matches");
                Assertions.assertEquals(artRecord1.getLocationId(), art.getLocationId(), "The art location matches");
                Assertions.assertEquals(artRecord1.getType(), art.getType(), "The art type matches");
                Assertions.assertEquals(artRecord1.getTimeStamp(), art.getTimeStamp(), "The art timestamp matches");
                Assertions.assertEquals(artRecord1.getPrice(), art.getPrice(), "The art prices match");
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
        Double price = 0.0;

        Art art = new Art(artId, name, artistName, locationId, type, humiditySensitive, timeStamp, price);

        ArtRecord artRecord = new ArtRecord();
        artRecord.setArtId(art.getArtId());
        artRecord.setName(art.getName());
        artRecord.setArtistName(art.getArtistName());
        artRecord.setLocationId(art.getLocationId());
        artRecord.setType(art.getType());
        artRecord.setTimeStamp(art.getTimeStamp());
        artRecord.setPrice(art.getPrice());


        when(artRepository.existsById(art.getArtId())).thenReturn(true);

        //When
        artService.updateArt(art);

        //Then
        verify(artRepository, times(1)).existsById(artRecord.getArtId());
    }
}

