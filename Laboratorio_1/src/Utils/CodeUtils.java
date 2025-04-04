package Utils;

import java.util.Random;

public class CodeUtils {
    public static String generarCodigoDoctor() {
        Random rand = new Random();
        int num1 = rand.nextInt(10);  // Números entre 0 y 9
        int num2 = rand.nextInt(10);
        char letra1 = (char) ('A' + rand.nextInt(26)); // Letra A-Z
        char letra2 = (char) ('A' + rand.nextInt(26));

        return String.format("ZNH-%d%c%d-MD-%c%d", num1, letra1, num2, letra2, num1);
    }
}

