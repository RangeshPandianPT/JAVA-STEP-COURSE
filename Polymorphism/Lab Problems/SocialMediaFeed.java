import java.time.LocalDateTime;

class Post {
    String author;
    String content;
    LocalDateTime time;

    Post(String author, String content) {
        this.author = author;
        this.content = content;
        this.time = LocalDateTime.now();
    }

    void display() {
        System.out.println(author + " posted at " + time + ": " + content);
    }
}

class InstagramPost extends Post {
    int likes;
    String hashtags;

    InstagramPost(String author, String content, int likes, String hashtags) {
        super(author, content);
        this.likes = likes;
        this.hashtags = hashtags;
    }

    @Override
    void display() {
        System.out.println(" Instagram Post");
        System.out.println(author + ": " + content);
        System.out.println("Likes: " + likes + " " + hashtags);
        System.out.println("Posted at: " + time);
        System.out.println();
    }
}

class TwitterPost extends Post {
    int retweets;

    TwitterPost(String author, String content, int retweets) {
        super(author, content);
        this.retweets = retweets;
    }

    @Override
    void display() {
        System.out.println(" Twitter Post");
        System.out.println(author + ": " + content + " (" + content.length() + " chars)");
        System.out.println("Retweets: " + retweets);
        System.out.println("Posted at: " + time);
        System.out.println();
    }
}

class LinkedInPost extends Post {
    int connections;

    LinkedInPost(String author, String content, int connections) {
        super(author, content);
        this.connections = connections;
    }

    @Override
    void display() {
        System.out.println(" LinkedIn Post");
        System.out.println(author + " (Connections: " + connections + ")");
        System.out.println("Professional Update: " + content);
        System.out.println("Posted at: " + time);
        System.out.println();
    }
}

public class SocialMediaFeed {
    public static void main(String[] args) {
        Post insta = new InstagramPost("Alice", "Enjoying the beach!", 120, "#sunset #travel");
        Post tweet = new TwitterPost("Bob", "Java is awesome!", 45);
        Post linkedin = new LinkedInPost("Charlie", "Excited to start my new job at XYZ Corp.", 500);

        insta.display();
        tweet.display();
        linkedin.display();
    }
}
