package Project2;

//============================================================================
//Name        : AdapterPattern.cpp
//
//The classes and/or objects participating in this pattern are:
// 1. Target   (API)
//       Defines the domain-specific interface that Client (VCR)  uses.
// 2. Adapter   (Compiler)
//       Adapts the interface Adaptee to the Target interface.
// 3. Adaptee   (Linux_File, BSD_File, NT_File)
//       Defines an existing interface that needs adapting.
// 4. Client   (Adapter)
//       Collaborates with objects conforming to the Target (EURSocket)
//       interface.
//
//This uses Objects Adapter.
//============================================================================

//This the  "Target" class.
//North America Socket. Our
//device is manufactured for to be
//used with a North American Socket.

interface API {
    int fprintf (File handle, String str);
}
//This is the "Adaptee" class. Continential Europe Socket
//The device will be used in Europe which has different sockets.


// This is the "Adapter" class. ConnectorAdapterNAtoEUR.
// We need a connector so our device works.

class Compiler implements API {
    public int fprintf (File handle, String str){
        return -1;
    }

    Compiler (LINUX_File adapteel, BSD_File adapteeb, NT_File adapteen) {
        _adapteeL = adapteel;
        _adapteeB = adapteeb;
        _adapteeN = adapteen;
    }
    private LINUX_File _adapteeL;
    private BSD_File _adapteeB;
    private NT_File _adapteeN;

}


// This is our client which will be using
// the Target (NASocket) Interface.

public class Adapter {
    public static void main(String[] args) {

        // Create adapter and place a request
        API file = new Compiler (new LINUX_File(), new BSD_File(), new NT_File());

    }
}

