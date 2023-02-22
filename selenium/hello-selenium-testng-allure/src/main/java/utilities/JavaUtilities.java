package utilities;

import logging.Log;

import java.io.File;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JavaUtilities {
    private JavaUtilities() {
    }

    public static String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public static int randomIndexNumber(int maxNumber) {
        SecureRandom rand = new SecureRandom();
        return rand.nextInt(maxNumber);
    }

    public static String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        return dtf.format(LocalDateTime.now());
    }

    public static String getTimeStamps() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmSS");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void createDirectoryPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            try {
                testDirectory.mkdir();
                Log.info("Directory: " + path + " is created!");
            } catch (Exception ex) {
                Log.error("Failed to create directory: " + path, stackTraceToString(ex));
            }
        }
    }

    /**
     * @param str1
     * @param str2
     * @return str1 > str2 -> positive value; str1 = str2 -> 0; str1 < str2 -> negative value
     */
    public static int stringCompare(String str1, String str2) {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int) str1.charAt(i);
            int str2_ch = (int) str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }

}
