import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

enum PersonalityType {
    BRAVE, CALCULATING, MYSTERIOUS, HUMOROUS, MELANCHOLIC, CURIOUS
}

abstract class StoryCharacter implements Serializable {
    public final String characterId;
    public final String backstory;
    public final PersonalityType corePersonality;
    protected String currentMood;
    protected Map<String, String> relationshipMap;
    protected Set<String> skillSet;
    protected String currentLocation;
    public static final String[] STORY_GENRES = {"Fantasy", "Sci-Fi", "Mystery", "Romance", "Adventure"};
    protected transient AtomicInteger achievementCounter;

    public StoryCharacter(String backstory, PersonalityType corePersonality) {
        this("CH-" + UUID.randomUUID().toString().substring(0, 8), backstory, corePersonality, "neutral", new HashMap<>(), new HashSet<>(), "Unknown");
    }

    public StoryCharacter(String characterId, String backstory, PersonalityType corePersonality, String currentMood, Map<String, String> relationshipMap, Set<String> skillSet, String currentLocation) {
        this.characterId = characterId;
        this.backstory = backstory;
        this.corePersonality = corePersonality;
        this.currentMood = currentMood;
        this.relationshipMap = relationshipMap;
        this.skillSet = skillSet;
        this.currentLocation = currentLocation;
        this.achievementCounter = new AtomicInteger(0);
    }

    public final String getIdentityCard() {
        return characterId + " | " + corePersonality + " | Origin: " + backstory;
    }

    public final void rememberInteraction(String otherId, String note) {
        relationshipMap.put(otherId, note);
    }

    public final int getAchievementCount() {
        return achievementCounter.get();
    }

    public void gainAchievement() {
        achievementCounter.incrementAndGet();
    }

    public abstract String speak();

    public abstract String uniqueAction();

    public final String attemptHackFinalAttribute() {
        return "Attempting to hack final attribute... failed. Finality is real, complains " + characterId;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(achievementCounter.get());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        int ac = ois.readInt();
        this.achievementCounter = new AtomicInteger(ac);
    }
}

class Hero extends StoryCharacter {
    public final String originArtifact;
    public final List<String> destinySigns;

    public Hero(String backstory, PersonalityType corePersonality) {
        this(backstory, corePersonality, "Common Amulet", Arrays.asList("Sign of Dawn"));
    }

    public Hero(String backstory, PersonalityType corePersonality, String originArtifact, List<String> destinySigns) {
        super(backstory, corePersonality);
        this.originArtifact = originArtifact;
        this.destinySigns = new ArrayList<>(destinySigns);
        skillSet.add("Swordsmanship");
        skillSet.add("Leadership");
    }

    public Hero(String fusedId, String backstory, PersonalityType corePersonality, String originArtifact, List<String> destinySigns, Set<String> skills) {
        super(fusedId, backstory, corePersonality, "determined", new HashMap<>(), skills, "Fused Realm");
        this.originArtifact = originArtifact;
        this.destinySigns = new ArrayList<>(destinySigns);
    }

    public String speak() {
        return characterId + ": I will protect the weak.";
    }

    public String uniqueAction() {
        gainAchievement();
        return characterId + " invokes the origin artifact: " + originArtifact;
    }
}

class Villain extends StoryCharacter {
    public final String evilMotivation;
    private int maliceLevel;

    public Villain(String backstory, PersonalityType corePersonality, String evilMotivation) {
        super(backstory, corePersonality);
        this.evilMotivation = evilMotivation;
        this.maliceLevel = 1;
        skillSet.add("Scheming");
    }

    public Villain(String backstory, PersonalityType corePersonality, String evilMotivation, int maliceLevel) {
        super(backstory, corePersonality);
        this.evilMotivation = evilMotivation;
        this.maliceLevel = maliceLevel;
        skillSet.add("Manipulation");
    }

    public String speak() {
        return characterId + ": The world will bend to my will because " + evilMotivation;
    }

    public String uniqueAction() {
        maliceLevel++;
        gainAchievement();
        return characterId + " raises malice to " + maliceLevel;
    }
}

class MysteriousStranger extends StoryCharacter {
    private transient Optional<String> revealedSecret;

    public MysteriousStranger() {
        this("Wandered in from nowhere", PersonalityType.MYSTERIOUS, "HiddenTown");
    }

    public MysteriousStranger(String backstory, PersonalityType corePersonality, String firstLocation) {
        super(backstory, corePersonality);
        this.currentLocation = firstLocation;
        this.revealedSecret = Optional.empty();
        skillSet.add("Observation");
    }

    public String revealSecret() {
        if (revealedSecret.isPresent()) return revealedSecret.get();
        String s = "Secret-" + UUID.randomUUID().toString().substring(0, 6);
        revealedSecret = Optional.of(s);
        gainAchievement();
        return s;
    }

    public String speak() {
        return characterId + ": Words are currency I seldom spend.";
    }

    public String uniqueAction() {
        return characterId + " slips a clue: " + revealSecret();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(revealedSecret.orElse(null));
        oos.writeInt(achievementCounter.get());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        Object maybe = ois.readObject();
        this.revealedSecret = Optional.ofNullable((String) maybe);
        int ac = ois.readInt();
        this.achievementCounter = new AtomicInteger(ac);
    }
}

final class ComicRelief extends StoryCharacter {
    public final String humorStyle;

    public ComicRelief(String backstory, PersonalityType corePersonality, String humorStyle) {
        super(backstory, corePersonality);
        this.humorStyle = humorStyle;
        skillSet.add("Timing");
    }

    public String speak() {
        return characterId + ": Did you hear the one about constructors?";
    }

    public String uniqueAction() {
        gainAchievement();
        return characterId + " cracks a joke in " + humorStyle + " style.";
    }
}

class StoryEngine {
    public static StoryCharacter generateFromPrompt(String prompt) {
        if (prompt.toLowerCase().contains("hero")) return new Hero("Raised by village", PersonalityType.BRAVE);
        if (prompt.toLowerCase().contains("villain")) return new Villain("Raised in shadow", PersonalityType.CALCULATING, "Power");
        if (prompt.toLowerCase().contains("stranger")) return new MysteriousStranger();
        if (prompt.toLowerCase().contains("funny")) return new ComicRelief("Jester lineage", PersonalityType.HUMOROUS, "dry");
        return new Hero("Lost child", PersonalityType.CURIOUS);
    }

    public static StoryCharacter randomCharacter(String genre) {
        Random r = new Random();
        int pick = r.nextInt(4);
        switch (pick) {
            case 0: return new Hero("Born under " + genre, PersonalityType.BRAVE);
            case 1: return new Villain("Shaped by " + genre, PersonalityType.CALCULATING, "Ambition");
            case 2: return new MysteriousStranger();
            default: return new ComicRelief("Stage-broken", PersonalityType.HUMOROUS, "slapstick");
        }
    }

    public static StoryCharacter fuseCharacters(StoryCharacter a, StoryCharacter b) {
        String fusedId = "FUSE-" + a.characterId.substring(3, 7) + b.characterId.substring(3, 7);
        String fusedBackstory = a.backstory + " + " + b.backstory;
        PersonalityType fusedPersonality = a.corePersonality.ordinal() >= b.corePersonality.ordinal() ? a.corePersonality : b.corePersonality;
        Set<String> fusedSkills = new HashSet<>();
        fusedSkills.addAll(a.skillSet);
        fusedSkills.addAll(b.skillSet);
        if (a instanceof Hero || b instanceof Hero) {
            List<String> signs = new ArrayList<>();
            signs.add("MergedSign");
            return new Hero(fusedId, fusedBackstory, fusedPersonality, "Merged Artifact", signs, fusedSkills);
        } else {
            return new ComicRelief(fusedBackstory, fusedPersonality, "meta");
        }
    }

    public static boolean canInteract(StoryCharacter s1, StoryCharacter s2) {
        if (s1 instanceof Hero && s2 instanceof Villain) return true;
        if (s1 instanceof WizardLike(s1) && s2 instanceof MysteriousStranger) return true;
        if (s1 instanceof ComicRelief || s2 instanceof ComicRelief) return true;
        return false;
    }

    private static boolean WizardLike(StoryCharacter s) {
        return s.skillSet.contains("Observation") || s.skillSet.contains("Swordsmanship");
    }

    public static String resolveConflict(StoryCharacter a, StoryCharacter b) {
        if (a instanceof Hero && b instanceof Villain) return a.characterId + " duels and defeats " + b.characterId;
        if (a instanceof Villain && b instanceof Hero) return b.characterId + " barely escapes " + a.characterId;
        if (a instanceof MysteriousStranger || b instanceof MysteriousStranger) return "The conflict turns into an uneasy alliance";
        if (a instanceof ComicRelief || b instanceof ComicRelief) return "They resolve conflict with an unexpected joke";
        return "A quiet resolution occurs";
    }

    public static String createDialogue(StoryCharacter a, StoryCharacter b) {
        return a.speak() + "\n" + b.speak() + "\n" + a.uniqueAction() + " => " + b.uniqueAction();
    }

    public static void saveStory(String filename, List<StoryCharacter> cast) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(cast);
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<StoryCharacter> loadStory(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<StoryCharacter>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Map<String, Map<String, Boolean>> buildRelationshipMatrix(List<StoryCharacter> cast) {
        Map<String, Map<String, Boolean>> matrix = new HashMap<>();
        for (StoryCharacter a : cast) {
            Map<String, Boolean> row = new HashMap<>();
            for (StoryCharacter b : cast) {
                boolean compatible = false;
                if (a instanceof Hero && b instanceof Villain) compatible = true;
                if (a instanceof ComicRelief || b instanceof ComicRelief) compatible = true;
                if (a instanceof MysteriousStranger && b instanceof MysteriousStranger) compatible = false;
                row.put(b.characterId, compatible);
            }
            matrix.put(a.characterId, row);
        }
        return matrix;
    }

    public static String generateStoryArc(List<StoryCharacter> cast, String genre) {
        StringBuilder arc = new StringBuilder();
        arc.append("Genre: ").append(genre).append("\n");
        for (int i = 0; i < Math.min(3, cast.size()); i++) {
            StoryCharacter c = cast.get(i);
            arc.append(c.characterId).append(" - ").append(c.corePersonality).append(" enters from ").append(c.currentLocation).append("\n");
        }
        arc.append("Inciting Incident: A relic appears\n");
        if (cast.stream().anyMatch(ch -> ch instanceof Villain)) arc.append("Villain seizes relic\n");
        if (cast.stream().anyMatch(ch -> ch instanceof Hero)) arc.append("Hero vows to reclaim it\n");
        arc.append("Meta moment: Characters comment on constructors and final limits\n");
        for (StoryCharacter c : cast) {
            arc.append(c.attemptHackFinalAttribute()).append("\n");
        }
        arc.append("Resolution: ").append(resolveConflict(cast.get(0), cast.get(Math.min(1, cast.size() - 1)))).append("\n");
        return arc.toString();
    }
}

public class InteractiveStoryGenerator {
    public static void main(String[] args) {
        List<StoryCharacter> cast = new ArrayList<>();
        StoryCharacter alice = StoryEngine.generateFromPrompt("hero from prophecy");
        StoryCharacter drev = StoryEngine.generateFromPrompt("villain with dark past");
        StoryCharacter x = StoryEngine.generateFromPrompt("mysterious stranger");
        StoryCharacter joker = StoryEngine.generateFromPrompt("funny court jester");
        cast.add(alice);
        cast.add(drev);
        cast.add(x);
        cast.add(joker);

        alice.currentLocation = "Stormhold";
        drev.currentLocation = "Obsidian Keep";
        x.currentLocation = "Crossroads";
        joker.currentLocation = "Market";

        alice.rememberInteraction(drev.characterId, "sworn enemy");
        drev.rememberInteraction(alice.characterId, "target");

        System.out.println("=== Dialogue Sample ===");
        System.out.println(StoryEngine.createDialogue(alice, drev));
        System.out.println();

        System.out.println("=== Fusion Example ===");
        StoryCharacter fused = StoryEngine.fuseCharacters(alice, joker);
        System.out.println("Fused Character: " + fused.getIdentityCard());
        cast.add(fused);

        System.out.println();
        System.out.println("=== Story Arc ===");
        String arc = StoryEngine.generateStoryArc(cast, StoryCharacter.STORY_GENRES[new Random().nextInt(StoryCharacter.STORY_GENRES.length)]);
        System.out.println(arc);

        System.out.println("=== Relationship Matrix ===");
        Map<String, Map<String, Boolean>> matrix = StoryEngine.buildRelationshipMatrix(cast);
        for (String id : matrix.keySet()) {
            System.out.println(id + " -> " + matrix.get(id));
        }

        System.out.println();
        System.out.println("Saving story to story_save.dat");
        StoryEngine.saveStory("story_save.dat", cast);

        System.out.println("Loading story back");
        List<StoryCharacter> loaded = StoryEngine.loadStory("story_save.dat");
        System.out.println("Loaded cast size: " + loaded.size());

        System.out.println();
        System.out.println("=== Meta-Story: Characters discuss finality ===");
        for (StoryCharacter c : cast) {
            System.out.println(c.characterId + " says: " + c.attemptHackFinalAttribute());
        }

        System.out.println();
        System.out.println("=== Achievements & Stats ===");
        for (StoryCharacter c : cast) {
            System.out.println(c.getIdentityCard() + " | Achievements: " + c.getAchievementCount() + " | Skills: " + c.skillSet);
        }

        System.out.println();
        System.out.println("=== Interactive Choice Simulation ===");
        Random r = new Random();
        String choice = r.nextBoolean() ? "help villain" : "help hero";
        System.out.println("Player choice: " + choice);
        if (choice.equals("help villain")) {
            drev.gainAchievement();
            System.out.println("Outcome: Unstable alliance formed.");
        } else {
            alice.gainAchievement();
            System.out.println("Outcome: Heroic triumph, but at a cost.");
        }

        System.out.println();
        System.out.println("Final Cast Snapshot:");
        for (StoryCharacter c : cast) {
            System.out.println(c.getIdentityCard() + " | Mood: " + c.currentMood + " | Location: " + c.currentLocation);
        }
    }
}
