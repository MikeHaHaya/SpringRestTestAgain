package app.core.repositories;

import java.sql.Date;
import java.util.List;

import app.core.beans.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("gameRepo")
public interface GameRepository extends JpaRepository<Game,Long> {

	List<Game> findGameByDateEquals(Date date);
	List<Game> findGameByDateBetween(Date date1, Date date2);
}
