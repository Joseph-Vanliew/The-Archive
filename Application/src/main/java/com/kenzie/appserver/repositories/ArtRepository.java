package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ArtRecord;
import jdk.jfr.Enabled;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ArtRepository extends CrudRepository<ArtRecord, String> {
    List<ArtRecord> findByLocationId(String locationId);
}
