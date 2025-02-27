import ArithmeticModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

public class Reg {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            ArithmeticImp arithmeticImpl = new ArithmeticImp();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(arithmeticImpl);
            Arithmetic href = ArithmeticHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            ncRef.rebind(ncRef.to_name("ArithmeticService"), href);

            System.out.println("Arithmetic Server Ready...");

            orb.run(); // Keep server running

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
