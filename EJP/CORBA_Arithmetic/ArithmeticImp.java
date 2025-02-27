public class ArithmeticImp extends ArithmeticPOA {
    public float add(float a, float b) {
        return a + b;
    }

    public float sub(float a, float b) {
        return a - b;
    }

    public float mul(float a, float b) {
        return a * b;
    }

    public float div(float a, float b) {
        if (b == 0) {
            System.out.println("Error: Division by zero attempted.");
            return Float.POSITIVE_INFINITY; // OR throw new ArithmeticException("Division by zero!");
        }
        return a / b;
    }
}
