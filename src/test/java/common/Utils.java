package common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static LocalDate getDateInFuture(int weeksToAdd){
        return LocalDate.now().plusWeeks(weeksToAdd);
    }

    public static String getDateAsString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}