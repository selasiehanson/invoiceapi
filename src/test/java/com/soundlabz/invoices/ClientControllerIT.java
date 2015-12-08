package com.soundlabz.invoices;

import builders.ClientBuilder;
import builders.UserBuilder;
import com.jayway.restassured.RestAssured;
import com.soundlabz.invoices.domain.Client;
import com.soundlabz.invoices.domain.User;
import com.soundlabz.invoices.domain.repositories.ClientRepository;
import com.soundlabz.invoices.domain.repositories.UserRepository;
import com.soundlabz.invoices.services.ClientService;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InvoiceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@ActiveProfiles("test")
public class ClientControllerIT {

    private static final String CLIENTS_ENDPOINT = "/api/clients";
    private static final String TOKEN_KEY = "x-auth-token";

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Value("${local.server.port}")
    private int serverPort;

    private static final String username = "Sammy Dee";
    private static final String password = "12345567890";

    @Before
    public void setup() {
        clientRepository.deleteAll();
        userRepository.deleteAll();


        User u = new UserBuilder()
                .username(username)
                .password(password)
                .build();

        userRepository.save(u);

        Client c1 = new ClientBuilder()
                .name("Kofi Koduah")
                .address("someaddress")
                .email("kd@infonet.com")
                .phoneNumber("232123")
                .user(u)
                .build();

        Client c2 = new ClientBuilder()
                .name("Manu Asamoah")
                .address("someaddress")
                .email("kd@infonet.com")
                .phoneNumber("232123")
                .user(u)
                .build();

        clientRepository.save(c1);
        clientRepository.save(c2);
        RestAssured.port = serverPort;
//        clientService.createClient(c1);
//        clientService.createClient(c1);
    }

    @Test
    public void getToken() {
        Map<String, String> loginCredentials = new HashMap<>();
        loginCredentials.put("username", username);
        loginCredentials.put("paswword", password);
        String  uri = String.format("/api/login?username=%s&password=%s",username, password);
        String token = post(uri).getHeader(TOKEN_KEY).toString();
        System.out.println(token);
    }

    @Test
    public void getLoginAuthShouldReturnToken() {

        given().
                parameters("username", username, "password", password)
                .when()
                .post("/api/login")
                .then()
                .header(TOKEN_KEY, not(isEmptyString()));

    }

    @Test
    public void getClientsShouldReturnAllClientsForTheCurrentUser() {
        when()
                .get(CLIENTS_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", hasItems("Kofi Koduah", "Manu Asamoah"));
    }

}
