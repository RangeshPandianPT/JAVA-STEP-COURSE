class SinglyLinkedList {
    Node head;

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
    public void insertAtPosition(int data, int position) {
        Node newNode = new Node(data);

        if (position < 1) {
            System.out.println("Invalid position!");
            return;
        }

        if (position == 1) { // insert at head
            newNode.next = head;
            head = newNode;
            return;
        }

        Node temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position greater than list size!");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
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
        SinglyLinkedList list = new SinglyLinkedList();

        list.insertAtPosition(10, 1);
        list.insertAtPosition(20, 2);
        list.insertAtPosition(30, 3);
        list.insertAtPosition(40, 4);

        System.out.println("Original List:");
        list.display();

        System.out.println("After inserting 50 at position 3:");
        list.insertAtPosition(50, 3);
        list.display();
    }
}
