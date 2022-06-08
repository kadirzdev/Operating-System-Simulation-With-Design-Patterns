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
	public ArrayList<RegisteredApplications> registeredApplications = new ArrayList<RegisteredApplications>();
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

}

