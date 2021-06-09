package app.core;

import app.core.beans.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class SpringWebTest {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String CONTROLLER_BASE_URI = "http://localhost:8080/team";

    // main
    public static void main(String[] args) {
        SpringApplication.run(SpringWebTest.class, args);

        callAddPlayer();
        callGetPlayer();
        callGetPlayers();
        callGetPlayersByName();
    }

    // Calls the add player method with random data
    private static void callAddPlayer() {

        Player player = new Player();
        player.setName("Mike");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Player> entity = new HttpEntity<Player>(player, headers);

        ResponseEntity<String> responsePlayer = restTemplate.exchange(CONTROLLER_BASE_URI + "/add-player",
                HttpMethod.POST, entity, String.class);

        System.out.println(responsePlayer);
    }

    // Calls the get player method with id=1
    private static void callGetPlayer() {

        Player player = restTemplate.getForObject(CONTROLLER_BASE_URI + "/get-player?id=1", Player.class);
        System.out.println(player);
    }

    // Calls the get all players method
    private static void callGetPlayers() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange
                (CONTROLLER_BASE_URI + "/get-players", HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }

    // Calls the get all players by name method with name=Mike
    private static void callGetPlayersByName() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange
                (CONTROLLER_BASE_URI + "/get-players-by-name?name=Mike", HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }

}
