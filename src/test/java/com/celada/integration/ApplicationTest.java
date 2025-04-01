package com.celada.integration;


import com.celada.domain.model.Person;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers(disabledWithoutDocker = true)
public class ApplicationTest {

    @LocalServerPort
    int port;

    RestTemplate restTemplate = new RestTemplate();

    static final Neo4jContainer<?> neo4j = new Neo4jContainer<>("neo4j:4.4")
            .withReuse(true);

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {

        neo4j.start();

        registry.add("spring.neo4j.uri", neo4j::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", neo4j::getAdminPassword);
    }

    @Test
    void createAndRead() {
        String dni = "12345678";
        Person person = Person.builder()
                .name("Juan")
                .surname("Perez")
                .dni(dni)
                .build();
        String createUri = String.format("http://localhost:%d/person", port);
        RequestEntity<Person> createRequest = new RequestEntity<>(person, HttpMethod.POST, URI.create(createUri));
        ResponseEntity<String> createResponse = restTemplate.exchange(createRequest, String.class);

        assert createResponse.getStatusCode().is2xxSuccessful();
        assert Objects.equals(createResponse.getBody(), dni);

        String readUri = String.format("http://localhost:%d/person/%s", port, dni);
        RequestEntity<Void> readRequest = new RequestEntity<>(HttpMethod.GET, URI.create(readUri));
        ResponseEntity<Person> readResponse = restTemplate.exchange(readRequest, Person.class);

        assert readResponse.getStatusCode().is2xxSuccessful();
        assert readResponse.getBody() != null;
        assert Objects.equals(readResponse.getBody().getDni(), person.getDni());
    }
}
