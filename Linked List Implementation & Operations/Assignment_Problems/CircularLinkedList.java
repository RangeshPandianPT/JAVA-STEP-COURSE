class CircularLinkedList {
    Node tail;

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
    }
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }
    public void deleteAtBeginning() {
        if (tail == null) {
            System.out.println("List is empty.");
            return;
        }
        Node head = tail.next;
        if (head == tail) {
            tail = null;
        } else {
            tail.next = head.next;
        }
    }
    public void deleteAtEnd() {
        if (tail == null) {
            System.out.println("List is empty.");
            return;
        }
        Node temp = tail.next;
        if (temp == tail) {
            tail = null;
        } else {
            while (temp.next != tail) {
                temp = temp.next;
            }
            temp.next = tail.next;
            tail = temp;
        }
    }
    public void display() {
        if (tail == null) {
            System.out.println("List is empty.");
            return;
        }
        Node temp = tail.next;
        System.out.print("Circular List = [");
        do {
            System.out.print(temp.data);
            temp = temp.next;
            if (temp != tail.next)
                System.out.print(" → ");
        } while (temp != tail.next);
        System.out.println(" → back to " + tail.next.data + "]");
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();

        cll.insertAtEnd(10);
        cll.insertAtEnd(20);
        cll.insertAtEnd(30);
        cll.display();

        cll.deleteAtBeginning();
        cll.display();

        cll.insertAtEnd(40);
        cll.display();
    }
}
