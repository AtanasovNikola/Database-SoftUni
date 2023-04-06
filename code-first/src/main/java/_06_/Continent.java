package _06_;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "continents")
public class Continent {

    private static final long serialVersionUID = 166666999789235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String name;

    @ManyToMany(mappedBy = "continents")
    private Set<Country> countries;

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

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
