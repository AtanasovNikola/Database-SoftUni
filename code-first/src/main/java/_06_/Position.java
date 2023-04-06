package _06_;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "positions")
public class Position {

    private static final long serialVersionUID = 313161999789235L;

    @Id
    @Column(length = 2,unique = true)
    private String id;

    @Column(nullable = false,name = "position_description")
    private String positionDescription;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
}
