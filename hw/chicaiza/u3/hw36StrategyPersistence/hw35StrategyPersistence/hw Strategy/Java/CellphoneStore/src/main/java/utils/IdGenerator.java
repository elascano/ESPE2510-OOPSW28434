package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IdGenerator {

    private static int counter = 0;

    
    public static String previewId() {
        LocalDate today = LocalDate.now();
        String datePart = today.format(DateTimeFormatter.ofPattern("yyyyMM"));
        String numberPart = String.format("%02d", counter);
        return datePart + numberPart;
    }

   
    public static String generateId() {
        if (counter > 99) {
            throw new IllegalStateException("Limit reached");
        }

        LocalDate today = LocalDate.now();
        String datePart = today.format(DateTimeFormatter.ofPattern("yyyyMM"));
        String numberPart = String.format("%02d", counter);

        counter++; 

        return datePart + numberPart;
    }
}
