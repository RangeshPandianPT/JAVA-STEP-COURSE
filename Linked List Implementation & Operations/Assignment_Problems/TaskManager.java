class TaskManager {
    Node tail;

    class Node {
        String taskName;
        int time;
        Node next;

        Node(String name, int t) {
            taskName = name;
            time = t;
            next = null;
        }
    }
    public void addTask(String name, int time) {
        Node newNode = new Node(name, time);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }
    public void executeTasks() {
        if (tail == null) {
            System.out.println("No tasks to execute.");
            return;
        }

        Node current = tail.next;
        System.out.print("Execution order → ");
        while (tail != null) {
            System.out.print(current.taskName + " ");
            current.time--;

            if (current.time == 0) {
                System.out.print("(Completed) ");
                deleteTask(current);
                if (tail == null)
                    break;
                current = tail.next;
            } else {
                current = current.next;
            }
        }
        System.out.println("\nAll tasks completed!");
    }
    private void deleteTask(Node node) {
        if (tail == null)
            return;

        Node temp = tail.next;
        Node prev = tail;

        if (tail == tail.next && tail == node) {
            tail = null;
            return;
        }
        do {
            if (temp == node) {
                prev.next = temp.next;
                if (temp == tail)
                    tail = prev;
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != tail.next);
    }

    public static void main(String[] args) {
        TaskManager tm = new TaskManager();

        tm.addTask("T1", 3);
        tm.addTask("T2", 2);
        tm.addTask("T3", 4);

        tm.executeTasks();
    }
}
