class DoublyLinkedListSort {
    Node head;

    class Node {
        int data;
        Node prev, next;
        Node(int d) {
            data = d;
            prev = next = null;
        }
    }

    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newNode;
        newNode.prev = temp;
    }

    public void bubbleSort() {
        if (head == null)
            return;
        boolean swapped;
        Node current;
        do {
            swapped = false;
            current = head;
            while (current.next != null) {
                if (current.data > current.next.data) {
                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    public void display() {
        Node temp = head;
        System.out.print("List: ");
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null)
                System.out.print(" ⇄ ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedListSort list = new DoublyLinkedListSort();
        list.insert(40);
        list.insert(10);
        list.insert(30);
        list.insert(20);

        System.out.println("Original List:");
        list.display();

        list.bubbleSort();

        System.out.println("Sorted List:");
        list.display();
    }
}
