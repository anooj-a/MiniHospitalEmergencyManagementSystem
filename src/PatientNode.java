public class PatientNode {

    Patient patient;
    PatientNode left;
    PatientNode right;

    //constructor
    public PatientNode(Patient patient) {   
        this.patient = patient;
        this.left = null;
        this.right = null;
    }
}