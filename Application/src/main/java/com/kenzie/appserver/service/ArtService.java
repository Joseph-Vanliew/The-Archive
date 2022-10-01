package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ArtRepository;
import com.kenzie.appserver.repositories.model.ArtRecord;
import com.kenzie.appserver.service.model.Art;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtService {
    private ArtRepository artRepository;

    @Autowired
    public ArtService(ArtRepository artRepository) {
        this.artRepository = artRepository;
    }

    public Art findArtById(String id) {
        Art artFromBackend = artRepository
                .findById(id)
                .map(art -> new Art(art.getArtId(),
                        art.getName(),
                        art.getArtistName(),
                        art.getLocationId(),
                        art.getType(),
                        art.isHumiditySensitive(),
                        art.getTimeStamp()
                ))
                .orElse(null);
        return artFromBackend;
    }


    public List<Art> findAllArt() {
        List<Art> allArt = new ArrayList<>();

        Iterable<ArtRecord> artIterator = artRepository.findAll();
        for (ArtRecord record: artIterator) {
            allArt.add(new Art(record.getArtId(),
                    record.getName(),
                    record.getArtistName(),
                    record.getLocationId(),
                    record.getType(),
                    record.isHumiditySensitive(),
                    record.getTimeStamp()
            ));
        }

        return allArt;
    }

    public List<Art> findByLocationId(String id) {
        List<ArtRecord> artRecords = artRepository.findByLocationId(id);
        List<Art> art = new ArrayList<>();
        for (ArtRecord record: artRecords) {
            art.add(new Art(record.getArtId(),
                    record.getName(),
                    record.getArtistName(),
                    record.getLocationId(),
                    record.getType(),
                    record.isHumiditySensitive(),
                    record.getTimeStamp()
            ));
        }
        return art;
    }

    public Art addNewArt(Art art) {
        ArtRecord record = artToRecordConverter(art);
        artRepository.save(record);
        return art;
    }

    public void updateArt(Art art) {
        if (artRepository.existsById(art.getArtId())) {
            ArtRecord record = artToRecordConverter(art);
            artRepository.save(record);
        }
    }

    public void removeArt(String artId) {
        artRepository.deleteById(artId);
    }

    private static ArtRecord artToRecordConverter (Art art) {
        ArtRecord record = new ArtRecord();
        record.setArtId(art.getArtId());
        record.setName(art.getName());
        record.setArtistName(art.getArtistName());
        record.setLocationId(art.getLocationId());
        record.setType(art.getType());
        record.setHumiditySensitive(art.isHumiditySensitive());
        record.setTimeStamp(art.getTimeStamp());

        return record;
    }
}
