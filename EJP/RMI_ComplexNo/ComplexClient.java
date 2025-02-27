
import java.rmi.Naming;
import java.io.*;

public class ComplexClient {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x, y, z, w, ans1, ans2;
        
        System.out.println("Enter 1st complex number:");
        x = Integer.parseInt(br.readLine());
        y = Integer.parseInt(br.readLine());

        System.out.println("Enter 2nd complex number:");
        z = Integer.parseInt(br.readLine());
        w = Integer.parseInt(br.readLine());

        try {
            ComplexInter obj = (ComplexInter) Naming.lookup("rmi://localhost/com");
            ans1 = obj.add1(x, z);
            ans2 = obj.add2(y, w);
            System.out.println("Sum = " + ans1 + " + i" + ans2);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
