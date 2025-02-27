
import java.rmi.Naming;

public class BankServer {
    public static void main(String[] args) {
        try {
            Naming.rebind("rmi://localhost/BankService", new BankImpl());
            System.out.println("Bank Service is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
