package app.core.repositories;

import java.util.List;

import app.core.beans.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("plyRepo")
public interface PlayerRepository extends JpaRepository<Player, Long> {

	List<Player> findPlayerByName(String name);
}
