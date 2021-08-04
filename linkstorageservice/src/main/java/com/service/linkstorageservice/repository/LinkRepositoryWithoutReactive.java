package com.service.linkstorageservice.repository;

import com.service.linkstorageservice.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepositoryWithoutReactive extends MongoRepository<Link, String> {
}
