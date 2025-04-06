package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static boolean isToday(String date) {
        try {
            Date inputDate = formatter.parse(date);
            Calendar inputCal = Calendar.getInstance();
            inputCal.setTime(inputDate);

            Calendar today = Calendar.getInstance();

            return inputCal.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                    && inputCal.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                    && inputCal.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            return false;
        }
    }

    public static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static boolean isValidDateFormat(String date) {
        try {
            formatter.setLenient(false);
            formatter.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static int calculateAge(String birthDate) {
        try {
            Date birth = formatter.parse(birthDate);
            Calendar birthCal = Calendar.getInstance();
            birthCal.setTime(birth);
            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
            if (today.get(Calendar.MONTH) < birthCal.get(Calendar.MONTH) ||
                    (today.get(Calendar.MONTH) == birthCal.get(Calendar.MONTH) &&
                            today.get(Calendar.DAY_OF_MONTH) < birthCal.get(Calendar.DAY_OF_MONTH))) {
                age--;
            }
            return age;
        } catch (ParseException e) {
            return 0;
        }
    }
}
