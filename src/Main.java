package week6_absfactory;
import java.util.ArrayList;


//=======================================================================
//Name        : AbstractFactory.cpp
// 1. AbstractFactory  ( CarFactory )
//	  Declares an interface for operations that create abstract products
// 2. ConcreteFactory  (OPELFactory,FordFactory)
//	  Implements the operations to create concrete product objects
// 3. AbstractProduct   (Part, Engine, Transmission)
//	  Declares an interface for a type of product object
// 4. Product  (OPEL_Engine, OPEL_Transmission,
//				 FORD_Engine, FORD_Transmission.)
//	  Defines a product object to be created by the corresponding 
//    concrete factory implements the AbstractProduct interface
// 5. Client  (BuildCar)
//	  Uses interfaces declared by AbstractFactory and AbstractProduct 
//    classes
//=======================================================================

// Top "Abstract Product" Part class
abstract class Part {
	abstract public String displayName();
	abstract double getPrice();
}
// An 'Abstract Product A' class 
// Engine base class.
abstract class Engine extends Part {
	protected double price;
	protected String name;
	public double getPrice(){ return price; }
	public String displayName() { return name;	}
}

//A 'ConcreteProduct A1' class
class OPEL_Engine extends Engine {	
	public OPEL_Engine(double p) { 
		price = p;
		name = new String("OPEL Engine");
		System.out.println("OPEL Engine is created..."); 
	}	
}
//A 'ConcreteProduct A2' class
class FORD_Engine extends Engine {
	public FORD_Engine(double p) { 
		price = p;
		name = new String("FORD Engine");
		System. out.println("FORD Engine is created...");
	}
}
//An 'Abstract Product B' class 
//Transmission base class
abstract class Transmission extends Part {
	protected double price;
	protected String name;
	public double getPrice(){ return price; }
	public String displayName() { return name;	}
}

//A 'ConcreteProduct B1' class
class OPEL_Transmission extends Transmission {
	public OPEL_Transmission(double p) { 
		price = p;
		name = new String("OPEL Transmission");
		System. out.println("OPEL Transmission is created...");
	}
}
//A 'ConcreteProduct B2' class
class FORD_Transmission extends Transmission {
	public FORD_Transmission(double p) { 
		price = p;
		name = new String("FORD Transmission");
		System. out.println("FORD Transmission is  created...");
	}
}	
//An 'Abstract Factory' class
abstract class CarFactory {
	abstract public Engine createEngine();
	abstract public Transmission createTransmission();
}
//A 'Concrete Factory' class
class OPELFactory extends CarFactory {
	public OPEL_Engine createEngine() {
		return new OPEL_Engine (25000.00);
	}
	public OPEL_Transmission createTransmission() {
		return new OPEL_Transmission(10000.00);
	}
}

//Another 'Concrete Factory' class
class FORDFactory extends CarFactory {
	public FORD_Engine createEngine() {
		return new FORD_Engine (20000.00);
	}
	public FORD_Transmission createTransmission() {
		return new FORD_Transmission(12000.00);
	}
}

//The 'Client'.
class BuildCar {
	// Object creation is delegated to factory.
	public void createCar(CarFactory factory) {
		parts = new ArrayList<Part>();
		parts.add(factory.createEngine());
		parts.add(factory.createTransmission());
	}
	void displayParts() {
		System.out.println("\tListing Parts\n\t-------------");
		parts.forEach(p  -> System.out.println("\t"+ p.displayName() + 
				            " " + p.getPrice()));
	}
	private ArrayList<Part> parts;
}
//Abstract Factory Method Design Pattern.
//Entry point into main application.
public class AbstractFactory {
	public static void main(String[] args) {
	   // Create factories.
	   CarFactory OPEL = new OPELFactory();
	   CarFactory FORD = new FORDFactory();

	   BuildCar car = new BuildCar();
	   System.out.println("Creating OPEL");
	   car.createCar(OPEL);
	   car.displayParts();
	   
	   System.out.println("Creating FORD");
	   car.createCar(FORD);
	   car.displayParts();
	}
}