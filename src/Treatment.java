public class Treatment {

    private int patientId;
    private String patientName;
    private String treatment;
    private String doctor;

    public Treatment(int patientId,
                     String patientName,
                     String treatment,
                     String doctor) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.treatment = treatment;
        this.doctor = doctor;
    }

    public void displayTreatment() {

        System.out.println(
                "Patient ID: " + patientId +
                " | Patient: " + patientName +
                " | Doctor: " + doctor +
                " | Treatment: " + treatment
        );
    }
}