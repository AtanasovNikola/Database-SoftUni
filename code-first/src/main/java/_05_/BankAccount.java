package _05_;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity(name = "_5_bank_account")
public class BankAccount extends BillingDetail{
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "swift")
    private String SWIFT;

    @OneToOne(mappedBy = "bankAccount")
    private User user;

    public BankAccount(){}

    public BankAccount(int ownerId,String bankName, String SWIFT) {
        super(ownerId);
        this.bankName = bankName;
        this.SWIFT = SWIFT;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSWIFT() {
        return SWIFT;
    }

    public void setSWIFT(String SWIFT) {
        this.SWIFT = SWIFT;
    }
}
