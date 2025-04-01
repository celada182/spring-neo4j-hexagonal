package com.celada.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Testcontainers(disabledWithoutDocker = true)
public class ApplicationTest {

    static final Neo4jContainer<?> neo4j = new Neo4jContainer<>("neo4j:4.4")
            .withPlugins("apoc")
            .withReuse(true);

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {

        neo4j.start();

        registry.add("spring.neo4j.uri", neo4j::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", neo4j::getAdminPassword);
    }

    @Test
    void whatever(@Autowired Neo4jTemplate template, @Autowired Neo4jClient client) {
        assertThat(template).isNotNull();
        assertThat(client).isNotNull();

        String apoc = client
                .query("RETURN apoc.version() AS output")
                .fetchAs(String.class)
                .first()
                .orElseThrow();
        assertThat(apoc).startsWith("4.4");
    }
}
