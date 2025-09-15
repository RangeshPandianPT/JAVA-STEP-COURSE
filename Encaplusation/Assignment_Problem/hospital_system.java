package hospital;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HospitalSystem {
    public static final String HOSPITAL_NAME = "City General Hospital";
    public static final int MAX_ROOM_NUMBER = 9999;
    public static final Set<String> RESTRICTED_BLOOD_TYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("AB+","AB-")));

    private final Map<String, Object> patientRegistry = new HashMap<>();

    public boolean admitPatient(Object patient, Object staff) {
        if (patient == null || staff == null) return false;
        if (!(patient instanceof Patient)) return false;
        Patient p = (Patient) patient;
        if (!validateStaffAccess(staff, p)) return false;
        patientRegistry.put(p.getPatientId(), p);
        return true;
    }

    private boolean validateStaffAccess(Object staff, Object patient) {
        if (staff instanceof Doctor) return true;
        if (staff instanceof Nurse) {
            Nurse n = (Nurse) staff;
            return n.canAccessTo((Patient) patient);
        }
        if (staff instanceof Administrator) {
            Administrator a = (Administrator) staff;
            return a.hasPermission("VIEW_PATIENT_BASIC");
        }
        return false;
    }

    String internalPatientCount() {
        return String.valueOf(patientRegistry.size());
    }

    public static final class MedicalRecord implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String recordId;
        private final String patientDNA;
        private final String[] allergies;
        private final String[] medicalHistory;
        private final LocalDate birthDate;
        private final String bloodType;
        private final LocalDate createdAt;

        public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory, LocalDate birthDate, String bloodType) {
            Objects.requireNonNull(recordId);
            Objects.requireNonNull(patientDNA);
            Objects.requireNonNull(allergies);
            Objects.requireNonNull(medicalHistory);
            Objects.requireNonNull(birthDate);
            Objects.requireNonNull(bloodType);
            if (recordId.isBlank()) throw new IllegalArgumentException("invalid record id");
            if (patientDNA.isBlank()) throw new IllegalArgumentException("invalid dna");
            if (!isValidBloodType(bloodType)) throw new IllegalArgumentException("invalid blood type");
            this.recordId = recordId;
            this.patientDNA = patientDNA;
            this.allergies = Arrays.copyOf(allergies, allergies.length);
            this.medicalHistory = Arrays.copyOf(medicalHistory, medicalHistory.length);
            this.birthDate = birthDate;
            this.bloodType = bloodType;
            this.createdAt = LocalDate.now();
        }

        private static boolean isValidBloodType(String bt) {
            return bt.matches("^(A|B|AB|O)[+-]$");
        }

        public String getRecordId() {
            return recordId;
        }

        public String getPatientDNA() {
            return patientDNA;
        }

        public String[] getAllergies() {
            return Arrays.copyOf(allergies, allergies.length);
        }

        public String[] getMedicalHistory() {
            return Arrays.copyOf(medicalHistory, medicalHistory.length);
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public String getBloodType() {
            return bloodType;
        }

        public LocalDate getCreatedAt() {
            return createdAt;
        }

        public final boolean isAllergicTo(String substance) {
            if (substance == null) return false;
            for (String a : allergies) if (a.equalsIgnoreCase(substance)) return true;
            return false;
        }

        @Override
        public String toString() {
            DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
            return "MedicalRecord{" + "recordId='" + recordId + '\'' + ", birthDate=" + birthDate.format(f) + ", bloodType='" + bloodType + '\'' + ", createdAt=" + createdAt.format(f) + '}';
        }
    }

    public static class Patient implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String patientId;
        private final MedicalRecord medicalRecord;
        private String currentName;
        private String emergencyContact;
        private String insuranceInfo;
        private int roomNumber;
        private String attendingPhysician;
        private final LocalDate admittedAt;

        public Patient(String currentName, String emergencyContact, String insuranceInfo, int roomNumber, String attendingPhysician) {
            this(UUID.randomUUID().toString(), null, currentName, emergencyContact, insuranceInfo, roomNumber, attendingPhysician, LocalDate.now());
        }

        public Patient(String patientId, MedicalRecord medicalRecord, String currentName, String emergencyContact, String insuranceInfo, int roomNumber, String attendingPhysician, LocalDate admittedAt) {
            Objects.requireNonNull(patientId);
            if (patientId.isBlank()) throw new IllegalArgumentException("invalid patient id");
            if (roomNumber < 0 || roomNumber > MAX_ROOM_NUMBER) throw new IllegalArgumentException("invalid room number");
            this.patientId = patientId;
            this.medicalRecord = medicalRecord;
            this.currentName = sanitizeName(currentName);
            this.emergencyContact = sanitizeContact(emergencyContact);
            this.insuranceInfo = insuranceInfo;
            this.roomNumber = roomNumber;
            this.attendingPhysician = attendingPhysician;
            this.admittedAt = admittedAt == null ? LocalDate.now() : admittedAt;
        }

        public Patient(MedicalRecord existingRecord, String currentName, int roomNumber, String attendingPhysician) {
            this(existingRecord == null ? UUID.randomUUID().toString() : "TX-" + UUID.randomUUID().toString(), existingRecord, currentName, null, null, roomNumber, attendingPhysician, LocalDate.now());
        }

        private static String sanitizeName(String n) {
            if (n == null) return "Unknown";
            return n.trim();
        }

        private static String sanitizeContact(String c) {
            if (c == null) return "";
            return c.trim();
        }

        public String getPatientId() {
            return patientId;
        }

        public MedicalRecord getMedicalRecord() {
            return medicalRecord;
        }

        public String getCurrentName() {
            return currentName;
        }

        public void setCurrentName(String currentName) {
            if (currentName == null || currentName.isBlank()) throw new IllegalArgumentException("invalid name");
            this.currentName = sanitizeName(currentName);
        }

        public String getEmergencyContact() {
            return emergencyContact;
        }

        public void setEmergencyContact(String emergencyContact) {
            this.emergencyContact = sanitizeContact(emergencyContact);
        }

        public String getInsuranceInfo() {
            return insuranceInfo;
        }

        public void setInsuranceInfo(String insuranceInfo) {
            this.insuranceInfo = insuranceInfo;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(int roomNumber) {
            if (roomNumber < 0 || roomNumber > MAX_ROOM_NUMBER) throw new IllegalArgumentException("invalid room");
            this.roomNumber = roomNumber;
        }

        public String getAttendingPhysician() {
            return attendingPhysician;
        }

        public void setAttendingPhysician(String attendingPhysician) {
            this.attendingPhysician = attendingPhysician;
        }

        String getBasicInfo() {
            StringBuilder sb = new StringBuilder();
            sb.append("id:").append(patientId).append("|name:").append(currentName).append("|room:").append(roomNumber);
            return sb.toString();
        }

        public String getPublicInfo() {
            return "name:" + currentName + " room:" + roomNumber;
        }

        @Override
        public String toString() {
            return "Patient{" + "patientId='" + patientId + '\'' + ", currentName='" + currentName + '\'' + ", roomNumber=" + roomNumber + ", attendingPhysician='" + attendingPhysician + '\'' + ", admittedAt=" + admittedAt + '}';
        }
    }

    public static class Doctor implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String licenseNumber;
        private final String specialty;
        private final Set<String> certifications;

        public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
            Objects.requireNonNull(licenseNumber);
            Objects.requireNonNull(specialty);
            this.licenseNumber = licenseNumber;
            this.specialty = specialty;
            this.certifications = certifications == null ? new HashSet<>() : new HashSet<>(certifications);
        }

        public String getLicenseNumber() {
            return licenseNumber;
        }

        public String getSpecialty() {
            return specialty;
        }

        public Set<String> getCertifications() {
            return Collections.unmodifiableSet(certifications);
        }

        public boolean canAccess(MedicalRecord r) {
            return r != null;
        }
    }

    public static class Nurse implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String nurseId;
        private final String shift;
        private final List<String> qualifications;

        public Nurse(String nurseId, String shift, List<String> qualifications) {
            Objects.requireNonNull(nurseId);
            this.nurseId = nurseId;
            this.shift = shift;
            this.qualifications = qualifications == null ? new ArrayList<>() : new ArrayList<>(qualifications);
        }

        public String getNurseId() {
            return nurseId;
        }

        public String getShift() {
            return shift;
        }

        public List<String> getQualifications() {
            return Collections.unmodifiableList(qualifications);
        }

        public boolean canAccessTo(Patient p) {
            if (p == null) return false;
            return Math.abs(p.getRoomNumber()) >= 0;
        }
    }

    public static class Administrator implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String adminId;
        private final List<String> accessPermissions;

        public Administrator(String adminId, List<String> accessPermissions) {
            Objects.requireNonNull(adminId);
            this.adminId = adminId;
            this.accessPermissions = accessPermissions == null ? new ArrayList<>() : new ArrayList<>(accessPermissions);
        }

        public String getAdminId() {
            return adminId;
        }

        public List<String> getAccessPermissions() {
            return Collections.unmodifiableList(accessPermissions);
        }

        public boolean hasPermission(String p) {
            return accessPermissions.contains(p);
        }
    }
}
