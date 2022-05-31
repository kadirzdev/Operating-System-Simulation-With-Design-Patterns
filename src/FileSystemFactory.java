import java.util.ArrayList;

abstract class FileSystem {

}


abstract class Directory extends FileSystem {

}


class LINUX_Directory extends Directory {
    public LINUX_Directory() {
        System.out.println("Linux Directory Created...");
    }
}

class NT_Directory extends Directory {
    public NT_Directory() {
        System.out.println("NT Directory Created...");
    }
}


class BSD_Directory extends Directory {
    public BSD_Directory() {
        System.out.println("BSD Directory Created...");
    }
}

abstract class File extends FileSystem {

}


class LINUX_File extends Directory {
    public LINUX_File() {
        System.out.println("Linux File Created...");
    }
}

class NT_File extends Directory {
    public NT_File() {
        System.out.println("NT File Created...");
    }
}


class BSD_File extends Directory {
    public BSD_File() {
        System.out.println("BSD File Created...");
    }
}

abstract class FileSystemFactory {
    abstract public Directory createDirectory();
    abstract public File createFile();
}

class LinuxFileSystemFactory extends FileSystemFactory {
    public LINUX_Directory createDirectory() {

    }
    public LINUX_File createFile() {

    }
}

class NTFileSystemFactory extends FileSystemFactory {
    public NT_Directory createDirectory() {

    }
    public NT_File createFile() {

    }
}

class BSDFileSystemFactory extends FileSystemFactory {
    public BSD_Directory createDirectory() {
    }
    public BSD_File createFile() {
    }
}

//The 'Client'.
class BuildCar {
    // Object creation is delegated to factory.
    public void createFileSystem(FileSystemFactory factory) {
        fileSystems = new ArrayList<FileSystem>();
        fileSystems.add(factory.createDirectory());
        fileSystems.add(factory.createFile());
    }
    void displayFileSystems() {
        System.out.println("\tListing File Systems\n\t-------------");
        fileSystems.forEach(p  -> System.out.println("\t"+ p.displayName() +
                " " + p.getPrice()));
    }
    private ArrayList<FileSystem> fileSystems;
}