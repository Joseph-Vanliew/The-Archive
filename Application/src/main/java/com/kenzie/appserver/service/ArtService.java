package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ArtRepository;
import org.springframework.stereotype.Service;

@Service
public class ArtService {
    private ArtRepository artRepository;
    // we need a StorageUnitService

    public ArtService(ArtRepository artRepository) {
        this.artRepository = artRepository;
    }

    //methods
    //findArtById


    //addNewArt

    //updateArt

    //removeArt

    //findAllArt
        //Look at concert project

    //findArtByStorageUnit

}
