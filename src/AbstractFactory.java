import java.util.ArrayList;

abstract class FileSystem {
    abstract public String displayName();

}

abstract class Directory extends FileSystem {
    protected String name;

    public String displayName() {
        return name;
    }
}


class LINUX_Directory extends Directory {
    public LINUX_Directory() {
        name = new String("Linux Directory");
        System.out.println("Linux Directory Created...");
    }
}

class NT_Directory extends Directory {
    public NT_Directory() {
        name = new String("NT Directory");
        System.out.println("NT Directory Created...");
    }
}


class BSD_Directory extends Directory {
    public BSD_Directory() {
        name = new String("BSD Directory");
        System.out.println("BSD Directory Created...");
    }
}


abstract class File extends FileSystem implements API{
    protected String name;
    protected File handle;

    public String displayName() {
        return name;
    }

    public int fprintf(File handle, String str) {
        return -1;
    }

}


class LINUX_File extends File implements API {
    public String name;
    public File handleL;
    public LINUX_File() {
        name = new String("Linux File");
        System.out.println("Linux File Created...");
    }
    public int uprintf(String str, File handle){

        return 0;
    }
}

class NT_File extends File implements API{
    public NT_File() {
        name = new String("NT File");
        System.out.println("NT File Created...");
    }
    public int printf (byte[] charArrray, File handle){
        return -1;
    }
}


class BSD_File extends File implements API{
    public BSD_File() {
        name = new String("BSD File");
        System.out.println("BSD File Created...");
    }
    public int uprintf(String str, File handle){

        return 0;
    }
}


abstract class FileSystemFactory {
    abstract public Directory createDirectory();
    abstract public File createFile();
}

class LinuxFileSystemFactory extends FileSystemFactory {
    public LINUX_Directory createDirectory() {
        return new LINUX_Directory();
    }
    public LINUX_File createFile() {
        return new LINUX_File();
    }
}

class NTFileSystemFactory extends FileSystemFactory {
    public NT_Directory createDirectory() {
        return new NT_Directory();
    }
    public NT_File createFile() {
        return new NT_File();
    }
}

class BSDFileSystemFactory extends FileSystemFactory {
    public BSD_Directory createDirectory() {
        return new BSD_Directory();
    }
    public BSD_File createFile() {
        return new BSD_File();
    }
}

//The 'Client'
class BuildFileSystem {
    // Object creation is delegated to factory.
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
        FileSystemFactory LINUX = new LinuxFileSystemFactory();
        FileSystemFactory NT    = new NTFileSystemFactory();
        FileSystemFactory BSD   = new BSDFileSystemFactory();

        BuildFileSystem fileSystem = new BuildFileSystem();
        System.out.println("Creating LINUX File System");
        fileSystem.createFileSystem(LINUX);
        fileSystem.displayFileSystems();

        System.out.println("Creating NT File System");
        fileSystem.createFileSystem(NT);
        fileSystem.displayFileSystems();

        System.out.println("Creating BSD File System");
        fileSystem.createFileSystem(BSD);
        fileSystem.displayFileSystems();

    }
}