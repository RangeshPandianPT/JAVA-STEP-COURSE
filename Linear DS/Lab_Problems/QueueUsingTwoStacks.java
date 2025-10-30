import java.util.*;

class QueueUsingTwoStacks {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void enqueue(int data) {
        stack1.push(data);
    }

    public int dequeue() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        Stack<Integer> temp = new Stack<>();
        temp.addAll(stack2);
        List<Integer> queueElements = new ArrayList<>();

        for (int i = temp.size() - 1; i >= 0; i--) {
            queueElements.add(temp.get(i));
        }

        for (int i = 0; i < stack1.size(); i++) {
            queueElements.add(stack1.get(i));
        }

        System.out.println("Queue: " + queueElements);
    }

    public static void main(String[] args) {
        QueueUsingTwoStacks queue = new QueueUsingTwoStacks();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.display();

        System.out.println("Dequeued: " + queue.dequeue());
        queue.display();

        queue.enqueue(40);
        queue.display();
    }
}
