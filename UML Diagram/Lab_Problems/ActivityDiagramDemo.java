class StudentRegistration {
    void startProcess() {
        fillForm();
        if (validateData()) {
            submitForm();
            adminApproval();
            confirmRegistration();
        } else {
            rejectForm();
        }
        endProcess();
    }

    void fillForm() { }
    boolean validateData() { return true; }
    void submitForm() { }
    void adminApproval() { }
    void confirmRegistration() { }
    void rejectForm() { }
    void endProcess() { }
}

public class ActivityDiagramDemo {
    public static void main(String[] args) {
        StudentRegistration registration = new StudentRegistration();
        registration.startProcess();
    }
}
