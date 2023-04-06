package _06_;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "bet_games")
public class BetGame implements Serializable {
    private static final long serialVersionUID = 166666999789235L;

    @Id
    @OneToOne
    private Game game;

    @Id
    @OneToOne
    private Bet bet;

    @OneToOne
    @JoinColumn(name = "result_prediction")
    private ResultPrediction resultPrediction;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getMoney() {
        return bet;
    }

    public void setMoney(Bet money) {
        this.bet = money;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}