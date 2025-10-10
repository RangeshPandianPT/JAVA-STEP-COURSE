class ContactInfo implements Cloneable {
    private String email;
    private String phone;

    public ContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Email: " + email + ", Phone: " + phone;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Student implements Cloneable {
    private String id;
    private String name;
    private ContactInfo contact;

    public Student(String id, String name, ContactInfo contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    // Shallow copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Deep copy
    protected Student deepClone() throws CloneNotSupportedException {
        Student cloned = (Student) super.clone();
        cloned.contact = (ContactInfo) contact.clone();
        return cloned;
    }

    public ContactInfo getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", " + contact;
    }
}

public class Registration {
    public static void main(String[] args) throws CloneNotSupportedException {
        ContactInfo contact = new ContactInfo("student@example.com", "9876543210");
        Student s1 = new Student("S101", "Rahul", contact);

        Student shallowCopy = (Student) s1.clone();
        Student deepCopy = s1.deepClone();

        System.out.println("Before modification:");
        System.out.println("Original: " + s1);
        System.out.println("Shallow Copy: " + shallowCopy);
        System.out.println("Deep Copy: " + deepCopy);

        shallowCopy.getContact().setEmail("updated@domain.com");

        System.out.println("\nAfter modifying shallow copy’s contact:");
        System.out.println("Original: " + s1);
        System.out.println("Shallow Copy: " + shallowCopy);
        System.out.println("Deep Copy: " + deepCopy);
    }
}
