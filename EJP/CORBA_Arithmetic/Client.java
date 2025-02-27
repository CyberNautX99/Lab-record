import ArithmeticModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Arithmetic arith = ArithmeticHelper.narrow(ncRef.resolve_str("ArithmeticService"));

            BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Enter two numbers (or type 'exit' to quit):");
                
                String input1 = rd.readLine();
                if (input1.equalsIgnoreCase("exit")) break;
                
                String input2 = rd.readLine();
                if (input2.equalsIgnoreCase("exit")) break;

                try {
                    float a = Float.parseFloat(input1);
                    float b = Float.parseFloat(input2);

                    System.out.println("Sum = " + arith.add(a, b));
                    System.out.println("Subtract = " + arith.sub(a, b));
                    System.out.println("Product = " + arith.mul(a, b));
                    System.out.println("Division = " + arith.div(a, b));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter numeric values.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
