package _06_;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "competitions")
public class Competition implements Serializable {
    private static final long serialVersionUID = 166666999789235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @ManyToOne
    private CompetitionType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompetitionType getType() {
        return type;
    }

    public void setType(CompetitionType type) {
        this.type = type;
    }
}
