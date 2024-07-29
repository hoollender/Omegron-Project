package com.omegron.service.impl;

import com.omegron.model.dto.AddLandLordDTO;
import com.omegron.model.dto.LandLordDTO;
import com.omegron.service.LandLordService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@ActiveProfiles("test")
@EnableWireMock(
        @ConfigureWireMock(name = "landlord-service")
)
public class LandLordServiceImplIT {

    @InjectWireMock("landlord-service")
    private WireMockServer wireMockServer;

    @Autowired
    private LandLordService landLordService;

    @BeforeEach
    void setUp() throws Exception {
        String wireMockBaseUrl = wireMockServer.baseUrl();
        System.out.println("WireMock Base URL: " + wireMockBaseUrl);

        // Create a new RestClient with the WireMock base URL
        RestClient dynamicRestClient = RestClient
                .builder()
                .baseUrl(wireMockBaseUrl)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();

        // Use reflection to update the RestClient in the service
        Field restClientField = LandLordServiceImpl.class.getDeclaredField("inventoryRestClient");
        restClientField.setAccessible(true);
        restClientField.set(landLordService, dynamicRestClient);
    }

    @Test
    void testGetAllLandLordsSummary() {
        wireMockServer.stubFor(get("/landlords/all").willReturn(
                aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                """
                                                [
                                                    {
                                                        "id": 1,
                                                        "firstName": "Kostadin",
                                                        "middleName": "Tsvetelinov",
                                                        "lastName": "Ivanov",
                                                        "dateOfBirth": "1999-03-20",
                                                        "address": "123 Street",
                                                        "personalNumber": "123456789",
                                                        "personalID": "ID123456",
                                                        "validityID": "2030-01-01",
                                                        "dateOfIssue": "2020-01-01"
                                                    },
                                                    {
                                                        "id": 2,
                                                        "firstName": "Tsvetina",
                                                        "middleName": "Rumenova",
                                                        "lastName": "Natseva",
                                                        "dateOfBirth": "2003-04-28",
                                                        "address": "456 Street",
                                                        "personalNumber": "987654321",
                                                        "personalID": "ID654321",
                                                        "validityID": "2035-05-05",
                                                        "dateOfIssue": "2025-05-05"
                                                    }
                                                ]
                                        """
                        )
        ));

        // Call the service method to get all landlords
        List<LandLordDTO> landLords = landLordService.getAllLandLordsSummary();

        // Verify the response is not null and has the expected number of landlords
        Assertions.assertNotNull(landLords);
        Assertions.assertEquals(2, landLords.size());

        // Verify the details of the first landlord
        LandLordDTO firstLandLord = landLords.get(0);
        Assertions.assertEquals(1L, firstLandLord.id());
        Assertions.assertEquals("Kostadin", firstLandLord.firstName());
        Assertions.assertEquals("Tsvetelinov", firstLandLord.middleName());
        Assertions.assertEquals("Ivanov", firstLandLord.lastName());
        Assertions.assertEquals(LocalDate.of(1999, 3, 20), firstLandLord.dateOfBirth());
        Assertions.assertEquals("123 Street", firstLandLord.address());
        Assertions.assertEquals("123456789", firstLandLord.personalNumber());
        Assertions.assertEquals("ID123456", firstLandLord.personalID());
        Assertions.assertEquals(LocalDate.of(2030, 1, 1), firstLandLord.validityID());
        Assertions.assertEquals(LocalDate.of(2020, 1, 1), firstLandLord.dateOfIssue());

        // Verify the details of the second landlord
        LandLordDTO secondLandLord = landLords.get(1);
        Assertions.assertEquals(2L, secondLandLord.id());
        Assertions.assertEquals("Tsvetina", secondLandLord.firstName());
        Assertions.assertEquals("Rumenova", secondLandLord.middleName());
        Assertions.assertEquals("Natseva", secondLandLord.lastName());
        Assertions.assertEquals(LocalDate.of(2003, 4, 28), secondLandLord.dateOfBirth());
        Assertions.assertEquals("456 Street", secondLandLord.address());
        Assertions.assertEquals("987654321", secondLandLord.personalNumber());
        Assertions.assertEquals("ID654321", secondLandLord.personalID());
        Assertions.assertEquals(LocalDate.of(2035, 5, 5), secondLandLord.validityID());
        Assertions.assertEquals(LocalDate.of(2025, 5, 5), secondLandLord.dateOfIssue());
    }

    @Test
    void testAddLandLord() {
        AddLandLordDTO newLandLord = new AddLandLordDTO(
                "Kostadin",
                "Tsvetelinov",
                "Ivanov",
                LocalDate.of(1990, 1, 1),
                "789 Street",
                "1122334455",
                "ID112233",
                LocalDate.of(2040, 12, 31),
                LocalDate.of(2020, 1, 1));

        wireMockServer.stubFor(post("/landlords/add").willReturn(
                aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
        ));

        wireMockServer.stubFor(get("/landlords/details/1").willReturn(
                aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                """
                                        {
                                            "id": 1,
                                            "firstName": "Kostadin",
                                            "middleName": "Tsvetelinov",
                                            "lastName": "Ivanov",
                                            "dateOfBirth": "1990-01-01",
                                            "address": "789 Street",
                                            "personalNumber": "1122334455",
                                            "personalID": "ID112233",
                                            "validityID": "2040-12-31",
                                            "dateOfIssue": "2020-01-01"
                                        }
                                        """
                        )
        ));

        landLordService.addLandLord(newLandLord);

        LandLordDTO landLordDetails = landLordService.getLandLordDetails(1L);
        Assertions.assertEquals("Kostadin", landLordDetails.firstName());
        Assertions.assertEquals("Tsvetelinov", landLordDetails.middleName());
        Assertions.assertEquals("Ivanov", landLordDetails.lastName());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), landLordDetails.dateOfBirth());
        Assertions.assertEquals("789 Street", landLordDetails.address());
        Assertions.assertEquals("1122334455", landLordDetails.personalNumber());
        Assertions.assertEquals("ID112233", landLordDetails.personalID());
        Assertions.assertEquals(LocalDate.of(2040, 12, 31), landLordDetails.validityID());
        Assertions.assertEquals(LocalDate.of(2020, 1, 1), landLordDetails.dateOfIssue());
    }

    @Test
    void testDeleteLandLord() {
        Long landLordId = 1L;

        wireMockServer.stubFor(delete("/landlords/" + landLordId).willReturn(
                aResponse()
                        .withStatus(204)
        ));

        landLordService.deleteLandLord(landLordId);

        wireMockServer.verify(deleteRequestedFor(urlEqualTo("/landlords/" + landLordId)));
    }

    @Test
    void testGetLandLordDetails() {
        Long landLordId = 1L;

        wireMockServer.stubFor(get("/landlords/details/" + landLordId).willReturn(
                aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                """
                                                {
                                                    "id": 1,
                                                    "firstName": "Kostadin",
                                                    "middleName": "Tsvetelinov",
                                                    "lastName": "Ivanov",
                                                    "dateOfBirth": "1990-01-01",
                                                    "address": "789 Street",
                                                    "personalNumber": "1122334455",
                                                    "personalID": "ID112233",
                                                    "validityID": "2040-12-31",
                                                    "dateOfIssue": "2020-01-01"
                                                }
                                        """
                        )
        ));

        LandLordDTO landLord = landLordService.getLandLordDetails(landLordId);

        Assertions.assertNotNull(landLord);
        Assertions.assertEquals(landLordId, landLord.id());
        Assertions.assertEquals("Kostadin", landLord.firstName());
        Assertions.assertEquals("Tsvetelinov", landLord.middleName());
        Assertions.assertEquals("Ivanov", landLord.lastName());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), landLord.dateOfBirth());
        Assertions.assertEquals("789 Street", landLord.address());
        Assertions.assertEquals("1122334455", landLord.personalNumber());
        Assertions.assertEquals("ID112233", landLord.personalID());
        Assertions.assertEquals(LocalDate.of(2040, 12, 31), landLord.validityID());
        Assertions.assertEquals(LocalDate.of(2020, 1, 1), landLord.dateOfIssue());
    }

    @Test
    void testFindById() {
        Long landLordId = 1L;

        wireMockServer.stubFor(get("/landlords/details/" + landLordId).willReturn(
                aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                """
                                                {
                                                    "id": 1,
                                                    "firstName": "Kostadin",
                                                    "middleName": "Tsvetelinov",
                                                    "lastName": "Ivanov",
                                                    "dateOfBirth": "1990-01-01",
                                                    "address": "789 Street",
                                                    "personalNumber": "1122334455",
                                                    "personalID": "ID112233",
                                                    "validityID": "2040-12-31",
                                                    "dateOfIssue": "2020-01-01"
                                                }
                                        """
                        )
        ));

        LandLordDTO landLord = landLordService.findById(landLordId);

        Assertions.assertNotNull(landLord);
        Assertions.assertEquals(landLordId, landLord.id());
        Assertions.assertEquals("Kostadin", landLord.firstName());
        Assertions.assertEquals("Tsvetelinov", landLord.middleName());
        Assertions.assertEquals("Ivanov", landLord.lastName());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), landLord.dateOfBirth());
        Assertions.assertEquals("789 Street", landLord.address());
        Assertions.assertEquals("1122334455", landLord.personalNumber());
        Assertions.assertEquals("ID112233", landLord.personalID());
        Assertions.assertEquals(LocalDate.of(2040, 12, 31), landLord.validityID());
        Assertions.assertEquals(LocalDate.of(2020, 1, 1), landLord.dateOfIssue());
    }
}

