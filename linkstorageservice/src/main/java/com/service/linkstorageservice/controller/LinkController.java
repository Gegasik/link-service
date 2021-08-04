package com.service.linkstorageservice.controller;

import com.service.linkstorageservice.model.Link;
import com.service.linkstorageservice.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class LinkController {
    @Autowired
    private LinkRepository linkRepository;
    /*@Autowired
    private LinkRepositoryWithoutReactive linkRepositoryWithoutReactive;*/


    @GetMapping("/getAll")
    public Flux<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<Link>> getLinkById(@PathVariable(value = "id") String linkId) {
        return linkRepository.findById(linkId)
                .map(ResponseEntity::ok)  // then the map operator is called on this Bucket to wrap it in a ResponseEntity object with status code 200 OK
                .defaultIfEmpty(ResponseEntity.notFound().build());   // finally there is a call to defaultIfEmpty to build an empty ResponseEntity with status 404 NOT FOUND if the Bucket was not found.
    }

    @PostMapping("/create")
    public Mono<Link> createLink( @RequestBody Link link) {
        return linkRepository.save(link);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Link>> updateBucket(@PathVariable(value = "id") String linkId,
                                                    @RequestBody Link link) {
        return linkRepository.findById(linkId)
                .flatMap(existingLink -> {
                    existingLink.setDescription(link.getDescription());
                    // then calls flatMap with this movie to update its entries using its setters and the values from the Bucket passed as argument.
                    return linkRepository.save(existingLink);
                })
                .map(updateBucket -> new ResponseEntity<>(updateBucket, HttpStatus.OK))  // Then it saves them to the database and wraps this updated Bucket in a ResponseEntity with status code 200 OK in case of success or 404 NOT FOUND in case of failure.
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteBucket(@PathVariable(value = "id") String linkId) {

        return linkRepository.findById(linkId)  // First, you search the Bucket you want to delete.
                .flatMap(existingLink ->
                        linkRepository.delete(existingLink)  // Next, you delete and return 200 OK to show your delete was successful
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));  // or you return 404 NOT FOUND to say the Bucket was not found
    }

    @DeleteMapping("/deleteAllBuckets")
    public Mono<Void> deleteAllBuckets() {
        return linkRepository.deleteAll();
    }
}
