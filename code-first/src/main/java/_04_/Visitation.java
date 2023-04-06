package _04_;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "visitations")
public class Visitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_time", nullable = false)
    private String dateTime;

    @Column(name = "comment", nullable = false)
    private String comment;


    private int patientId;

    @ManyToOne
    private Patient patient;

    @OneToMany(mappedBy = "visitation")
    private List<Diagnose> diagnoses = new ArrayList<>();

    @OneToMany(mappedBy = "visitation")
    private List<Medicament> medicaments = new ArrayList<>();

    public Visitation() {
    }

    public Visitation(int patientId,String dateTime, String comment,Diagnose diagnose,Medicament medicament) {
        this.patientId = patientId;
        this.dateTime = dateTime;
        this.comment = comment;
        this.diagnoses.add(diagnose);
        this.medicaments.add(medicament);
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(List<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
