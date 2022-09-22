package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ArtRecord;
import jdk.jfr.Enabled;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ArtRepository extends CrudRepository<ArtRecord, String> {
}
