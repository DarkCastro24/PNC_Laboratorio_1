package Utils;

import java.util.Random;

public class CodeUtils {
    private static final Random random = new Random();

    public static String generateDoctorCode() {
        return "ZNH-" + generateNumber() + generateLetter() + generateNumber() + "-MD-" + generateLetter() + generateNumber();
    }

    private static String generateLetter() {
        return String.valueOf((char) ('A' + random.nextInt(26)));
    }

    private static String generateNumber() {
        return String.valueOf(random.nextInt(10));
    }

    public static String generateAppointmentCode() {
        return "CIT-" + generateNumbers(3) + "-" + generateLetters(3);
    }

    public static String generateRandomHour() {
        int hour = 8 + random.nextInt(9); // 08 to 16
        int minutes = random.nextInt(60);
        return String.format("%02d:%02d", hour, minutes);
    }

    private static String generateNumbers(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static String generateLetters(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char letter = (char) ('A' + random.nextInt(26));
            sb.append(letter);
        }
        return sb.toString();
    }
}

