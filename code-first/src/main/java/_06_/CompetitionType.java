package _06_;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "competition_types")
public class CompetitionType implements Serializable {
    private static final long serialVersionUID = 166666999789235L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
