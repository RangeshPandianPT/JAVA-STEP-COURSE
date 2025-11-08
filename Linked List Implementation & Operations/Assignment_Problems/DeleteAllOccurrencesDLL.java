class DeleteAllOccurrencesDLL {
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
    public void deleteAll(int value) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == value) {
                if (temp.prev != null)
                    temp.prev.next = temp.next;
                else
                    head = temp.next; 

                if (temp.next != null)
                    temp.next.prev = temp.prev;
            }
            temp = temp.next;
        }
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
        DeleteAllOccurrencesDLL list = new DeleteAllOccurrencesDLL();

        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(20);
        list.insert(40);

        System.out.println("Original List:");
        list.display();

        int deleteValue = 20;
        System.out.println("\nDeleting all occurrences of " + deleteValue + "...");
        list.deleteAll(deleteValue);

        System.out.println("\nUpdated List:");
        list.display();
    }
}
