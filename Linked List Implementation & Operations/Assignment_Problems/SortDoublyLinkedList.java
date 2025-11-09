class SortDoublyLinkedList {
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
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.prev = temp;
    }
    public void bubbleSort() {
        if (head == null)
            return;

        boolean swapped;
        Node temp;
        do {
            swapped = false;
            temp = head;
            while (temp.next != null) {
                if (temp.data > temp.next.data) {
                    int t = temp.data;
                    temp.data = temp.next.data;
                    temp.next.data = t;
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null)
                System.out.print(" ⇄ ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SortDoublyLinkedList list = new SortDoublyLinkedList();

        list.insert(40);
        list.insert(10);
        list.insert(30);
        list.insert(20);

        System.out.println("Original List:");
        list.display();

        list.bubbleSort();

        System.out.println("\nSorted List:");
        list.display();
    }
}
