import java.util.Objects;

class Game {
    String name;
    int players;

    Game(String name, int players) {
        this.name = name;
        this.players = players;
    }

    @Override
    public String toString() {
        return "Game{name='" + name + "', players=" + players + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Game)) return false;
        Game g = (Game) obj;
        return players == g.players && Objects.equals(name, g.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, players);
    }
}

class CardGame extends Game {
    String deckType;

    CardGame(String name, int players, String deckType) {
        super(name, players);
        this.deckType = deckType;
    }

    @Override
    public String toString() {
        return super.toString() + ", CardGame{deckType='" + deckType + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CardGame)) return false;
        if (!super.equals(obj)) return false;
        CardGame cg = (CardGame) obj;
        return Objects.equals(deckType, cg.deckType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deckType);
    }
}

public class GameDemo {
    public static void main(String[] args) {
        Game g1 = new Game("Chess", 2);
        Game g2 = new Game("Chess", 2);
        CardGame c1 = new CardGame("Poker", 4, "Standard 52");
        CardGame c2 = new CardGame("Poker", 4, "Standard 52");
        CardGame c3 = new CardGame("Poker", 4, "Short Deck");

        System.out.println(g1);
        System.out.println(c1);

        System.out.println("g1 equals g2: " + g1.equals(g2));
        System.out.println("c1 equals c2: " + c1.equals(c2));
        System.out.println("c1 equals c3: " + c1.equals(c3));
    }
}
