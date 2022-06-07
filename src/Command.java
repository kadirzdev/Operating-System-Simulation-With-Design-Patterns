
import java.util.ArrayList;
import java.util.Iterator;

//
//The classes and/or objects participating in this pattern are:
//
//1. ResetDevice  (Command)
//		- declares an interface for executing an operation.
//2. ConcreteCommand  (HardDiskReset)
//		- defines a binding between a Receiver object and an action.
//		- implements Execute by invoking the corresponding operation(s) on Receiver
//3. Client  (Main)
//		- creates a ConcreteCommand object and sets its receiver.
//4. Invoker  (OperatingSystem)
//		- asks the command to carry out the request
//5. Receiver  (HardDisk)
//		- knows how to perform the operations associated with carrying out
//		  a request. Any class may serve as a Receiver.
//
//

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

// "Invoker"
class OperatingSystem {
    public void Reset(ResetDevice resetDevice) {
        ResetDevice _resetDevice = resetDevice;
        resetDevice.Execute();
    }

};

public class Command {
    public static void main(String[] args) {

        // Create user and let her compute
        ResetDevice resetDevice = null;
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