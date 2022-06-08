package Project2;

import java.util.ArrayList;
import java.util.Scanner;

//**********************
//*NERGİS GİZEM YILMAZ *
//*BEGÜM KÜÇÜK         *
//*YAĞMUR ZEYNEP TOPRAK*
//*SUPHİ KADİR ÖZARPACI*
//*OS Modelling        *
//**********************
//

//============================================================================
//Name        : ObserverPattern.java
//============================================================================
//The classes and/or objects participating in this pattern are:
//	1. Subject  (Devices)
//		. Knows its observers. Any number of Observer objects may observe a subject.
//	    . Provides an interface for attaching and detaching Observer objects.
//	2. ConcreteSubject  (HardDisk, CPU, IODevices)
//	    . Stores state of interest to ConcreteObserver sends a notification to its.
//		. Observers when its state changes.
//	3. Observer  (Applications)
//   . Defines an updating interface for objects that should be notified
//     of changes in a subject.
//	4. ConcreteObserver  (RegisteredApplications)
//   . Maintains a reference to a ConcreteSubject object
//   . Stores state that should stay consistent with the subject's
//   . Implements the Observer updating interface to keep its state
//     consistent with the subject's


//'Subject' ==> Devices
abstract class Devices {
	public Devices(String name) {
		_name = name;
	}
    //Register the Observers
	public void Attach (RegisteredApplications regApps) {
		registeredApplications.add(regApps);
	}
	//Unregister from the list of Observers.
	public void Detach (RegisteredApplications regApps) {
		for (int i = 0; i< registeredApplications.size(); i++) {
			if (registeredApplications.get(i).getName() == regApps.getName()) {
				registeredApplications.remove(i);
				return;
			}
		}
	}

	// Display Devices
	public static void DeviceSelect() {

		System.out.println("SYSTEM CONTROL PANEL");
		System.out.println("HARDDISK");
		System.out.println("CPU");
		System.out.println("IODEVICES");
		System.out.println("MACRO COMMANDS");

		// Upper Choice
		Scanner sc = new Scanner(System.in); //System.in is a standard input stream
		String selectedDevice = sc.nextLine(); //reads string

		switch (selectedDevice.toUpperCase()) {
			case "HARDDISK":
				System.out.println("HardDisk Commands");
				System.out.println("RESET - Resets the HardDisk");
				System.out.println("ADD NOTIFIER - Notifies the registered applications");
				System.out.println("REMOVE NOTIFIER - Removes the registered applications");

				String hdAction = sc.nextLine().toUpperCase(); //reads string

				switch (hdAction) {

					case "RESET":
						ResetDevice resetDevice;
						OperatingSystem os = new OperatingSystem();
						HardDisk devices = new HardDisk("HardDisk");
						resetDevice = new HardDiskReset(devices, "HardDisk");
						os.Reset(resetDevice);
						break;
					case "ADD NOTIFIER":
						break;
					case "REMOVE NOTIFIER":

				}
				break;
			case "CPU":
				System.out.println("CPU Commands");
				System.out.println("RESET - Resets the CPU");
				System.out.println("ADD NOTIFIER - Notifies the registered applications");
				System.out.println("REMOVE NOTIFIER - Removes the registered applications");

				String cpuAction = sc.nextLine().toUpperCase(); //reads string

				switch (cpuAction) {

					case "RESET":
						ResetDevice resetDevice;
						OperatingSystem os = new OperatingSystem();
						CPU devices = new CPU("CPU");
						resetDevice = new CPUReset(devices, "CPU");
						os.Reset(resetDevice);
						break;
					case "ADD NOTIFIER":
						break;
					case "REMOVE NOTIFIER":

				}
				break;
			case "IODEVICES":
				System.out.println("ID DEVICE Commands");
				System.out.println("RESET - Resets the IODevices");
				System.out.println("ADD NOTIFIER - Notifies the registered applications");
				System.out.println("REMOVE NOTIFIER - Removes the registered applications");

				String IOAction = sc.nextLine().toUpperCase(); //reads string

				switch (IOAction) {

					case "RESET":
						ResetDevice resetDevice;

						OperatingSystem os = new OperatingSystem();
						IODevices devices = new IODevices("IO Device");
						resetDevice = new IODeviceReset(devices, "IO Device");
						os.Reset(resetDevice);
						break;
					case "ADD NOTIFIER":
						String applicationName = sc.nextLine().toUpperCase(); //reads string
						RegisteredApplications s = new RegisteredApplications(applicationName);
						IODevices ioDevices = new IODevices("I/O Devices");
						s.setDevices(ioDevices);
						ioDevices.Attach(s);
						DeviceSelect();
					case "REMOVE NOTIFIER":

				}
				break;
			case "MACRO COMMANDS":
				System.out.println("RESETALL - Resets all the devices");
				String GenericReset = sc.nextLine().toUpperCase(); //reads string

				switch (GenericReset) {

					case "RESETALL":
						ResetDevice resetDevice;
						OperatingSystem os = new OperatingSystem();
						HardDisk devices = new HardDisk("HardDisk");
						CPU devices2 = new CPU("CPU");
						IODevices devices3 = new IODevices("IO Devices");
						ArrayList<ResetDevice> devices4 = new ArrayList<ResetDevice>();
						devices4.add(new HardDiskReset(devices, "HardDisk"));
						devices4.add(new CPUReset(devices2, "CPU"));
						devices4.add(new IODeviceReset(devices3, "IO Devices"));
						resetDevice = new ResetAllDevices(devices4);
						os.Reset(resetDevice);
						break;
					case "ADD NOTIFIER":
						System.out.println("ADDALL - Notifies the registered ALL applications");
						break;
					case "REMOVE NOTIFIER":
						System.out.println("REMOVEALL - Removes the registered ALL applications");
				}
				break;

			default:
				System.out.println("Please enter HardDisk, CPU or IODevices");
				break;
		}
	}


	// Notify the Observers.
	public void Notify() {
		// set argument to something that helps
		// tell the Observers what happened
		for (int i = 0; i < registeredApplications.size(); i++) {
			registeredApplications.get(i).Update(this);
		}
	}
	public String getName() {return _name;}
	void setName(String value) {_name = value;}
	protected String _name;
	protected ArrayList<RegisteredApplications> registeredApplications = new ArrayList<RegisteredApplications>();
	abstract public  void Reset();
}

//'ConcreteSubject' ==> HardDisk
//ReceiverHardDisk
class HardDisk extends Devices {
	//HardDisk has FileSystem
	FileSystem fileSystem = new FileSystem() {
		protected String displayName() {
			return null;
		}
	};
	public HardDisk(String name) {
		super(name);
	}
	public void setName(String name) {
		_name = name;
		Notify();
	}
	public void Reset() {
		System.out.println("Resetting " + _name + "...");
	}
}

//'ConcreteSubject' ==> CPU
//ReceiverCPU
class CPU extends Devices {
    public CPU(String name) {
        super(name);
    }
	public String getName(){
		return _name;
	}
    public void setName(String name) {
        // Whenever a change happens to _price, notify
        // observers.
		_name = name;
		Notify();
    }
	public void Reset() {
		System.out.println("Resetting " + _name + "...");
	}
}

//'ConcreteSubject' ==> IODevices
//ReceiverIODevice
class IODevices extends Devices {
    public IODevices(String name) {
        super(name);
    }
    public String getName() {return _name;}
    public void setName(String name) {
        _name = name;
        Notify();
    }
	public void Reset() {
		System.out.println("Resetting " + _name + "...");
	}
}

//'Observer'  ==> Applications.
interface Applications {
	public void Update(Devices devices);
}

//'ConcreteObserver' ==> RegisteredApplications
class RegisteredApplications implements Applications {
	private Devices _devices;
	private String _regAp_name;
	private String _device_name;
	// Constructor
	public RegisteredApplications(String name) {
		_regAp_name = name;
	}
	public void Update(Devices devices) {
		 _devices = devices;
		 _device_name = devices.getName();
		 System.out.println("Notified " + _regAp_name + " of " + _device_name +
		         "'s " + "change to ");
	 }
	public Devices getDevices() { return _devices; }
	public void setDevices(Devices value) { _devices = value; }
	public String getName() { return _regAp_name; }
}

//MainApp test application
public class Observer {
	public static void main(String[] args) {



		Devices.DeviceSelect();





	}
}

