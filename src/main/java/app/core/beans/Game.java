package app.core.beans;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String title;
    @Column
    private Date date;
    @Column
    private int score;

    public Game() {
    }

    public Game(String title, Date date,int score) {
        this.title = title;
        this.date = date;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", score=" + score +
                '}';
    }
}
