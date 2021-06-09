package app.core;

import app.core.beans.Game;
import app.core.beans.Player;
import app.core.beans.PlayerList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SpringWebTest {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String CONTROLLER_BASE_URI = "http://localhost:8080/team";

    // main
    public static void main(String[] args) {
        SpringApplication.run(SpringWebTest.class, args);

        callAddPlayer();
//        callGetPlayer();
//        callGetPlayers();
//        callGetPlayersByName();
    }

    // Calls the add player method with random data of two players
    private static void callAddPlayer() {

        // Creates a list of players
        List<Player> players = new ArrayList<>();

        // Creates a player
        Player player1 = new Player();
        Player player2 = new Player();

        // Creates a list of games
        Game[] gameArray1 = {
                new Game("Game1", Date.valueOf(LocalDate.of(2020, 3, 5)), 85),
                new Game("Game2", Date.valueOf(LocalDate.of(2020, 1, 25)), 55),
                new Game("Game3", Date.valueOf(LocalDate.of(2020, 5, 20)), 100)};
        List<Game> gameList1 = Arrays.asList(gameArray1);

        Game[] gameArray2 = {
                new Game("Game1", Date.valueOf(LocalDate.of(2020, 4, 9)), 90),
                new Game("Game2", Date.valueOf(LocalDate.of(2020, 2, 15)), 80),
                new Game("Game3", Date.valueOf(LocalDate.of(2020, 6, 18)), 60)};
        List<Game> gameList2 = Arrays.asList(gameArray2);

        player1.setName("Mike");
        player1.setGames(gameList1);
        player2.setName("Moshe");
        player2.setGames(gameList2);

        players.add(player1);
        players.add(player2);

//        addPlayerList(players);
        restTemplate.postForObject(CONTROLLER_BASE_URI + "/add-player", players, ResponseEntity.class);

    }

    // Calls the get player method with id=1
    private static void callGetPlayer() {

        Player player = restTemplate.getForObject(CONTROLLER_BASE_URI + "/get-player/1", Player.class);
        System.out.println(player);
    }

    // Calls the get all players method
    private static void callGetPlayers() {

        ResponseEntity<Player[]> response = restTemplate.getForEntity(CONTROLLER_BASE_URI + "/get-players", Player[].class);
        Player[] players = response.getBody();
        System.out.println(Arrays.toString(players));
    }

    // Calls the get all players by name method with name=Mike
    private static void callGetPlayersByName() {

        ResponseEntity<Player[]> response = restTemplate.getForEntity(CONTROLLER_BASE_URI + "/get-players-by-name?name=Mike", Player[].class);
        Player[] players = response.getBody();
        System.out.println(Arrays.toString(players));
    }

    // Uses HttpHeaders and exchange method to add players
    private static void addPlayerList(List<Player> players) {

        for (Player player : players) {

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<Player> entity = new HttpEntity<Player>(player, headers);

            ResponseEntity<String> responsePlayer = restTemplate.exchange(CONTROLLER_BASE_URI + "/add-player",
                    HttpMethod.POST, entity, String.class);

            System.out.println(responsePlayer);
        }
    }

}
