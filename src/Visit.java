public class Visit {

    private int visitId;
    private String date;
    private String doctor;
    private String diagnosis;
    private String treatment;

    public Visit(int visitId,
                 String date,
                 String doctor,
                 String diagnosis,
                 String treatment) {

        this.visitId = visitId;
        this.date = date;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getVisitId() {
        return visitId;
    }

    public void displayVisit() {

        System.out.println(
                "Visit ID: " + visitId +
                " | Date: " + date +
                " | Doctor: " + doctor +
                " | Diagnosis: " + diagnosis +
                " | Treatment: " + treatment
        );
    }
}