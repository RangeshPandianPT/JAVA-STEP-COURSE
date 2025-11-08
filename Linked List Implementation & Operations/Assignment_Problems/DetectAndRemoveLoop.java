class DetectAndRemoveLoop {
    Node head;

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }
    public void createLoop(int position) {
        if (position == 0) return;

        Node temp = head;
        Node loopNode = null;
        int count = 1;
        while (temp.next != null) {
            if (count == position)
                loopNode = temp;
            temp = temp.next;
            count++;
        }
        temp.next = loopNode;
    }
    public void detectAndRemoveLoop() {
        Node slow = head, fast = head;
        boolean loopExists = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) { 
                loopExists = true;
                removeLoop(slow);
                System.out.println("Loop detected and removed.");
                return;
            }
        }

        if (!loopExists)
            System.out.println("No loop found.");
    }
    private void removeLoop(Node loopNode) {
        Node ptr1 = head;
        Node ptr2 = loopNode;

        while (ptr1.next != ptr2.next) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        ptr2.next = null; // break the loop
    }
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null)
                System.out.print(" → ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DetectAndRemoveLoop list = new DetectAndRemoveLoop();

        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);
        list.insert(50);

        System.out.println("Creating a loop (50 → 30)...");
        list.createLoop(3);

        list.detectAndRemoveLoop();
        System.out.println("List after loop removal:");
        list.display();
    }
}
