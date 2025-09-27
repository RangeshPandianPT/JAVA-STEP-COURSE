abstract class Artwork {
    String title;
    String artist;

    Artwork(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    void displayBasicInfo() {
        System.out.println("Title: " + title);
        System.out.println("Artist: " + artist);
    }

    abstract void displayGeneralFeatures();
}

class Painting extends Artwork {
    String brushTechnique;
    String colorPalette;
    String frameSpec;

    Painting(String title, String artist, String brushTechnique, String colorPalette, String frameSpec) {
        super(title, artist);
        this.brushTechnique = brushTechnique;
        this.colorPalette = colorPalette;
        this.frameSpec = frameSpec;
    }

    @Override
    void displayGeneralFeatures() {
        System.out.println("Type: Painting");
        System.out.println("Color Palette: " + colorPalette);
    }

    void displayDetailedPaintingInfo() {
        System.out.println("Brush Technique: " + brushTechnique);
        System.out.println("Frame Specification: " + frameSpec);
    }
}

class Sculpture extends Artwork {
    String material;
    String dimensions;
    String lighting;

    Sculpture(String title, String artist, String material, String dimensions, String lighting) {
        super(title, artist);
        this.material = material;
        this.dimensions = dimensions;
        this.lighting = lighting;
    }

    @Override
    void displayGeneralFeatures() {
        System.out.println("Type: Sculpture");
        System.out.println("Material: " + material);
    }

    void displayDetailedSculptureInfo() {
        System.out.println("Dimensions: " + dimensions);
        System.out.println("Lighting Requirements: " + lighting);
    }
}

class DigitalArt extends Artwork {
    String resolution;
    String fileFormat;
    String interactiveElements;

    DigitalArt(String title, String artist, String resolution, String fileFormat, String interactiveElements) {
        super(title, artist);
        this.resolution = resolution;
        this.fileFormat = fileFormat;
        this.interactiveElements = interactiveElements;
    }

    @Override
    void displayGeneralFeatures() {
        System.out.println("Type: Digital Art");
        System.out.println("Resolution: " + resolution);
    }

    void displayDetailedDigitalInfo() {
        System.out.println("File Format: " + fileFormat);
        System.out.println("Interactive Elements: " + interactiveElements);
    }
}

class Photography extends Artwork {
    String cameraSettings;
    String editingDetails;
    String printSpec;

    Photography(String title, String artist, String cameraSettings, String editingDetails, String printSpec) {
        super(title, artist);
        this.cameraSettings = cameraSettings;
        this.editingDetails = editingDetails;
        this.printSpec = printSpec;
    }

    @Override
    void displayGeneralFeatures() {
        System.out.println("Type: Photography");
        System.out.println("Camera Settings: " + cameraSettings);
    }

    void displayDetailedPhotoInfo() {
        System.out.println("Editing Details: " + editingDetails);
        System.out.println("Print Specification: " + printSpec);
    }
}

public class ArtGallery {
    public static void main(String[] args) {
        Artwork[] gallery = {
            new Painting("Sunset Bliss", "Ravi", "Impressionist", "Warm Tones", "Wooden Frame"),
            new Sculpture("Stone Warrior", "Meera", "Marble", "6ft Tall", "Spotlight"),
            new DigitalArt("Virtual Dream", "Arjun", "4K", "PNG", "Augmented Reality"),
            new Photography("City Lights", "Ananya", "ISO 800, f/2.8", "HDR Editing", "Glossy Paper")
        };

        for (Artwork art : gallery) {
            art.displayBasicInfo();
            art.displayGeneralFeatures();

            if (art instanceof Painting) {
                Painting p = (Painting) art;
                p.displayDetailedPaintingInfo();
            } else if (art instanceof Sculpture) {
                Sculpture s = (Sculpture) art;
                s.displayDetailedSculptureInfo();
            } else if (art instanceof DigitalArt) {
                DigitalArt d = (DigitalArt) art;
                d.displayDetailedDigitalInfo();
            } else if (art instanceof Photography) {
                Photography ph = (Photography) art;
                ph.displayDetailedPhotoInfo();
            }
        }
    }
}
