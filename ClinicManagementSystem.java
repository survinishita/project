import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    int id;
    String name;
    int age;
    String phone;

    Patient(int id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}

class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}

class Appointment {
    int id;
    int patientId;
    int doctorId;
    String date;
    String time;

    Appointment(int id, int patientId, int doctorId, String date, String time) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
    }
}

class Prescription {
    int id;
    int patientId;
    int doctorId;
    String medicine;
    String dosage;

    Prescription(int id, int patientId, int doctorId, String medicine, String dosage) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicine = medicine;
        this.dosage = dosage;
    }
}

public class ClinicManagementSystem {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static ArrayList<Prescription> prescriptions = new ArrayList<>();

    static int patientId = 1;
    static int appointmentId = 1;
    static int prescriptionId = 1;

    public static void main(String[] args) {
        addSampleDoctors();

        while (true) {
            System.out.println("\n======================================");
            System.out.println("   CLINIC MANAGEMENT SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Register Patient");
            System.out.println("2. View Patients");
            System.out.println("3. View Doctors");
            System.out.println("4. Book Appointment");
            System.out.println("5. View Appointments");
            System.out.println("6. Add Prescription");
            System.out.println("7. View Prescriptions");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    viewPatients();
                    break;
                case 3:
                    viewDoctors();
                    break;
                case 4:
                    bookAppointment();
                    break;
                case 5:
                    viewAppointments();
                    break;
                case 6:
                    addPrescription();
                    break;
                case 7:
                    viewPrescriptions();
                    break;
                case 8:
                    System.out.println("Thank you for using the system!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void addSampleDoctors() {
        doctors.add(new Doctor(101, "Dr. Ananya", "General Physician"));
        doctors.add(new Doctor(102, "Dr. Rahul", "Dermatologist"));
        doctors.add(new Doctor(103, "Dr. Meera", "Pediatrician"));
    }

    static void registerPatient() {
        System.out.print("Enter patient name: ");
        String name = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        patients.add(new Patient(patientId, name, age, phone));
        System.out.println("Patient registered successfully!");
        System.out.println("Patient ID: " + patientId);
        patientId++;
    }

    static void viewPatients() {
        System.out.println("\n--- PATIENT LIST ---");

        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }

        for (Patient p : patients) {
            System.out.println("ID: " + p.id +
                    " | Name: " + p.name +
                    " | Age: " + p.age +
                    " | Phone: " + p.phone);
        }
    }

    static void viewDoctors() {
        System.out.println("\n--- DOCTOR LIST ---");

        for (Doctor d : doctors) {
            System.out.println("ID: " + d.id +
                    " | Name: " + d.name +
                    " | Specialization: " + d.specialization);
        }
    }

    static void bookAppointment() {
        if (patients.isEmpty()) {
            System.out.println("Please register a patient first.");
            return;
        }

        viewPatients();
        System.out.print("Enter patient ID: ");
        int pId = sc.nextInt();

        viewDoctors();
        System.out.print("Enter doctor ID: ");
        int dId = sc.nextInt();
        sc.nextLine();

        if (!patientExists(pId) || !doctorExists(dId)) {
            System.out.println("Invalid patient or doctor ID.");
            return;
        }

        System.out.print("Enter appointment date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        System.out.print("Enter appointment time: ");
        String time = sc.nextLine();

        appointments.add(new Appointment(appointmentId, pId, dId, date, time));
        System.out.println("Appointment booked successfully!");
        System.out.println("Appointment ID: " + appointmentId);
        appointmentId++;
    }

    static void viewAppointments() {
        System.out.println("\n--- APPOINTMENT LIST ---");

        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }

        for (Appointment a : appointments) {
            System.out.println("Appointment ID: " + a.id +
                    " | Patient ID: " + a.patientId +
                    " | Doctor ID: " + a.doctorId +
                    " | Date: " + a.date +
                    " | Time: " + a.time);
        }
    }

    static void addPrescription() {
        if (patients.isEmpty()) {
            System.out.println("Please register a patient first.");
            return;
        }

        viewPatients();
        System.out.print("Enter patient ID: ");
        int pId = sc.nextInt();

        viewDoctors();
        System.out.print("Enter doctor ID: ");
        int dId = sc.nextInt();
        sc.nextLine();

        if (!patientExists(pId) || !doctorExists(dId)) {
            System.out.println("Invalid patient or doctor ID.");
            return;
        }

        System.out.print("Enter medicine name: ");
        String medicine = sc.nextLine();

        System.out.print("Enter dosage: ");
        String dosage = sc.nextLine();

        prescriptions.add(
                new Prescription(prescriptionId, pId, dId, medicine, dosage)
        );

        System.out.println("Prescription added successfully!");
        System.out.println("Prescription ID: " + prescriptionId);
        prescriptionId++;
    }

    static void viewPrescriptions() {
        System.out.println("\n--- PRESCRIPTION LIST ---");

        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions available.");
            return;
        }

        for (Prescription p : prescriptions) {
            System.out.println("Prescription ID: " + p.id +
                    " | Patient ID: " + p.patientId +
                    " | Doctor ID: " + p.doctorId +
                    " | Medicine: " + p.medicine +
                    " | Dosage: " + p.dosage);
        }
    }

    static boolean patientExists(int id) {
        for (Patient p : patients) {
            if (p.id == id) {
                return true;
            }
        }
        return false;
    }

    static boolean doctorExists(int id) {
        for (Doctor d : doctors) {
            if (d.id == id) {
                return true;
            }
        }
        return false;
    }
}
