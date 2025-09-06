public class AudioMixer 
{
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;

    public AudioMixer() 
	{
        this("StandardMix-8", 8, false); 
    }

    public AudioMixer(String mixerModel, int numberOfChannels) 
	{
        this(mixerModel, numberOfChannels, false); 
    }

    public AudioMixer(String mixerModel, int numberOfChannels, boolean hasBluetoothConnectivity) {
        this(mixerModel, numberOfChannels, hasBluetoothConnectivity, 120.0); 
    }

    public AudioMixer(String mixerModel, int numberOfChannels,
                      boolean hasBluetoothConnectivity, double maxVolumeDecibels) {
        this.mixerModel = mixerModel;
        this.numberOfChannels = numberOfChannels;
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
        this.maxVolumeDecibels = maxVolumeDecibels;

        this.connectedDevices = new String[numberOfChannels]; 
        this.deviceCount = 0;

        System.out.println(">> Constructor executed for model: " + mixerModel);
    }

    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("Connected: " + deviceName);
        } else {
            System.out.println("All channels occupied!");
        }
    }

    public void displayMixerStatus() {
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
        System.out.println("Connected Devices: " + deviceCount + "/" + numberOfChannels);

        for (int i = 0; i < deviceCount; i++) {
            System.out.println(" Channel " + (i + 1) + ": " + connectedDevices[i]);
        }
    }

    public static void main(String[] args) {

        AudioMixer mixer1 = new AudioMixer();
        mixer1.connectDevice("Guitar");
        mixer1.connectDevice("Keyboard");

        AudioMixer mixer2 = new AudioMixer("StudioMix-16", 16);
        mixer2.connectDevice("Drum Machine");
        mixer2.connectDevice("Microphone");

        AudioMixer mixer3 = new AudioMixer("DJMix-4", 4, true);
        mixer3.connectDevice("Laptop");
        mixer3.connectDevice("Turntable");

        AudioMixer mixer4 = new AudioMixer("ConcertMix-24", 24, true, 150.0);
        mixer4.connectDevice("Stage Mic");
        mixer4.connectDevice("Bass Guitar");

        mixer1.displayMixerStatus();
        mixer2.displayMixerStatus();
        mixer3.displayMixerStatus();
        mixer4.displayMixerStatus();


        System.out.println("1. No-arg constructor → calls 3-param constructor → calls full constructor.");
        System.out.println("2. Two-param constructor → calls 3-param constructor → calls full constructor.");
        System.out.println("3. Three-param constructor → calls full constructor directly.");
        System.out.println("4. Full constructor initializes everything and prints execution message.");
    }
}
