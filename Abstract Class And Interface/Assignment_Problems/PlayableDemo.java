interface Playable {
    void play();
    void pause();
}

class MusicPlayer implements Playable {
    public void play() { System.out.println("Playing music..."); }
    public void pause() { System.out.println("Music paused."); }
}

class VideoPlayer implements Playable {
    public void play() { System.out.println("Playing video..."); }
    public void pause() { System.out.println("Video paused."); }
}

public class PlayableDemo {
    public static void main(String[] args) {
        Playable ref;

        ref = new MusicPlayer();
        ref.play();
        ref.pause();

        ref = new VideoPlayer();
        ref.play();
        ref.pause();
    }
}
