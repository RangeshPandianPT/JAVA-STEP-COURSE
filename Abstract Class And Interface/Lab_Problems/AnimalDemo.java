abstract class Animal {
    protected String name;
    protected String habitat;

    Animal(String name, String habitat) {
        this.name = name;
        this.habitat = habitat;
    }

    abstract void eat();
}

interface Soundable {
    void makeSound();
}

class Dog extends Animal implements Soundable {
    private String breed;

    Dog(String name, String habitat, String breed) {
        super(name, habitat);
        this.breed = breed;
    }

    @Override
    void eat() {
        System.out.println(name + " eats dog food and meat.");
    }

    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof Woof!");
    }

    void showDetails() {
        System.out.println("Animal: " + name);
        System.out.println("Breed: " + breed);
        System.out.println("Habitat: " + habitat);
    }
}

public class AnimalDemo {
    public static void main(String[] args) {
        Dog dog = new Dog("Tommy", "Domestic", "Labrador");
        dog.showDetails();
        dog.eat();
        dog.makeSound();
    }
}
