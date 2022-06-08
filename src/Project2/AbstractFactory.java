package Project2;

import java.util.ArrayList;

//**********************
//*NERGİS GİZEM YILMAZ *
//*BEGÜM KÜÇÜK         *
//*YAĞMUR ZEYNEP TOPRAK*
//*SUPHİ KADİR ÖZARPACI*
//*OS Modelling        *
//**********************

//Top Class of the File&Directory
abstract class FileSystem {
    abstract protected String displayName();
}

//AbstractProductA
abstract class Directory extends FileSystem {
    protected String name;

    public String displayName() {
        return name;
    }
}

//AbstractProductA1
class LINUX_Directory extends Directory {
    public LINUX_Directory() {
        name = new String("Linux Directory");
        System.out.println("Linux Directory Created...");
    }
}

//AbstractProductA2
class NT_Directory extends Directory {
    public NT_Directory() {
        name = new String("NT Directory");
        System.out.println("NT Directory Created...");
    }
}

//AbstractProductA3
class BSD_Directory extends Directory {
    public BSD_Directory() {
        name = new String("BSD Directory");
        System.out.println("BSD Directory Created...");
    }
}

//AbstractProductB
abstract class File extends FileSystem{
    protected String name;
    protected File handle;

    public String displayName() {
        return name;
    }

}

//AbstractProductB1
class LINUX_File extends File{
    public File handleL;
    public LINUX_File() {
        name = new String("Linux File");
        System.out.println("Linux File Created...");
    }
    //Adaptee of the adapter pattern
    public int uprintf(String str, File handle){
        return -1;
    }
}

//AbstractProductB2
class NT_File extends File{
    public NT_File() {
        name = new String("NT File");
        System.out.println("NT File Created...");
    }
    //Adaptee of the adapter pattern
    public int printf (byte[] charArrray, File handle){
        return -1;
    }
}

//AbstractProductB3
class BSD_File extends File{
    public BSD_File() {
        name = new String("BSD File");
        System.out.println("BSD File Created...");
    }
    //Adaptee of the adapter pattern
    public int uprintf(String str, File handle){

        return -1;
    }
}

//AbstractFactory
abstract class FileSystemFactory {
    abstract public Directory createDirectory();
    abstract public File createFile();

    //Singleton Pattern
    public static FileSystemFactory getInstance() {
        if (instance == null) {
            instance = new LinuxFileSystemFactory();
        }
        return instance;
    }
    private static FileSystemFactory instance = null;

}

//ConcreteFactory1
class LinuxFileSystemFactory extends FileSystemFactory {
    public LINUX_Directory createDirectory() {
        return new LINUX_Directory();
    }
    public LINUX_File createFile() {
        return new LINUX_File();
    }
    private static LinuxFileSystemFactory instance = null;
}

//ConcreteFactory2
class NTFileSystemFactory extends FileSystemFactory {
    public NT_Directory createDirectory() {
        return new NT_Directory();
    }
    public NT_File createFile() {
        return new NT_File();
    }
    private static NTFileSystemFactory instance = null;
}

//ConcreteFactory3
class BSDFileSystemFactory extends FileSystemFactory {
    public BSD_Directory createDirectory() {
        return new BSD_Directory();
    }
    public BSD_File createFile() {
        return new BSD_File();
    }
    private static BSDFileSystemFactory instance = null;
}

//The 'Client'
class BuildFileSystem {
    public void createFileSystem(FileSystemFactory factory) {
        fileSystems = new ArrayList<FileSystem>();
        fileSystems.add(factory.createDirectory());
        fileSystems.add(factory.createFile());
    }
    void displayFileSystems() {
        System.out.println("\tListing File Systems\n\t-------------");
        fileSystems.forEach(p  -> System.out.println("\t"+ p.displayName()));
    }
    private ArrayList<FileSystem> fileSystems;
}

//Abstract Factory Method Design Pattern.
//Entry point into main application.
public class AbstractFactory {
    public static void main(String[] args) {
        // Create factories.
        FileSystemFactory LINUX = FileSystemFactory.getInstance();
        FileSystemFactory NT    = FileSystemFactory.getInstance();

        BuildFileSystem fileSystem = new BuildFileSystem();
        System.out.println("Creating LINUX File System");
        fileSystem.createFileSystem(LINUX);
        fileSystem.displayFileSystems();

        System.out.println("Creating NT File System");
        fileSystem.createFileSystem(NT);
        fileSystem.displayFileSystems();

        System.out.println(LINUX == NT);


    }
}
