public class PatientBST {

    private PatientNode root;

    public void insert(Patient patient) {

        PatientNode newNode = new PatientNode(patient);

        if (root == null) {
            root = newNode;
            return;
        }

        PatientNode current = root;

        while (true) {

            if (patient.getPatientId() < current.patient.getPatientId()) {

                if (current.left == null) {
                    current.left = newNode;
                    return;
                }

                current = current.left;

            } else if (patient.getPatientId() > current.patient.getPatientId()) {

                if (current.right == null) {
                    current.right = newNode;
                    return;
                }

                current = current.right;

            } else {

                System.out.println("Patient ID already exists.");
                return;
            }
        }
    }

    public Patient search(int patientId) {

        PatientNode current = root;

        while (current != null) {

            if (patientId == current.patient.getPatientId()) {
                return current.patient;     // found the patient → return it
            }

            if (patientId < current.patient.getPatientId()) {
                current = current.left;     // search left subtree
            } else {
                current = current.right;    // search right subtree
            }
        }

        return null;    // patient not found
    }

    public void delete(int patientId) {

        if (search(patientId) == null) {
            System.out.println("Patient not found.");
            return;
        }

        root = deleteRecursive(root, patientId);

        System.out.println("Patient deleted.");
    }

    private PatientNode deleteRecursive(PatientNode current, int patientId) {

        if (current == null) {
            return null;
        }

        if (patientId < current.patient.getPatientId()) {

            current.left = deleteRecursive(current.left, patientId);

        } else if (patientId > current.patient.getPatientId()) {

            current.right = deleteRecursive(current.right, patientId);

        } else {

            // No children
            if (current.left == null && current.right == null) {
                return null;
            }

            // Only right child
            if (current.left == null) {
                return current.right;
            }

            // Only left child
            if (current.right == null) {
                return current.left;
            }

            // Two children
            PatientNode successor = findMinimum(current.right);

            current.patient = successor.patient;

            current.right = deleteRecursive(
                    current.right,
                    successor.patient.getPatientId()
            );
        }

        return current;
    }

    private PatientNode findMinimum(PatientNode node) {

        PatientNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void displayInOrder() {

        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }

        displayInOrderRecursive(root);
    }

    private void displayInOrderRecursive(PatientNode current) {

        if (current == null) {
            return;
        }

        // Visit left subtree
        displayInOrderRecursive(current.left);

        // Visit current patient
        System.out.println(current.patient);

        // Visit right subtree
        displayInOrderRecursive(current.right);
    }
}