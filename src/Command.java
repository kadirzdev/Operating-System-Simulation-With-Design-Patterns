interface resetDevice {
    public void shutdown();
}
class HardDiskReset implements resetDevice {
    public void shutdown() {
        devices.Reset(HardDisk);
    }
    private Devices devices;
    private Devices HardDisk;
}
class CPUReset implements resetDevice {
    public void shutdown() {
        devices.Reset(CPU);
    }
    private Devices devices;
    private Devices CPU;
}
class IOReset implements resetDevice {
    public void shutdown() {
        devices.Reset(IODevices);
    }
    private Devices devices;
    private Devices IODevices;
}
class OperatingSystem {

    public void Reset(resetDevice resetDevice) {
        resetDevice.shutdown();
        devices.Reset(HardDisk);
        devices.Reset(CPU);
        devices.Reset(IODevices);

    }
    private Devices devices;
    private Devices HardDisk;
    private Devices CPU;
    private Devices IODevices;
}

class GenericReset implements resetDevice{
    public void shutdown() {
        devices.Reset(HardDisk);
        devices.Reset(CPU);
        devices.Reset(IODevices);
    }
    private Devices devices;
    private Devices HardDisk;
    private Devices CPU;
    private Devices IODevices;


}

public class Command {
    public static void main(String[] args) {

    }
}
