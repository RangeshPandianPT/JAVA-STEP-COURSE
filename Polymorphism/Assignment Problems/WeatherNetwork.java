import java.util.*;
interface WeatherSensor {
    String getId();
    void readData();
}

class TemperatureSensor implements WeatherSensor {
    private String id;
    private double calibrationOffset;
    private double minRange, maxRange;

    TemperatureSensor(String id, double calibrationOffset, double minRange, double maxRange) {
        this.id = id;
        this.calibrationOffset = calibrationOffset;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public String getId() { return id; }

    public void readData() {
        double value = minRange + Math.random() * (maxRange - minRange);
        value += calibrationOffset;
        System.out.println("TemperatureSensor " + id + ": " + value + "°C");
    }
}
class HumiditySensor implements WeatherSensor {
    private String id;
    private double moistureLevel;

    HumiditySensor(String id, double moistureLevel) {
        this.id = id;
        this.moistureLevel = moistureLevel;
    }

    public String getId() { return id; }

    public void readData() {
        double humidity = 40 + Math.random() * 60;
        double dewPoint = humidity * 0.02 + moistureLevel;
        System.out.println("HumiditySensor " + id + ": " + humidity + "%, Dew Point: " + dewPoint + "°C");
    }
}
class WindSensor implements WeatherSensor {
    private String id;

    WindSensor(String id) {
        this.id = id;
    }

    public String getId() { return id; }

    public void readData() {
        double speed = 5 + Math.random() * 50;
        String[] directions = {"N", "S", "E", "W", "NE", "NW", "SE", "SW"};
        String direction = directions[new Random().nextInt(directions.length)];
        System.out.println("WindSensor " + id + ": " + speed + " km/h, Direction: " + direction);
    }
}
class RainSensor implements WeatherSensor {
    private String id;

    RainSensor(String id) {
        this.id = id;
    }

    public String getId() { return id; }

    public void readData() {
        double accumulation = Math.random() * 20;
        double intensity = Math.random() * 100;
        System.out.println("RainSensor " + id + ": Accumulation " + accumulation + "mm, Intensity: " + intensity + "%");
    }
}

class SensorFactory {
    public static WeatherSensor createSensor(String type, String id) {
        switch (type.toLowerCase()) {
            case "temperature":
                return new TemperatureSensor(id, 0.5, -10, 50);
            case "humidity":
                return new HumiditySensor(id, 2.0);
            case "wind":
                return new WindSensor(id);
            case "rain":
                return new RainSensor(id);
            default:
                throw new IllegalArgumentException("Unknown sensor type: " + type);
        }
    }
}

public class WeatherNetwork {
    public static void main(String[] args) {
        List<WeatherSensor> sensors = new ArrayList<>();
        sensors.add(SensorFactory.createSensor("temperature", "T-101"));
        sensors.add(SensorFactory.createSensor("humidity", "H-202"));
        sensors.add(SensorFactory.createSensor("wind", "W-303"));
        sensors.add(SensorFactory.createSensor("rain", "R-404"));

        for (WeatherSensor sensor : sensors) {
            sensor.readData();
        }
    }
}
