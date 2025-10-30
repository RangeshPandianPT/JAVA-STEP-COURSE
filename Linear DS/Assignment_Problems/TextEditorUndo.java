import java.util.*;

public class TextEditorUndo {
    Stack<String> actions = new Stack<>();

    public void type(String word) {
        actions.push(word);
        System.out.println("'" + word + "' added");
    }

    public void undo() {
        if (!actions.isEmpty()) {
            String removed = actions.pop();
            System.out.println("Undo performed, removed: '" + removed + "'");
        } else {
            System.out.println("No actions to undo");
        }
    }

    public void showContent() {
        if (actions.isEmpty()) {
            System.out.println("Editor is empty");
        } else {
            System.out.print("Current text: ");
            for (String word : actions) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditorUndo editor = new TextEditorUndo();

        editor.type("Hello");
        editor.type("World");
        editor.showContent();

        editor.undo();
        editor.showContent();

        editor.type("Java");
        editor.showContent();
    }
}
