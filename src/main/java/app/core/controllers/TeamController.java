package app.core.controllers;

import app.core.beans.Player;
import app.core.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

// http://localhost:8080/team
@RequestMapping("/team")
@RestController
@DependsOn("teamService")
public class TeamController {

    @Autowired
    private TeamService service;

    // http://localhost:8080/team/add-player
    @PostMapping("/add-player")
    public long addPlayer(@RequestBody Player player) {
        service.addPlayer(player);
        return player.getId();
    }

    // http://localhost:8080/team/get-player?id=(id here)
    @GetMapping("/get-player")
    public Player getPlayer(@RequestParam long id) {
        return service.getPlayer(id);
    }

    // http://localhost:8080/team/get-players
    @GetMapping("/get-players")
    public List<Player> getPlayers() {
        return service.getPlayers();
    }

    // http://localhost:8080/team/get-players-by-name?name=(name here)
    @GetMapping("/get-players-by-name")
    public List<Player> getPlayersByName(@RequestParam String name) {
        return service.getPlayer(name);
    }
}
