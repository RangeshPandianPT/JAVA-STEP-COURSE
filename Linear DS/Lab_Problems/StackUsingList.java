import java.util.*;

class StackUsingList {
    private List<Integer> stackList = new ArrayList<>();

    public void push(int item) {
        stackList.add(item);
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stackList.remove(stackList.size() - 1);
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stackList.get(stackList.size() - 1);
    }

    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    public void display() {
        System.out.println("Stack: " + stackList);
    }

    public static void main(String[] args) {
        StackUsingList stack = new StackUsingList();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();

        System.out.println("Top element: " + stack.peek());
        System.out.println("Popped element: " + stack.pop());
        stack.display();
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
