package app.core.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import app.core.beans.Player;
import app.core.beans.Game;
import app.core.repositories.PlayerRepository;
import app.core.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@Service("teamService")
@Transactional
@DependsOn({"gameRepo", "plyRepo"})
public class TeamService {

	@Autowired
	private PlayerRepository plyRepo;
	@Autowired
	private GameRepository gameRepo;

	public void addPlayer(Player p){
		plyRepo.save(p);
	}
	
	public Player getPlayer(long playerId){
		Optional<Player> opt = plyRepo.findById(playerId);
		if (opt.isPresent())
			return opt.get();
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
	
	public List<Player> getPlayer(String name){
		return plyRepo.findPlayerByName(name);
	}
	
	public List<Player> getPlayers(){
		return plyRepo.findAll();
	}
	
	public List<Game> getGames(){
		return gameRepo.findAll();
	}

	public List<Game> getGames(Date date){
		return gameRepo.findGameByDateEquals(date);
	}
	
	public List<Game> getGames(Date from, Date to){
		return gameRepo.findGameByDateBetween(from, to);
	}
}
