import java.rmi.Naming;
import java.util.Scanner;

public class BankClient {
    public static void main(String[] args) {
        try {
            BankInter bank = (BankInter) Naming.lookup("rmi://localhost/BankService");
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. Create Account\n2. Deposit\n3. Withdraw\n4. Balance\n5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); 

                if (choice == 5) break;

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                switch (choice) {
                    case 1:
                        bank.createAccount(name);
                        System.out.println("Account Created.");
                        break;
                    case 2:
                        System.out.print("Enter amount: ");
                        bank.deposit(name, sc.nextDouble());
                        System.out.println("Deposited.");
                        break;
                    case 3:
                        System.out.print("Enter amount: ");
                        bank.withdraw(name, sc.nextDouble());
                        System.out.println("Withdrawn.");
                        break;
                    case 4:
                        System.out.println("Balance: " + bank.getBalance(name));
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

