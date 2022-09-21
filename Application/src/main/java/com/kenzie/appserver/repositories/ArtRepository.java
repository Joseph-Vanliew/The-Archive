package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ArtRecord;
import org.springframework.data.repository.CrudRepository;

public interface ArtRepository extends CrudRepository<ArtRecord, String> {
}
