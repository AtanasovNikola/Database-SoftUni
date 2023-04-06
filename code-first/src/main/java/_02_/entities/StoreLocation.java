package _02_.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "_2_store_locations")
public class StoreLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String locationName;


    @OneToMany(targetEntity = Sale.class,mappedBy = "storeLocation")
    private Set<Sale> sales;

    public StoreLocation(){}

    public StoreLocation(String locationName) {
        this.locationName = locationName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
