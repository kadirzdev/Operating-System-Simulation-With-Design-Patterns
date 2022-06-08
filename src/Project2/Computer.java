package Project2;

import java.util.ArrayList;
import java.util.Scanner;

public class Computer {

    public static void main(String[] args) {

        // Create FileSystemType.
        System.out.println("Please select a file system type: ");
        FileSystemFactory FileSystemType = FileSystemFactory.getInstance();

        BuildFileSystem fileSystem = new BuildFileSystem();
        fileSystem.createFileSystem(FileSystemType);
        fileSystem.displayFileSystems();

        // set device harddisk
        HardDisk harddisk = new HardDisk("HardDisk");
        harddisk.setName("HardDisk");

        // set device cpu
        CPU cpu = new CPU("CPU");
        cpu.setName("CPU");

        // set device iodevice
        IODevices IODevice = new IODevices("IODevices");
        IODevice.setName("IODevices");



        while (true) {


            System.out.println("SYSTEM CONTROL PANEL");
            System.out.println("1- HARDDISK");
            System.out.println("2- CPU");
            System.out.println("3- IODEVICES");
            System.out.println("4- MACRO COMMANDS");

            // Upper Choice
            Scanner sc = new Scanner(System.in); //System.in is a standard input stream
            String selectedDevice = sc.nextLine(); //reads string


            switch (selectedDevice.toUpperCase()) {
                case "1":
                    System.out.println("HardDisk Commands");
                    System.out.println("1- RESET - Resets the HardDisk");
                    System.out.println("2- ADD NOTIFIER - Notifies the registered applications");
                    System.out.println("3- REMOVE NOTIFIER - Removes the registered applications");

                    String hdAction = sc.nextLine().toUpperCase(); //reads string
                    switch (hdAction) {


                        case "1":
                            ResetDevice resetDevice;
                            OperatingSystem os = new OperatingSystem();
                            resetDevice = new HardDiskReset(harddisk, "HardDisk");
                            os.Reset(resetDevice);
                            break;
                        case "2":
                            System.out.println("Please enter the application name: ");
                            String applicantName = sc.nextLine(); //reads string
                            RegisteredApplications s = new RegisteredApplications(applicantName);
                            harddisk.Attach(s);
                            System.out.println("Application " + applicantName + " registered successfully");
                            break;
                        case "3":
                            System.out.println("Please enter the application name: ");
                            String applicantNameRemove = sc.nextLine(); //reads string
                            RegisteredApplications x = new RegisteredApplications(applicantNameRemove);
                            harddisk.Detach(x);
                            System.out.println("Application " + applicantNameRemove + " removed successfully");
                            break;
                    }
                    break;
                case "2":
                    System.out.println("CPU Commands");
                    System.out.println("1- RESET - Resets the CPU");
                    System.out.println("2- ADD NOTIFIER - Notifies the registered applications");
                    System.out.println("3- REMOVE NOTIFIER - Removes the registered applications");

                    String cpuAction = sc.nextLine().toUpperCase(); //reads string

                    switch (cpuAction) {

                        case "1":
                            ResetDevice resetDevice;
                            OperatingSystem os = new OperatingSystem();
                            resetDevice = new CPUReset(cpu, "CPU");
                            os.Reset(resetDevice);
                            break;
                        case "2":
                            System.out.println("Please enter the application name: ");
                            String applicantName = sc.nextLine(); //reads string
                            RegisteredApplications s = new RegisteredApplications(applicantName);
                            cpu.Attach(s);
                            System.out.println("Application " + applicantName + " registered successfully");
                            break;
                        case "3":
                            System.out.println("Please enter the application name: ");
                            String applicantNameRemove = sc.nextLine(); //reads string
                            RegisteredApplications x = new RegisteredApplications(applicantNameRemove);
                            cpu.Detach(x);
                            System.out.println("Application " + applicantNameRemove + " removed successfully");
                            break;
                    }
                    break;
                case "3":
                    System.out.println("ID DEVICE Commands");
                    System.out.println("1- RESET - Resets the IODevices");
                    System.out.println("2- ADD NOTIFIER - Notifies the registered applications");
                    System.out.println("3- REMOVE NOTIFIER - Removes the registered applications");

                    String IOAction = sc.nextLine().toUpperCase(); //reads string

                    switch (IOAction) {

                        case "1":
                            ResetDevice resetDevice;

                            OperatingSystem os = new OperatingSystem();
                            resetDevice = new IODeviceReset(IODevice, "IO Device");
                            os.Reset(resetDevice);


                            break;
                        case "2":
                            System.out.println("Please enter the application name: ");
                            String applicantName = sc.nextLine(); //reads string
                            RegisteredApplications s = new RegisteredApplications(applicantName);
                            IODevice.Attach(s);
                            System.out.println("Application " + applicantName + " registered successfully");
                            break;
                        case "3":
                            System.out.println("Please enter the application name: ");
                            String applicantNameRemove = sc.nextLine(); //reads string
                            RegisteredApplications x = new RegisteredApplications(applicantNameRemove);
                            IODevice.Detach(x);
                            System.out.println("Application " + applicantNameRemove + " removed successfully");
                            break;
                    }
                    break;
                case "4":
                    System.out.println("MACRO Commands");
                    System.out.println("1- RESETALL - Resets all the devices");
                    System.out.println("2- ADD NOTIFIER - Notifies the registered applications");
                    System.out.println("3- REMOVE NOTIFIER - Removes the registered applications");
                    String GenericReset = sc.nextLine().toUpperCase(); //reads string

                    switch (GenericReset) {
                        case "1":
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
                        case "2":
                            System.out.println("ADDALL - Notifies the registered ALL applications");
                            break;
                        case "3":
                            System.out.println("REMOVEALL - Removes the registered ALL applications");
                    }
                    break;
                default:
                    System.out.println("Please enter HardDisk, CPU or IODevices");
                    break;
            }
        }
    }
}
