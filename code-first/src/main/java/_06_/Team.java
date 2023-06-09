package _06_;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity(name = "teams")
public class Team implements Serializable {

    private static final long serialVersionUID = 313161999789235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(length = 3)
    private String initials;

    @ManyToOne
    @JoinColumn(name = "primary_kit_color_id")
    private Color primaryKitColor;

    @ManyToOne
    @JoinColumn(name = "secondary_kit_color_id")
    private Color secondaryKitColor;

    @ManyToOne
    private Town town;

    private BigDecimal budget;

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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Color getPrimaryKitColor() {
        return primaryKitColor;
    }

    public void setPrimaryKitColor(Color primaryKitColor) {
        this.primaryKitColor = primaryKitColor;
    }

    public Color getSecondaryKitColor() {
        return secondaryKitColor;
    }

    public void setSecondaryKitColor(Color secondaryKitColor) {
        this.secondaryKitColor = secondaryKitColor;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
