import java.util.ArrayList;
//IODEvices ArrayList olacak


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


//'Subject' ==> Stock
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
	// Notify the Observers.
	public void Notify() {
		// set argument to something that helps
		// tell the Observers what happened
		for (int i = 0; i < registeredApplications.size(); i++) {
			registeredApplications.get(i).Update(this);
		}
	}
	public void Reset(Devices device) {
		System.out.println("Resetting " + device.getName() + "...");
	}
	public String getName() {return _name;}
	void setName(String value) {_name = value;}
	protected String _name;          // Internal Subject state
	protected ArrayList<RegisteredApplications> registeredApplications = new ArrayList<RegisteredApplications>();
}

//'ConcreteSubject' ==> IBM

class HardDisk extends Devices {

	public HardDisk(String name) {
		super(name);
	}


	public void setName(String name) {
		// Whenever a change happens to _price, notify
		// observers.
		_name = name;
		Notify();
	}





}
class CPU extends Devices {
    // Constructor
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
}
class IODevices extends Devices {
    // Constructor
    public IODevices(String name) {
        super(name);
    }
    public String getName() {return _name;}
    public void setName(String name) {
        // Whenever a change happens to _price, notify
        // observers.
        _name = name;
        Notify();
    }
}

//'Observer'  ==> Abstract Observer.

interface Applications {
	public void Update(Devices devices);
}

//'ConcreteObserver' ==> Investor

class RegisteredApplications implements Applications {
	private Devices _devices;
	private String _regAp_name;
	private String _device_name;     // Internal Observer state
	// Constructor
	public RegisteredApplications(String name) {
		_regAp_name = name;
	}
	public void Update(Devices devices) {
		 _devices = devices; 				 // Reference to Subject
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
		// Create investors
	    RegisteredApplications s = new RegisteredApplications("Ahmet");
		RegisteredApplications b = new RegisteredApplications("Ayse");

	    // Create IBM stock and attach investors
	    HardDisk hardDisk = new HardDisk("Hard Disk");
		CPU cpu = new CPU("CPU");
		IODevices ioDevices = new IODevices("I/O Devices");

		s.setDevices(hardDisk);
		s.setDevices(cpu);
		s.setDevices(ioDevices);
		b.setDevices(hardDisk);
		b.setDevices(cpu);
		b.setDevices(ioDevices);
		hardDisk.Attach(s);
		cpu.Attach(s);
		ioDevices.Attach(s);
		hardDisk.Attach(b);
		cpu.Attach(b);
		ioDevices.Attach(b);

		hardDisk.setName("new HardDisk");
		cpu.setName("New CPU");
		ioDevices.setName("new IO");


	    System.out.println("Removing Ayse from Notification list");
	    hardDisk.Detach(b);
		cpu.Detach(b);
		ioDevices.Detach(b);
		hardDisk.setName("new HardDisk2");
		cpu.setName("New CPU2");
		ioDevices.setName("new IO2");

	    // We have a dangling reference in our Observer. Remember our
	    // "implementation issues" discussion in the lecture.
	    System.out.println(s.getDevices()); // Reference has a value.
	}
}

