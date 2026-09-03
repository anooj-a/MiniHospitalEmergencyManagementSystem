public class EmergencyQueue {

    private QueueNode front;
    private QueueNode rear;

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Patient patient) {

        QueueNode newNode = new QueueNode(patient);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        System.out.println("Patient added to emergency queue.");
    }

    public Patient dequeue() {

        if (isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return null;
        }

        Patient patient = front.patient;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        System.out.println("Treating patient: "
                + patient.getPatientName());

        return patient;
    }

    public void display() {

        if (isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return;
        }

        QueueNode current = front;

        System.out.println("--- Emergency Waiting Queue ---");

        while (current != null) {

            current.patient.displayPatient();

            current = current.next;
        }
    }
}