import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankInter extends Remote {
    void createAccount(String name) throws RemoteException;
    void deposit(String name, double amount) throws RemoteException;
    void withdraw(String name, double amount) throws RemoteException;
    double getBalance(String name) throws RemoteException;
}
