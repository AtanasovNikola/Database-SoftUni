package _06_;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "player_statistics")
public class PlayerStatistic implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @Column(name = "scored_goals")
    private int scoredGoals;
    @Column(name = "player_assists")
    private int playerAssists;
    @Column(name = "minutes_played")
    private int minutesPlayed;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }
}
