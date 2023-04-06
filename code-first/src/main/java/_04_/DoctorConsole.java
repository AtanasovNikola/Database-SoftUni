package _04_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DoctorConsole {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("CodeFirstEx");
    EntityManager em = factory.createEntityManager();
    private Scanner scanner;
    private Set<Patient> patients;
    private Set<Visitation> visitations;


    public DoctorConsole() {
        scanner = new Scanner(System.in);
        this.patients = new HashSet<>();
        this.visitations = new HashSet<>();
    }

    public void start() {
        em.getTransaction().begin();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    viewPatients();
                    break;
                case 3:
                    addVisitation();
                    break;
                case 4:
                    viewVisitations();
                    break;
                case 5:
                    running = false;
                    em.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("1. Add patient");
        System.out.println("2. View patients");
        System.out.println("3. Add visitation");
        System.out.println("4. View visitations");
        System.out.println("5. Exit");
        System.out.print("Please choose an option: ");
    }

    private void addPatient() {
        System.out.println("First name: ");
        String firstName = scanner.next();
        System.out.println("Last name: ");
        String lastName = scanner.next();
        System.out.println("Address: ");
        String address = scanner.next();
        System.out.println("Email: ");
        String email = scanner.next();
        System.out.println("Date of birth (yyyy-MM-dd): ");
        String dobStr = scanner.next();
        System.out.println("Has medical insurance (true/false): ");
        boolean hasInsurance = scanner.nextBoolean();

        Patient patient = new Patient(firstName, lastName, address, email, dobStr, hasInsurance);
        patients.add(patient);
        em.persist(patient);
        System.out.println("Patient added successfully.");
        em.getTransaction().commit();
    }

    private void viewPatients() {
        int id = 5;
        Patient patient = em.find(Patient.class, id);
        while (true) {

            try {
                System.out.println(patient.getFirstName() + " " + patient.getLastName());
                id++;

                patient = em.find(Patient.class, id);
            } catch (NullPointerException e) {
                return;
            }
        }


    }

    private void addVisitation() {
        System.out.println("Patient ID: ");
        int patientId = Integer.parseInt(scanner.next());
        System.out.println("Date (yyyy-MM-dd): ");
        String dateStr = scanner.next();
        System.out.println("Comments: ");
        String comments = scanner.next();
        System.out.println("Diagnose name: ");
        String diagnoseName = scanner.next();
        System.out.println("Diagnose comment: ");
        String diagnoseComment = scanner.next();
        System.out.println("Medicament name: ");
        String medicamentName = scanner.next();


        Medicament medicament = new Medicament(medicamentName);
        Diagnose diagnose = new Diagnose(diagnoseName, diagnoseComment);
        Visitation visitation = new Visitation(patientId, dateStr, comments, diagnose, medicament);
        visitations.add(visitation);
        em.persist(visitation);
        System.out.println("Visitation added successfully.");
        em.getTransaction().commit();
    }

    private void viewVisitations() {
        int id = 1;
        Visitation visitation = em.find(Visitation.class, id);
        while (true) {
            id++;
            try {
                System.out.println(visitation.getId() + " " + visitation.getComment());
                visitation = em.find(Visitation.class, id);
            } catch (NullPointerException e) {
                return;
            }
        }
    }
}

