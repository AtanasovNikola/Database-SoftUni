package _03_;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "_3_teachers")
public class Teacher extends Person {

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "salary_per_hour", nullable = false)
    private BigDecimal salaryPerHour;

@OneToMany(mappedBy = "teacher")
private Set<Course> courses;


    public Teacher() {
        super();
    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, BigDecimal salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}
