class TaskManager {
    Node tail;

    class Node {
        String taskName;
        int time;
        Node next;

        Node(String name, int t) {
            taskName = name;
            time = t;
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
            System.out.println("No tasks to execute");
            return;
        }

        Node current = tail.next;
        System.out.print("Execution order → ");

        while (tail != null) {
            System.out.print(current.taskName + " ");
            current.time -= 1; 

            if (current.time <= 0) {
                removeTask(current.taskName);
                if (tail == null)
                    break;
                current = tail.next;
            } else {
                current = current.next;
            }
        }
        System.out.println("\nAll tasks completed.");
    }
    private void removeTask(String name) {
        if (tail == null)
            return;

        Node current = tail.next, prev = tail;

        do {
            if (current.taskName.equals(name)) {
                if (current == tail && current == tail.next) {
                    tail = null; // only one node
                } else {
                    prev.next = current.next;
                    if (current == tail)
                        tail = prev;
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != tail.next);
    }
    public void displayTasks() {
        if (tail == null) {
            System.out.println("No tasks in the list");
            return;
        }
        Node temp = tail.next;
        System.out.print("Tasks = [");
        do {
            System.out.print(temp.taskName + "(" + temp.time + "s)");
            temp = temp.next;
            if (temp != tail.next)
                System.out.print(" → ");
        } while (temp != tail.next);
        System.out.println(" → back to " + tail.next.taskName + "]");
    }

    public static void main(String[] args) {
        TaskManager tm = new TaskManager();
        tm.addTask("T1", 3);
        tm.addTask("T2", 2);
        tm.addTask("T3", 4);

        tm.displayTasks();
        tm.executeTasks();
    }
}
