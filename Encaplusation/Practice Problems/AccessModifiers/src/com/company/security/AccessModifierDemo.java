package com.company.security;

public class AccessModifierDemo {

    // ================== FIELDS ==================
    private int privateField;        // Only this class
    String defaultField;             // Same package
    protected double protectedField; // Same package + subclasses
    public boolean publicField;      // Everywhere

    // ================== METHODS ==================
    private void privateMethod() {
        System.out.println("Private method called");
    }

    void defaultMethod() {
        System.out.println("Default method called");
    }

    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    // ================== CONSTRUCTOR ==================
    public AccessModifierDemo(int privateField, String defaultField, double protectedField, boolean publicField) {
        this.privateField = privateField;
        this.defaultField = defaultField;
        this.protectedField = protectedField;
        this.publicField = publicField;
    }

    // ================== INTERNAL ACCESS TEST ==================
    public void testInternalAccess() {
        // Accessing all fields
        System.out.println("Private Field: " + privateField);
        System.out.println("Default Field: " + defaultField);
        System.out.println("Protected Field: " + protectedField);
        System.out.println("Public Field: " + publicField);

        // Calling all methods
        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();
    }

    // ================== MAIN ==================
    public static void main(String[] args) {
        AccessModifierDemo demo = new AccessModifierDemo(10, "Hello", 99.9, true);

        // Direct field access
        // System.out.println(demo.privateField);   // ❌ Compile error (private)
        System.out.println(demo.defaultField);     // ✅ Accessible (same package)
        System.out.println(demo.protectedField);   // ✅ Accessible (same package)
        System.out.println(demo.publicField);      // ✅ Accessible everywhere

        // Method calls
        // demo.privateMethod();   // ❌ Compile error (private)
        demo.defaultMethod();      // ✅ Accessible (same package)
        demo.protectedMethod();    // ✅ Accessible (same package)
        demo.publicMethod();       // ✅ Accessible everywhere

        // Test internal accessibility
        demo.testInternalAccess();

        // Same package test
        SamePackageTest.testAccess();
    }
}

// ================== SECOND CLASS ==================
class SamePackageTest {
    public static void testAccess() {
        AccessModifierDemo demo = new AccessModifierDemo(20, "World", 55.5, false);

        // System.out.println(demo.privateField);   // ❌ Compile error (private)
        System.out.println(demo.defaultField);     // ✅ Accessible (same package)
        System.out.println(demo.protectedField);   // ✅ Accessible (same package)
        System.out.println(demo.publicField);      // ✅ Accessible everywhere

        // demo.privateMethod();   // ❌ Compile error (private)
        demo.defaultMethod();      // ✅ Accessible (same package)
        demo.protectedMethod();    // ✅ Accessible (same package)
        demo.publicMethod();       // ✅ Accessible everywhere
    }
}
