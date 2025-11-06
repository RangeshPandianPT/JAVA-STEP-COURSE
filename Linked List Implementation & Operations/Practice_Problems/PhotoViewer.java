class PhotoViewer {
    class Node {
        String photo;
        Node next, prev;
        Node(String p) {
            photo = p;
        }
    }

    Node head, tail, current;

    public void addPhoto(String p) {
        Node newNode = new Node(p);
        if (head == null) {
            head = tail = current = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void nextPhoto() {
        if (current == null) {
            System.out.println("No photos available.");
        } else if (current.next == null) {
            System.out.println("You are at the last photo: " + current.photo);
        } else {
            current = current.next;
            System.out.println("Next → " + current.photo);
        }
    }

    public void prevPhoto() {
        if (current == null) {
            System.out.println("No photos available.");
        } else if (current.prev == null) {
            System.out.println("You are at the first photo: " + current.photo);
        } else {
            current = current.prev;
            System.out.println("Prev ← " + current.photo);
        }
    }

    public static void main(String[] args) {
        PhotoViewer viewer = new PhotoViewer();

        viewer.addPhoto("Pic1");
        viewer.addPhoto("Pic2");
        viewer.addPhoto("Pic3");

        System.out.println("Current: " + viewer.current.photo);
        viewer.nextPhoto();
        viewer.nextPhoto();
        viewer.prevPhoto();
    }
}
