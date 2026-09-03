public class VisitHistory {

    private VisitNode head;

    public void addVisit(Visit visit) {

        VisitNode newNode = new VisitNode(visit);

        if (head == null) {

            head = newNode;

        } else {

            VisitNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        System.out.println("Visit added.");
    }

    public boolean searchVisit(int visitId) {

        VisitNode current = head;

        while (current != null) {

            if (current.visit.getVisitId() == visitId) {

                current.visit.displayVisit();

                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean removeVisit(int visitId) {

        if (head == null) {
            return false;
        }

        if (head.visit.getVisitId() == visitId) {

            head = head.next;

            return true;
        }

        VisitNode current = head;

        while (current.next != null) {

            if (current.next.visit.getVisitId() == visitId) {

                current.next = current.next.next;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    public void displayVisits() {

        if (head == null) {

            System.out.println("No visits recorded.");

            return;
        }

        VisitNode current = head;

        System.out.println("--- Patient Visit History ---");

        while (current != null) {

            current.visit.displayVisit();

            current = current.next;
        }
    }
}