class Weather {
    String condition;

    Weather(String condition) {
        this.condition = condition;
        System.out.println("Weather created: " + condition);
    }

    void show() {
        System.out.println("General weather: " + condition);
    }
}

class Storm extends Weather {
    double windSpeed;

    Storm(String condition, double windSpeed) {
        super(condition);
        this.windSpeed = windSpeed;
        System.out.println("Storm created with wind speed: " + windSpeed + " km/h");
    }

    @Override
    void show() {
        System.out.println("Stormy weather: " + condition + ", wind speed " + windSpeed + " km/h");
    }
}

class Thunderstorm extends Storm {
    boolean lightning;

    Thunderstorm(String condition, double windSpeed, boolean lightning) {
        super(condition, windSpeed);
        this.lightning = lightning;
        System.out.println("Thunderstorm created with lightning: " + lightning);
    }

    @Override
    void show() {
        System.out.println("Thunderstorm: " + condition + ", wind speed " + windSpeed +
                           " km/h, lightning: " + lightning);
    }
}

class Sunshine extends Weather {
    int uvIndex;

    Sunshine(String condition, int uvIndex) {
        super(condition);
        this.uvIndex = uvIndex;
        System.out.println("Sunshine created with UV index: " + uvIndex);
    }

    @Override
    void show() {
        System.out.println("Sunny weather: " + condition + ", UV index " + uvIndex);
    }
}

public class WeatherDemo {
    public static void main(String[] args) {
        Weather[] forecasts = new Weather[3];
        forecasts[0] = new Thunderstorm("Heavy Rain", 80, true);
        forecasts[1] = new Storm("Tropical Storm", 60);
        forecasts[2] = new Sunshine("Clear Sky", 7);

        System.out.println("\n--- Forecast Report ---");
        for (Weather w : forecasts) {
            w.show();
        }
    }
}
