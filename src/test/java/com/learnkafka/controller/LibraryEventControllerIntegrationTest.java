package com.learnkafka.controller;


import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EmbeddedKafka(topics = {"library-events"}, partitions = 3)
//@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
//        "spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}"})
public class LibraryEventControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void postLibraryEvent() {
        //given
        Book book = Book.builder()
                .bookId(123)
                .bookAuthor("Dilip"+ new Random().nextInt(1000))
                .bookName("Kafka In Action")
                .build();

        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .book(book)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<LibraryEvent> request = new HttpEntity<>(libraryEvent, headers);

        //when
        ResponseEntity<LibraryEvent> responseEntity = restTemplate.exchange("/v1/libraryevent", HttpMethod.POST, request, LibraryEvent.class);

        //then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

   }
}