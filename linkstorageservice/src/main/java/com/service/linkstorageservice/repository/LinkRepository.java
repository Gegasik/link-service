package com.service.linkstorageservice.repository;

import com.service.linkstorageservice.model.Link;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends ReactiveMongoRepository<Link, String> {
}
