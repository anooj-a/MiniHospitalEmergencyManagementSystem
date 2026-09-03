public class TreatmentStack {

    private StackNode top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Treatment treatment) {

        StackNode newNode = new StackNode(treatment);

        newNode.next = top;

        top = newNode;

        System.out.println("Treatment added to history.");
    }

    public Treatment pop() {

        if (isEmpty()) {
            System.out.println("Treatment history is empty.");
            return null;
        }

        Treatment treatment = top.treatment;

        top = top.next;

        System.out.println("Most recent treatment removed.");

        return treatment;
    }

    public void display() {

        if (isEmpty()) {
            System.out.println("Treatment history is empty.");
            return;
        }

        StackNode current = top;

        System.out.println("--- Treatment History ---");

        while (current != null) {

            current.treatment.displayTreatment();

            current = current.next;
        }
    }
}