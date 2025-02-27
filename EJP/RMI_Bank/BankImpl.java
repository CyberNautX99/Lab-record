import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;

public class BankImpl extends UnicastRemoteObject implements BankInter {
    private HashMap<String, Double> accounts = new HashMap<>();

    public BankImpl() throws RemoteException {
        super();
    }

    public void createAccount(String name) throws RemoteException {
        accounts.putIfAbsent(name, 0.0);
    }

    public void deposit(String name, double amount) throws RemoteException {
        accounts.put(name, accounts.getOrDefault(name, 0.0) + amount);
    }

    public void withdraw(String name, double amount) throws RemoteException {
        double balance = accounts.getOrDefault(name, 0.0);
        if (balance >= amount) {
            accounts.put(name, balance - amount);
        } else {
            throw new RemoteException("Insufficient balance");
        }
    }

    public double getBalance(String name) throws RemoteException {
        return accounts.getOrDefault(name, 0.0);
    }
}
