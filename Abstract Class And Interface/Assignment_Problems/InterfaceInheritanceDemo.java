interface Animal {
    void eat();
}

interface Pet extends Animal {
    void play();
}

class Dog implements Pet {
    public void eat() { System.out.println("Dog is eating."); }
    public void play() { System.out.println("Dog is playing."); }
}

public class InterfaceInheritanceDemo {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.play();
    }
}
