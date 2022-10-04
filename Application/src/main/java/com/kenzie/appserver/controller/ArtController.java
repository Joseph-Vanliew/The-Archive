package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.service.ArtService;
import com.kenzie.appserver.service.model.Art;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/art")
public class ArtController {

    private ArtService artService;

    ArtController(ArtService artService) {
        this.artService = artService;
    }

    @GetMapping("/{artId}")
    public ResponseEntity<ArtResponse> get(@PathVariable("artId") String artId) {

        Art art = artService.findArtById(artId);
        if (art == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(artResponse(art));
    }

    @PostMapping
    public ResponseEntity<ArtResponse> addNewArt(@RequestBody ArtCreateRequest artCreateRequest) {
        Art art = new Art(randomUUID().toString(),
                artCreateRequest.getName(),
                artCreateRequest.getArtistName(),
                artCreateRequest.getLocationId(),
                artCreateRequest.getType(),
                artCreateRequest.isHumiditySensitive(),
                artCreateRequest.getTimeStamp().toString(),
                artCreateRequest.getPrice()
        );

        artService.addNewArt(art);

        ArtResponse response = artResponse(art);

        return ResponseEntity.created(URI.create("/art/" + response.getArtId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ArtResponse>> getAllArt() {
        List<Art> artWork = artService.findAllArt();
        if (artWork == null || artWork.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ArtResponse> artPieces = new ArrayList<>();

        for (Art art : artWork) {
            artPieces.add(artResponse(art));
        }

        return ResponseEntity.ok(artPieces);
    }

    @PutMapping
    public ResponseEntity<ArtResponse> updateArt(@RequestBody ArtUpdateRequest artUpdateRequest) {
        Art art = new Art(artUpdateRequest.getArtId(),
                artUpdateRequest.getName(),
                artUpdateRequest.getArtistName(),
                artUpdateRequest.getLocationId(),
                artUpdateRequest.getType(),
                artUpdateRequest.isHumiditySensitive(),
                artUpdateRequest.getTimeStamp().toString(),
                artUpdateRequest.getPrice()
        );
        artService.updateArt(art);

        ArtResponse artResponse = artResponse(art);

        return ResponseEntity.ok(artResponse);
    }

    @DeleteMapping("/{artId}")
    public ResponseEntity removeArt(@PathVariable("artId") String artId) {
        artService.removeArt(artId);
        return ResponseEntity.noContent().build();
    }
    private ArtResponse artResponse(Art art) {
        ArtResponse response = new ArtResponse();
        response.setArtId(art.getArtId());
        response.setName(art.getName());
        response.setArtistName(art.getArtistName());
        response.setLocationId(art.getLocationId());
        response.setType(art.getType());
        response.setHumiditySensitive(art.isHumiditySensitive());
        response.setTimeStamp(art.getTimeStamp());

        return response;
    }
}
