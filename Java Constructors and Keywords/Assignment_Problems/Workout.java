class Workout {
    String activityName;
    int durationInMinutes;
    int caloriesBurned;

    Workout() {
        this("Walking", 30, 100);
    }

    Workout(String activityName) {
        this(activityName, 30, 30 * 5);
    }

    Workout(String activityName, int durationInMinutes) {
        this(activityName, durationInMinutes, durationInMinutes * 5);
    }

    Workout(String activityName, int durationInMinutes, int caloriesBurned) {
        this.activityName = activityName;
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = caloriesBurned;
    }

    void displayWorkout() {
        System.out.println("Activity: " + activityName);
        System.out.println("Duration: " + durationInMinutes + " mins");
        System.out.println("Calories Burned: " + caloriesBurned);
    }
}

public class FitnessTracker {
    public static void main(String[] args) {
        Workout w1 = new Workout();
        Workout w2 = new Workout("Running");
        Workout w3 = new Workout("Cycling", 45);

        w1.displayWorkout();
        w2.displayWorkout();
        w3.displayWorkout();
    }
}
