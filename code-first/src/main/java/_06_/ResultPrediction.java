package _06_;

import javax.persistence.*;

@Entity(name = "results_prediction")
public class ResultPrediction {
    private static final long serialVersionUID = 166666999789235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Enumerated(EnumType.STRING)
    private PredictionEnum prediction;



}