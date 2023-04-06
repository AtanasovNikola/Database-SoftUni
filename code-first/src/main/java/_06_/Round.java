package _06_;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "rounds")
public class Round implements Serializable {

    private static final long serialVersionUID = 313161999789235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;


}
