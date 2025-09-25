class Content {
    String title;
    String genre;

    Content(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    void play() {
        System.out.println("Playing: " + title + " [" + genre + "]");
    }
}

class Movie extends Content {
    double rating;
    int duration; 
    boolean subtitles;

    Movie(String title, String genre, double rating, int duration, boolean subtitles) {
        super(title, genre);
        this.rating = rating;
        this.duration = duration;
        this.subtitles = subtitles;
    }

    void showMovieDetails() {
        System.out.println("Movie: " + title + " | Rating: " + rating + " | Duration: " + duration + " mins | Subtitles: " + (subtitles ? "Available" : "Not Available"));
    }
}

class TVSeries extends Content {
    int seasons;
    int episodes;

    TVSeries(String title, String genre, int seasons, int episodes) {
        super(title, genre);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    void suggestNextEpisode(int currentEpisode) {
        if (currentEpisode < episodes) {
            System.out.println("Next episode of " + title + " is Episode " + (currentEpisode + 1));
        } else {
            System.out.println("You’ve finished all episodes of " + title + "!");
        }
    }
}

class Documentary extends Content {
    String[] tags;

    Documentary(String title, String genre, String[] tags) {
        super(title, genre);
        this.tags = tags;
    }

    void showEducationalTags() {
        System.out.print("Documentary: " + title + " | Tags: ");
        for (String tag : tags) {
            System.out.print(tag + " ");
        }
        System.out.println();
    }
}

public class StreamingPlatform {
    public static void main(String[] args) {
        Content[] watchlist = {
            new Movie("Inception", "Sci-Fi", 8.8, 148, true),
            new TVSeries("Stranger Things", "Mystery", 4, 34),
            new Documentary("Planet Earth", "Nature", new String[]{"Wildlife", "Environment"})
        };

        for (Content c : watchlist) {
            c.play();

            if (c instanceof Movie) {
                Movie m = (Movie) c;  
                m.showMovieDetails();
            } else if (c instanceof TVSeries) {
                TVSeries tv = (TVSeries) c;  
                tv.suggestNextEpisode(5);
            } else if (c instanceof Documentary) {
                Documentary d = (Documentary) c;  
                d.showEducationalTags();
            }

            System.out.println();
        }
    }
}
