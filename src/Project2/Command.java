package Project2;
//**********************
//*NERGİS GİZEM YILMAZ *
//*BEGÜM KÜÇÜK         *
//*YAĞMUR ZEYNEP TOPRAK*
//*SUPHİ KADİR ÖZARPACI*
//*OS Modelling        *
//**********************
//

import java.util.ArrayList;

//"Command"
//
interface ResetDevice {
    void Execute();
}
//"ConcreteCommand"
//
class HardDiskReset implements ResetDevice {
    // Constructor
    public HardDiskReset(HardDisk devices, String name) {
        _devices = devices;
        _name = name;
    }
    public void Execute() {
        _devices.Reset();
    }

    private HardDisk _devices;
    private String _name;
}

//"ConcreteCommand"
//
class CPUReset implements ResetDevice {
    // Constructor
    public CPUReset(CPU devices, String name) {
        _devices = devices;
        _name = name;
    }
    public void Execute() {
        _devices.Reset();
    }

    private CPU _devices;
    private String _name;
}


//"ConcreteCommand"
//
class IODeviceReset implements ResetDevice {
    // Constructor
    public IODeviceReset(IODevices devices, String name) {
        _devices = devices;
        _name = name;
    }
    public void Execute() {
        _devices.Reset();
    }


    private IODevices _devices;
    private String _name;
}

class ResetAllDevices implements ResetDevice {
    public ResetAllDevices(ArrayList<ResetDevice> devices) {
        _devices = devices;
    }
    public void Execute() {
        for (ResetDevice device : _devices) {
            device.Execute();
        }
    }

    private ArrayList<ResetDevice> _devices;
}

// "Invoker"
class OperatingSystem {
    public void Reset(ResetDevice resetDevice) {
        ResetDevice _resetDevice = resetDevice;
        resetDevice.Execute();
    }
};

public class Command {
    public static void main(String[] args) {
        ResetDevice resetDevice;
        OperatingSystem os = new OperatingSystem();
        HardDisk devices = new HardDisk("HardDisk");
        resetDevice = new HardDiskReset(devices, "HardDisk");
        os.Reset(resetDevice);

        CPU devices2 = new CPU("CPU");
        resetDevice = new CPUReset(devices2, "CPU");
        os.Reset(resetDevice);

        IODevices devices3 = new IODevices("IO Devices");
        resetDevice = new IODeviceReset(devices3, "IO Devices");
        os.Reset(resetDevice);
    }
}