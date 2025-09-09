import java.util.*;

final class SpaceStationRegistry {
    private static final List<SpaceCrew> crewList = new ArrayList<>();

    public static void registerCrew(SpaceCrew crew) {
        if (crewList.size() < SpaceCrew.MAX_CREW_CAPACITY) {
            crewList.add(crew);
        } else {
            System.out.println("Station at full capacity!");
        }
    }

    public static void displayAllCrew() {
        for (SpaceCrew c : crewList) {
            System.out.println(c.getCrewIdentification() + " | Rank: " + c.currentRank + " | Skill: " + c.skillLevel);
        }
    }

    public static int getTotalMissions() {
        int total = 0;
        for (SpaceCrew c : crewList) {
            total += c.missionCount;
        }
        return total;
    }

    public static void handleEmergency() {
        boolean pilotReady = false, engineerReady = false, scienceReady = false;
        for (SpaceCrew c : crewList) {
            if (c instanceof PilotCrew) pilotReady = true;
            if (c instanceof EngineerCrew) engineerReady = true;
            if (c instanceof ScienceCrew) scienceReady = true;
        }
        if (pilotReady && engineerReady && scienceReady) {
            System.out.println("Emergency handled successfully. Station stabilized.");
        } else {
            System.out.println("Emergency response failed. Missing critical crew types!");
        }
    }
}

abstract class SpaceCrew {
    public final String crewId;
    public final String homeplanet;
    public final CrewRank initialRank;
    protected CrewRank currentRank;
    protected int skillLevel;
    protected int missionCount;
    protected int spaceHours;
    public static final String STATION_NAME = "Stellar Odyssey";
    public static final int MAX_CREW_CAPACITY = 50;

    public SpaceCrew(String homeplanet, CrewRank initialRank) {
        this("C" + UUID.randomUUID(), homeplanet, initialRank, initialRank, 1, 0, 0);
    }

    public SpaceCrew(String crewId, String homeplanet, CrewRank initialRank, CrewRank currentRank, int skillLevel, int missionCount, int spaceHours) {
        this.crewId = crewId;
        this.homeplanet = homeplanet;
        this.initialRank = initialRank;
        this.currentRank = currentRank;
        this.skillLevel = skillLevel;
        this.missionCount = missionCount;
        this.spaceHours = spaceHours;
    }

    public SpaceCrew(String homeplanet) {
        this(homeplanet, CrewRank.CADET);
    }

    public SpaceCrew(String homeplanet, CrewRank rank, int missions, int skills) {
        this("C" + UUID.randomUUID(), homeplanet, rank, rank, skills, missions, missions * 10);
    }

    public final String getCrewIdentification() {
        return "ID: " + crewId + " | Planet: " + homeplanet + " | Station: " + STATION_NAME;
    }

    public final boolean canBePromoted() {
        return currentRank.level < CrewRank.ADMIRAL.level && skillLevel > 5;
    }

    public final int calculateSpaceExperience() {
        return (missionCount * 10) + spaceHours + (skillLevel * 5);
    }
}

final class PilotCrew extends SpaceCrew {
    private final String flightCertification;

    public PilotCrew(String homeplanet, CrewRank rank, String certification) {
        super(homeplanet, rank, 0, 3);
        this.flightCertification = certification;
    }

    public String getCertification() {
        return flightCertification;
    }
}

final class ScienceCrew extends SpaceCrew {
    private final String specialization;

    public ScienceCrew(String homeplanet, CrewRank rank, String specialization) {
        super(homeplanet, rank, 2, 4);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
}

final class EngineerCrew extends SpaceCrew {
    private final String engineeringType;

    public EngineerCrew(String homeplanet, CrewRank rank, String type) {
        super(homeplanet, rank, 5, 6);
        this.engineeringType = type;
    }

    public String getEngineeringType() {
        return engineeringType;
    }
}

final enum CrewRank {
    CADET(1), OFFICER(2), COMMANDER(3), CAPTAIN(4), ADMIRAL(5);

    public final int level;

    CrewRank(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

public class SpaceStationCrewManagement {
    public static void main(String[] args) {
        SpaceCrew p1 = new PilotCrew("Earth", CrewRank.OFFICER, "Flight-A1");
        SpaceCrew s1 = new ScienceCrew("Mars", CrewRank.COMMANDER, "Astrobiology");
        SpaceCrew e1 = new EngineerCrew("Jupiter", CrewRank.CAPTAIN, "Warp Drive");

        SpaceStationRegistry.registerCrew(p1);
        SpaceStationRegistry.registerCrew(s1);
        SpaceStationRegistry.registerCrew(e1);

        SpaceStationRegistry.displayAllCrew();
        System.out.println("Total Missions: " + SpaceStationRegistry.getTotalMissions());

        System.out.println(p1.getCrewIdentification());
        System.out.println("Can be promoted: " + p1.canBePromoted());
        System.out.println("Experience Score: " + p1.calculateSpaceExperience());

        SpaceStationRegistry.handleEmergency();
    }
}
