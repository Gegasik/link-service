package com.service.linkstorageservice;

import com.google.common.collect.Collections2;
import com.service.linkstorageservice.model.Link;
//import com.service.linkstorageservice.repository.LinkRepository;
import com.service.linkstorageservice.repository.LinkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@EnableEurekaClient
@SpringBootApplication
public class LinkstorageserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkstorageserviceApplication.class, args);
	}
	@Bean
	CommandLineRunner run(LinkRepository linkRepository) {
		return args -> {
			linkRepository.deleteAll()
					.thenMany(Flux.just(
							new Link("Java", "OOP", List.of("qwe","wqew") ),
							new Link("wq", "OOP", List.of("qwe","wqew") ),
							new Link("Javdadsa", "OOzxccP", List.of("qwe","wqew") ),
							new Link("Javdad321sa", "OOzxeqwccP", List.of("qwe","wqew") )
					)
							.flatMap(linkRepository::save))
					.thenMany(linkRepository.findAll())
					.subscribe(System.out::println);

		};
	}
}
