class HospitalSystem {
    void manageAppointments(Admin admin, Doctor doctor, Patient patient) {
        admin.scheduleAppointment();
        doctor.checkAppointments();
        patient.viewAppointment();
    }

    void updateRecords(Doctor doctor) {
        doctor.updateMedicalRecord();
    }

    void generateBills(Admin admin, Patient patient) {
        admin.createBill();
        patient.viewBill();
    }
}

class Admin {
    void scheduleAppointment() { }
    void createBill() { }
}

class Doctor {
    void checkAppointments() { }
    void updateMedicalRecord() { }
}

class Patient {
    void viewAppointment() { }
    void viewBill() { }
}

public class UseCaseDiagramDemo {
    public static void main(String[] args) {
        HospitalSystem system = new HospitalSystem();
        Admin admin = new Admin();
        Doctor doctor = new Doctor();
        Patient patient = new Patient();

        system.manageAppointments(admin, doctor, patient);
        system.updateRecords(doctor);
        system.generateBills(admin, patient);
    }
}
