import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class ComplexImpl extends UnicastRemoteObject implements ComplexInter {
    private static final long serialVersionUID = 1L; // Best practice for Serializable class

    public ComplexImpl() throws RemoteException {
        super();
    }

    public int add1(int a1, int b1) throws RemoteException {
        return a1 + b1;
    }

    public int add2(int a2, int b2) throws RemoteException {
        return a2 + b2;
    }
}
