class UniversityRegistration {
    void startProcess() {
        fillForm();
        if (validateDetails()) {
            uploadDocuments();
            if (verifyDocuments()) {
                payFees();
                getConfirmation();
            } else {
                rejectApplication();
            }
        } else {
            showError();
        }
        endProcess();
    }

    void fillForm() { }
    boolean validateDetails() { return true; }
    void uploadDocuments() { }
    boolean verifyDocuments() { return true; }
    void payFees() { }
    void getConfirmation() { }
    void rejectApplication() { }
    void showError() { }
    void endProcess() { }
}

public class ActivityDiagramDemo {
    public static void main(String[] args) {
        UniversityRegistration registration = new UniversityRegistration();
        registration.startProcess();
    }
}
