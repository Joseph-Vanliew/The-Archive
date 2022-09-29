package com.kenzie.appserver.dao;

//import com.kenzie.appserver.objects.ArtPieces;


public class ArtDao {
//    private final DynamoDBMapper mapper;
//
//    public ArtPiecesDAO(DynamoDBMapper mapper) {
//        this.mapper = mapper;
//    }
//
//    public List<ArtPieces> getAllArtPiecesByArtist(String artistName) throws IOException {
//        Map<String, AttributeValue> valueMap = new HashMap<>();
//        valueMap.put(":ArtistToFind", new AttributeValue().withS(artistName));
//        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
//                .withFilterExpression("artist = :artistName")
//                .withExpressionAttributeValues(valueMap);
//        PaginatedScanList<ArtPieces> artList = mapper.scan(ArtPieces.class, scanExpression);
//        return artList;
//    }
//    public List<ArtPieces> getAllArtPiecesByLocation(String location){
//        Map<String, AttributeValue> valueMap = new HashMap<>();
//        valueMap.put(":ArtistToFind", new AttributeValue().withS(location));
//        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
//                .withFilterExpression("location = :Location")
//                .withExpressionAttributeValues(valueMap);
//        PaginatedScanList<ArtPieces> artList = mapper.scan(ArtPieces.class, scanExpression);
//        return artList;
//    }
//    public List<ArtPieces> getAllArtPiecesByType(String type){
//        return new ArrayList<>();
//    }
//    public List<ArtPieces> getAllArtPiecesByArtId(String artId){
//        return new ArrayList<>();
//    }
//
//    public ArtPieces getArtPiece(String artistName, String artId) {
//        ArtPieces artPiece = new ArtPieces();
//
//        if (artPiece != null) {
//            artPiece.setArtistName(artistName);
//            artPiece.setArtId(artId);
//        } else {
//            throw new RuntimeException("Invalid userId entered, please check spelling and try again");
//        }
//        mapper.load(artPiece);
//        return artPiece;
//    }

}
