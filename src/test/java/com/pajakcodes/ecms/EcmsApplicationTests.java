package com.pajakcodes.ecms;

import com.pajakcodes.ecms.dto.AddCallRecordRequest;
import com.pajakcodes.ecms.dto.AuthResponse;
import com.pajakcodes.ecms.dto.SystemUser;
import org.flywaydb.core.Flyway;
import org.json.JSONException;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class EcmsApplicationTests {

    @Container
    protected static final GenericContainer<?> postgres = new GenericContainer<>(DockerImageName.parse("postgres:alpine3.19"))
            .withExposedPorts(5432)
            .withEnv(Map.of(
                    "POSTGRES_DB", "ecms_db",
                    "POSTGRES_USER", "app_user",
                    "POSTGRES_PASSWORD", "zxc")
            )
            .waitingFor(Wait.forListeningPort());

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> "jdbc:postgresql://" + postgres.getHost() + ":" + postgres.getMappedPort(5432) + "/ecms_db");
        registry.add("spring.datasource.username", () -> "app_user");
        registry.add("spring.datasource.password", () -> "zxc");
    }

    private String uri;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void beforeEach() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://" + postgres.getHost() + ":" + postgres.getMappedPort(5432) + "/ecms_db", "app_user", "zxc")
                .locations("flyway")
                .load();
        flyway.migrate();
        uri = "http://localhost:" + port + "/";
    }

    @Test
    void testLogin() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                uri + "auth/login",
                new SystemUser("john", "john123", new ArrayList<>()),
                String.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testListCallRecordByIncidentTypeIdSuccess() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(getAuthorizationHeaders());
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri + "callrecord/listCallRecords?page=0&size=2&incidentTypeId=1",
                HttpMethod.GET,
                entity,
                String.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        System.out.println("--------------responseEntity: " + responseEntity.getBody());
        String expectedJson = "{\n" +
                "  \"content\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"title\": \"Fire\",\n" +
                "      \"messageText\": \"Fire! Fire! Please assist.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"title\": \"Flooding\",\n" +
                "      \"messageText\": \"Flooding! Flooding! Please assist.\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"pageable\": {\n" +
                "    \"pageNumber\": 0,\n" +
                "    \"pageSize\": 2,\n" +
                "    \"sort\": {\n" +
                "      \"empty\": true,\n" +
                "      \"sorted\": false,\n" +
                "      \"unsorted\": true\n" +
                "    },\n" +
                "    \"offset\": 0,\n" +
                "    \"paged\": true,\n" +
                "    \"unpaged\": false\n" +
                "  },\n" +
                "  \"last\": false,\n" +
                "  \"totalPages\": 2,\n" +
                "  \"totalElements\": 3,\n" +
                "  \"first\": true,\n" +
                "  \"size\": 2,\n" +
                "  \"number\": 0,\n" +
                "  \"sort\": {\n" +
                "    \"empty\": true,\n" +
                "    \"sorted\": false,\n" +
                "    \"unsorted\": true\n" +
                "  },\n" +
                "  \"numberOfElements\": 2,\n" +
                "  \"empty\": false\n" +
                "}";

        JSONAssert.assertEquals(expectedJson, responseEntity.getBody(), false);
    }

    @Test
    void testListCallRecordByCallStatusTypeIdSuccess() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(getAuthorizationHeaders());
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri + "callrecord/listCallRecords?page=0&size=2&callStatusTypeId=2",
                HttpMethod.GET,
                entity,
                String.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.out.println("--------------responseEntity: " + responseEntity.getBody());

        String expectedJson = "{\n" +
                "  \"content\": [\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"title\": \"Flooding\",\n" +
                "      \"messageText\": \"Flooding! Flooding! Please assist.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"title\": \"Heart attack\",\n" +
                "      \"messageText\": \"Heart attack! Heart attack! Please assist.\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"pageable\": {\n" +
                "    \"pageNumber\": 0,\n" +
                "    \"pageSize\": 2,\n" +
                "    \"sort\": {\n" +
                "      \"empty\": true,\n" +
                "      \"sorted\": false,\n" +
                "      \"unsorted\": true\n" +
                "    },\n" +
                "    \"offset\": 0,\n" +
                "    \"paged\": true,\n" +
                "    \"unpaged\": false\n" +
                "  },\n" +
                "  \"last\": true,\n" +
                "  \"totalElements\": 2,\n" +
                "  \"totalPages\": 1,\n" +
                "  \"size\": 2,\n" +
                "  \"number\": 0,\n" +
                "  \"sort\": {\n" +
                "    \"empty\": true,\n" +
                "    \"sorted\": false,\n" +
                "    \"unsorted\": true\n" +
                "  },\n" +
                "  \"first\": true,\n" +
                "  \"numberOfElements\": 2,\n" +
                "  \"empty\": false\n" +
                "}";

        JSONAssert.assertEquals(expectedJson, responseEntity.getBody(), false);
    }

    @Test
    void testAddCallRecordSuccess() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        AddCallRecordRequest request = new AddCallRecordRequest("Illegal race", "Illegal race! Illegal race! Please assist.", 4L);
        HttpEntity<AddCallRecordRequest> entity = new HttpEntity<>(request, getAuthorizationHeaders());
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri + "callrecord/addCallRecrod",
                HttpMethod.POST,
                entity,
                String.class
        );

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

         entity = new HttpEntity<>(getAuthorizationHeaders());
        responseEntity = restTemplate.exchange(
                uri + "callrecord/listCallRecords?page=0&size=100&callStatusTypeId=1",
                HttpMethod.GET,
                entity,
                String.class
        );

        JSONAssert.assertEquals("{\"content\":[{\"title\":\"Illegal race\"}]}",
                        responseEntity.getBody(), JSONCompareMode.LENIENT);
    }

    @Test
    void testDeleteCallRecordSuccess() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        AddCallRecordRequest request = new AddCallRecordRequest("Illegal race", "Illegal race! Illegal race! Please assist.", 4L);
        HttpEntity<String> entity = new HttpEntity<>(getAuthorizationHeaders());
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri + "callrecord/deleteCallRecord?callRecordId=5",
                HttpMethod.DELETE,
                entity,
                String.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        responseEntity = restTemplate.exchange(
                uri + "callrecord/listCallRecords?page=0&size=100&callStatusTypeId=1",
                HttpMethod.GET,
                entity,
                String.class
        );

        JSONAssert.assertNotEquals("{\"content\":[{\"title\":\"Car Crash\"}]}",
                responseEntity.getBody(), JSONCompareMode.LENIENT);
    }

    private HttpHeaders getAuthorizationHeaders() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AuthResponse> responseEntity = restTemplate.postForEntity(
                uri + "auth/login",
                new SystemUser("john", "john123", new ArrayList<>()),
                AuthResponse.class
        );

        AuthResponse authResponse = responseEntity.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + authResponse.getToken());

        return headers;
    }

}
