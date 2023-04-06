package _06_;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "countries")
public class Country {

    private static final long serialVersionUID = 313161999789235L;

    @Id
    @Column(nullable = false, length = 3, unique = true)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents",
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"))
    private Set<Continent> continents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Continent> getContinents() {
        return continents;
    }

    public void setContinents(Set<Continent> continents) {
        this.continents = continents;
    }
}
