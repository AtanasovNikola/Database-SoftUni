package _05_;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.Month;

@Entity(name = "_5_credit_card")
public class CreditCard extends BillingDetail{
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "expiration_month")
    private Month expirationMonth;
    @Column(name = "expiration_year")
    private int expirationYear;
    @OneToOne
    private User userId;

    public CreditCard(){}

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public CreditCard(int ownerId, String cardType, Month expirationMonth, int expirationYear) {
        super(ownerId);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Month getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Month expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }
}
