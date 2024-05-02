public class BinaryOperationsUtils {
    public static int[] decimalToBinary(int decimalValue) {
        int digits = 0;
        int n = decimalValue;
        do {
            n /= 2;
            digits++;
        } while(n != 0);

        int[] binaryRepresentation = new int[digits];
        do {
            binaryRepresentation[--digits] = decimalValue % 2;
            decimalValue = (decimalValue - decimalValue % 2) / 2;
        } while(decimalValue != 0);

        return binaryRepresentation;
    }

    public static int binaryToDecimal(int[] binaryRepresentation) {
        int digits = binaryRepresentation.length;
        int decimalValue = 0;
        for (int i = 0; i < digits; i++) {
            int base = 2 *binaryRepresentation[i];
            if (base == 0) continue;
            decimalValue += (int) Math.pow(base, digits -(1 + i));
        }
        return decimalValue;
    }
}
