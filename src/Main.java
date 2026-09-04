import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // Scanner is used to get input from the user
    private static final Scanner scanner = new Scanner(System.in);

    // Create the required data structures
    private static final PatientBST patientBST = new PatientBST();

    private static final EmergencyQueue emergencyQueue =
            new EmergencyQueue();

    private static final TreatmentStack treatmentStack =
            new TreatmentStack();

    // Each Patient ID is connected to that patient's visit history
    private static final Map<Integer, VisitHistory> visitHistories =
            new HashMap<>();


    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        while (true) {

            showMainMenu();

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    deletePatient();
                    break;

                case 4:
                    displayPatients();
                    break;

                case 5:
                    addEmergencyPatient();
                    break;

                case 6:
                    treatNextEmergencyPatient();
                    break;

                case 7:
                    displayEmergencyQueue();
                    break;

                case 8:
                    addTreatmentRecord();
                    break;

                case 9:
                    removeLatestTreatment();
                    break;

                case 10:
                    displayTreatmentHistory();
                    break;

                case 11:
                    addPatientVisit();
                    break;

                case 12:
                    searchPatientVisit();
                    break;

                case 13:
                    removePatientVisit();
                    break;

                case 14:
                    displayPatientVisits();
                    break;

                case 0:
                    System.out.println("System closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }


    // =========================================================
    // MAIN MENU
    // =========================================================

    private static void showMainMenu() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("     MINI HOSPITAL EMERGENCY MANAGEMENT");
        System.out.println("==============================================");

        System.out.println();
        System.out.println(" -- Patient Records (BST) --");
        System.out.println(" ---------------------------");
        System.out.println();
        System.out.println("1.  Register Patient");
        System.out.println("2.  Search Patient");
        System.out.println("3.  Delete Patient");
        System.out.println("4.  Display Patients (in-order by ID)");

        System.out.println("----------------------------------------------");

        System.out.println();
        System.out.println(" -- Emergency Queue --");
        System.out.println(" ---------------------");
        System.out.println();
        System.out.println("5.  Add Patient to Emergency Queue");
        System.out.println("6.  Treat Next Emergency Patient (Dequeue - remove from front)");
        System.out.println("7.  Display Emergency Queue");

        System.out.println("----------------------------------------------");

        System.out.println();
        System.out.println(" -- Treatment History (Stack) --");
        System.out.println(" -------------------------------");
        System.out.println();
        System.out.println("8.  Add Treatment Record");
        System.out.println("9.  Remove Latest Treatment");
        System.out.println("10. Display Treatment History");

        System.out.println("----------------------------------------------");

        System.out.println("11. Add Patient Visit");
        System.out.println("12. Search Patient Visit");
        System.out.println("13. Remove Patient Visit");
        System.out.println("14. Display Patient Visits");

        System.out.println("----------------------------------------------");

        System.out.println("0.  Exit");

        System.out.println("==============================================");
    }


    // =========================================================
    // PATIENT BST
    // =========================================================

    // Register a new patient
    private static void registerPatient() {

        System.out.println();
        System.out.println("--- Register Patient ---");

        int id = readPositiveInt("Patient ID: ");

        // Check whether Patient ID already exists
        if (patientBST.search(id) != null) {

            System.out.println("Patient ID already exists.");

            return;
        }

        String name =
                readNonEmpty("Patient name: ");

        int age =
                readPositiveInt("Age: ");

        String contact =
                readNonEmpty("Contact number: ");

        String condition =
                readNonEmpty("Medical condition: ");


        // Create Patient object
        Patient patient =
                new Patient(
                        id,
                        name,
                        age,
                        contact,
                        condition
                );


        // Insert patient into BST
        patientBST.insert(patient);


        // Create an empty visit history for the patient
        visitHistories.putIfAbsent(
                id,
                new VisitHistory()
        );


        System.out.println(
                "Patient registered successfully."
        );
    }


    // Search for a patient in the BST
    private static void searchPatient() {

        System.out.println();
        System.out.println("--- Search Patient ---");

        int id =
                readPositiveInt(
                        "Enter Patient ID: "
                );


        Patient patient =
                patientBST.search(id);


        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

        } else {

            patient.displayPatient();
        }
    }


    // Delete a patient from BST
    private static void deletePatient() {

        System.out.println();
        System.out.println("--- Delete Patient ---");

        int id =
                readPositiveInt(
                        "Enter Patient ID to delete: "
                );


        patientBST.delete(id);
    }


    // Display patients using BST in-order traversal
    private static void displayPatients() {

        System.out.println();
        System.out.println("--- Patient Records ---");

        System.out.println(
                "Patients are displayed in ascending Patient ID order."
        );

        patientBST.displayInOrder();
    }
    


    // =========================================================
    // EMERGENCY QUEUE
    // =========================================================

    // Add an existing patient to emergency queue
    private static void addEmergencyPatient() {

        System.out.println();
        System.out.println("--- Add Emergency Patient ---");

        int id = readPositiveInt("Enter Patient ID: ");


        // Find patient from BST
        Patient patient = patientBST.search(id);


        if (patient == null) {
            System.out.println("Patient not found. Register the patient first.");
            return;
        }


        // Add patient to Queue
        emergencyQueue.enqueue(patient);
    }


    // Treat the patient at the front of the queue
    private static void treatNextEmergencyPatient() {

        System.out.println();
        System.out.println("--- Treat Next Emergency Patient ---");


        // FIFO: first patient added is treated first
        Patient patient = emergencyQueue.dequeue();


        if (patient != null) {

            System.out.println();
            System.out.println("Patient selected for treatment:");

            patient.displayPatient();


            String treatment = readNonEmpty("Treatment provided: ");


            String doctor = readNonEmpty("Doctor name: ");


            // Create completed treatment record
            Treatment treatmentRecord = new Treatment(
                            patient.getPatientId(),
                            patient.getPatientName(),
                            treatment,
                            doctor
                );


            // Add completed treatment to Stack
            treatmentStack.push( treatmentRecord);


            System.out.println("Treatment completed and added to treatment history.");
        }
    }


    // Display emergency queue
    private static void displayEmergencyQueue() {

        System.out.println();
        System.out.println("--- Emergency Waiting Queue ---");

        emergencyQueue.display();
    }


    // =========================================================
    // TREATMENT STACK
    // =========================================================

    // Manually add a treatment record
    private static void addTreatmentRecord() {

        System.out.println();
        System.out.println("--- Add Treatment Record ---");

        int id =
                readPositiveInt(
                        "Patient ID: "
                );


        Patient patient =
                patientBST.search(id);


        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }


        String treatment =
                readNonEmpty(
                        "Treatment: "
                );


        String doctor =
                readNonEmpty(
                        "Doctor name: "
                );


        Treatment treatmentRecord =
                new Treatment(
                        patient.getPatientId(),
                        patient.getPatientName(),
                        treatment,
                        doctor
                );


        // Push onto Stack
        treatmentStack.push(
                treatmentRecord
        );
    }


    // Remove most recently completed treatment
    private static void removeLatestTreatment() {

        System.out.println();
        System.out.println("--- Remove Latest Treatment ---");


        // LIFO: last treatment added is removed first
        Treatment treatment =
                treatmentStack.pop();


        if (treatment != null) {

            System.out.println(
                    "Removed treatment:"
            );

            treatment.displayTreatment();
        }
    }


    // Display treatment history
    private static void displayTreatmentHistory() {

        System.out.println();
        System.out.println("--- Treatment History ---");

        treatmentStack.display();
    }


    // =========================================================
    // PATIENT VISIT HISTORY
    // =========================================================

    /*
     * Find the VisitHistory belonging to a patient.
     *
     * Each patient has their own singly linked list.
     */
    private static VisitHistory getVisitHistoryForPatient(
            int patientId) {


        // First check whether patient exists in BST
        Patient patient =
                patientBST.search(patientId);


        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return null;
        }


        // Get existing visit history
        // or create one if it does not exist
        return visitHistories.computeIfAbsent(
                patientId,
                key -> new VisitHistory()
        );
    }


    // Add a visit to a patient's linked list
    private static void addPatientVisit() {

        System.out.println();
        System.out.println("--- Add Patient Visit ---");

        int patientId =
                readPositiveInt(
                        "Patient ID: "
                );


        VisitHistory history =
                getVisitHistoryForPatient(
                        patientId
                );


        if (history == null) {
            return;
        }


        int visitId =
                readPositiveInt(
                        "Visit ID: "
                );


        String date =
                readNonEmpty(
                        "Date: "
                );


        String doctor =
                readNonEmpty(
                        "Doctor: "
                );


        String diagnosis =
                readNonEmpty(
                        "Diagnosis: "
                );


        String treatment =
                readNonEmpty(
                        "Treatment: "
                );


        Visit visit =
                new Visit(
                        visitId,
                        date,
                        doctor,
                        diagnosis,
                        treatment
                );


        // Add visit to linked list
        history.addVisit(visit);
    }


    // Search a visit
    private static void searchPatientVisit() {

        System.out.println();
        System.out.println("--- Search Patient Visit ---");

        int patientId =
                readPositiveInt(
                        "Patient ID: "
                );


        VisitHistory history =
                getVisitHistoryForPatient(
                        patientId
                );


        if (history == null) {
            return;
        }


        int visitId =
                readPositiveInt(
                        "Visit ID: "
                );


        boolean found =
                history.searchVisit(
                        visitId
                );


        if (!found) {

            System.out.println(
                    "Visit not found."
            );
        }
    }


    // Remove a visit from linked list
    private static void removePatientVisit() {

        System.out.println();
        System.out.println("--- Remove Patient Visit ---");

        int patientId =
                readPositiveInt(
                        "Patient ID: "
                );


        VisitHistory history =
                getVisitHistoryForPatient(
                        patientId
                );


        if (history == null) {
            return;
        }


        int visitId =
                readPositiveInt(
                        "Visit ID: "
                );


        boolean removed =
                history.removeVisit(
                        visitId
                );


        if (removed) {

            System.out.println(
                    "Visit removed successfully."
            );

        } else {

            System.out.println(
                    "Visit not found."
            );
        }
    }


    // Display all visits for a patient
    private static void displayPatientVisits() {

        System.out.println();
        System.out.println("--- Display Patient Visits ---");

        int patientId =
                readPositiveInt(
                        "Patient ID: "
                );


        VisitHistory history =
                getVisitHistoryForPatient(
                        patientId
                );


        if (history != null) {

            history.displayVisits();
        }
    }


    // =========================================================
    // INPUT VALIDATION
    // =========================================================

    // Read an integer safely
    private static int readInt(String prompt) {

        while (true) {

            System.out.print(prompt);

            String input =
                    scanner.nextLine();


            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid integer."
                );
            }
        }
    }


    // Read a positive integer
    private static int readPositiveInt(
            String prompt) {

        while (true) {

            int value =
                    readInt(prompt);


            if (value > 0) {

                return value;
            }


            System.out.println(
                    "Value must be greater than 0."
            );
        }
    }


    // Read text and prevent empty input
    private static String readNonEmpty(
            String prompt) {

        while (true) {

            System.out.print(prompt);

            String value =
                    scanner.nextLine().trim();


            if (!value.isEmpty()) {

                return value;
            }


            System.out.println(
                    "Input cannot be empty."
            );
        }
    }
}