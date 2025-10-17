class Address {
    String city;
    String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return city + ", " + state;
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    protected Person deepClone() {
        Address newAddress = new Address(address.city, address.state);
        return new Person(name, newAddress);
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', address=" + address + "}";
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Chennai", "Tamil Nadu");
        Person p1 = new Person("Rahul", addr);

        Person p2 = (Person) p1.clone();

        Person p3 = p1.deepClone();

        System.out.println("Before modification:");
        System.out.println("Original: " + p1);
        System.out.println("Shallow Clone: " + p2);
        System.out.println("Deep Clone: " + p3);

        p1.address.city = "Coimbatore";

        System.out.println("\nAfter modification:");
        System.out.println("Original: " + p1);
        System.out.println("Shallow Clone: " + p2);
        System.out.println("Deep Clone: " + p3);
    }
}
