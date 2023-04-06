package _05_;

import javax.persistence.*;


@MappedSuperclass
public class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "owner_id")
    private int ownerId;

    public BillingDetail() {
    }

    public BillingDetail(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return ownerId;
    }

    public void setOwner(int owner) {
        this.ownerId = owner;
    }
}
