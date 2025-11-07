class CircularLinkedList {
    Node tail;

    class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
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
            System.out.println("List is empty");
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
            System.out.println("List is empty");
            return;
        }
        Node current = tail.next;
        if (current == tail) {
            tail = null;
            return;
        }
        while (current.next != tail)
            current = current.next;
        current.next = tail.next;
        tail = current;
    }

    public void display() {
        if (tail == null) {
            System.out.println("List is empty");
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
        CircularLinkedList list = new CircularLinkedList();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.display();

        list.deleteAtBeginning();
        list.display();

        list.insertAtEnd(40);
        list.display();
    }
}
