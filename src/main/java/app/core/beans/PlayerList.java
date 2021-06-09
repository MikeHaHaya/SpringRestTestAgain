package app.core.beans;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {

    List<Player> players = new ArrayList<>();;

    public PlayerList() {
    }

    public PlayerList(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "PlayerList{" +
                "players=" + players +
                '}';
    }
}
