package com.celada.integration;


import com.celada.domain.model.Person;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers(disabledWithoutDocker = true)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    void create() {
        String dni = "12345678";
        Person person = Person.builder()
                .name("Juan")
                .surname("Perez")
                .dni(dni)
                .build();
        String uri = String.format("http://localhost:%d/person", port);
        RequestEntity<Person> request = new RequestEntity<>(person, HttpMethod.POST, URI.create(uri));
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        assert response.getStatusCode().is2xxSuccessful();
        assert Objects.equals(response.getBody(), dni);
    }

    @Test
    @Order(2)
    void read() {
        String dni = "12345678";
        String uri = String.format("http://localhost:%d/person/%s", port, dni);
        RequestEntity<Void> request = new RequestEntity<>(HttpMethod.GET, URI.create(uri));
        ResponseEntity<Person> response = restTemplate.exchange(request, Person.class);

        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody() != null;
        assert Objects.equals(response.getBody().getDni(), dni);
    }

    @Test
    @Order(3)
    void createWife() {
        String dni = "98765432";
        Person person = Person.builder()
                .name("Maria")
                .surname("Perez")
                .dni(dni)
                .build();
        String uri = String.format("http://localhost:%d/person", port);
        RequestEntity<Person> request = new RequestEntity<>(person, HttpMethod.POST, URI.create(uri));
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        assert response.getStatusCode().is2xxSuccessful();
        assert Objects.equals(response.getBody(), dni);
    }

    @Test
    @Order(4)
    void update() {
        String dni = "12345678";
        String uri = String.format("http://localhost:%d/person/%s", port, dni);
        String wifeDni = "98765432";
        Person wife = Person.builder()
                .dni(wifeDni)
                .build();
        String firstChildDni = "11111111";
        Person firstChild = Person.builder()
                .name("Juan")
                .surname("Perez")
                .dni(firstChildDni)
                .build();
        String secondChildDni = "22222222";
        Person secondChild = Person.builder()
                .name("Pedro")
                .surname("Perez")
                .dni(secondChildDni)
                .build();
        Person update = Person.builder()
                .wife(wife)
                .children(List.of(firstChild, secondChild))
                .build();
        RequestEntity<Person> request = new RequestEntity<>(update, HttpMethod.PUT, URI.create(uri));
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        assert response.getStatusCode().is2xxSuccessful();
    }

    @Test
    @Order(5)
    void readUpdate() {
        String dni = "12345678";
        String uri = String.format("http://localhost:%d/person/%s", port, dni);
        RequestEntity<Void> request = new RequestEntity<>(HttpMethod.GET, URI.create(uri));
        ResponseEntity<Person> response = restTemplate.exchange(request, Person.class);

        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody() != null;
        assert Objects.equals(response.getBody().getDni(), dni);
        assert Objects.equals(response.getBody().getWife().getDni(), "98765432");
        assert Objects.equals(response.getBody().getChildren().get(0).getDni(), "11111111");
        assert Objects.equals(response.getBody().getChildren().get(1).getDni(), "22222222");
    }
}
