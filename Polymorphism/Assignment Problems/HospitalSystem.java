abstract class MedicalStaff {
    String name;
    int staffId;

    MedicalStaff(String name, int staffId) {
        this.name = name;
        this.staffId = staffId;
    }

    void scheduleShift(String shift) {
        System.out.println(name + " (ID: " + staffId + ") scheduled for " + shift + " shift");
    }

    void accessIdCard() {
        System.out.println(name + " (ID: " + staffId + ") accessed hospital facilities");
    }

    void processPayroll(double amount) {
        System.out.println(name + " (ID: " + staffId + ") received salary: ₹" + amount);
    }

    abstract void performDuties();
}

class Doctor extends MedicalStaff {
    Doctor(String name, int staffId) {
        super(name, staffId);
    }

    @Override
    void performDuties() {
        System.out.println(name + " is diagnosing patients, prescribing medicine, and performing surgeries.");
    }
}

class Nurse extends MedicalStaff {
    Nurse(String name, int staffId) {
        super(name, staffId);
    }

    @Override
    void performDuties() {
        System.out.println(name + " is administering medicine, monitoring patients, and assisting in procedures.");
    }
}

class Technician extends MedicalStaff {
    Technician(String name, int staffId) {
        super(name, staffId);
    }

    @Override
    void performDuties() {
        System.out.println(name + " is operating equipment, running tests, and maintaining instruments.");
    }
}

class Administrator extends MedicalStaff {
    Administrator(String name, int staffId) {
        super(name, staffId);
    }

    @Override
    void performDuties() {
        System.out.println(name + " is scheduling appointments and managing hospital records.");
    }
}

public class HospitalSystem {
    public static void main(String[] args) {
        MedicalStaff staff;

        staff = new Doctor("Dr. Kumar", 101);
        staff.scheduleShift("Morning");
        staff.accessIdCard();
        staff.processPayroll(120000);
        staff.performDuties();

        staff = new Nurse("Sister Priya", 202);
        staff.scheduleShift("Night");
        staff.accessIdCard();
        staff.processPayroll(60000);
        staff.performDuties();

        staff = new Technician("Ravi", 303);
        staff.scheduleShift("Evening");
        staff.accessIdCard();
        staff.processPayroll(50000);
        staff.performDuties();

        staff = new Administrator("Meena", 404);
        staff.scheduleShift("Day");
        staff.accessIdCard();
        staff.processPayroll(80000);
        staff.performDuties();
    }
}
