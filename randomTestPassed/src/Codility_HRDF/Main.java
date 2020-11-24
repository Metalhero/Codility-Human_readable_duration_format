package Codility_HRDF;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kérem adja meg a konvertálandó másodpercet: ");
        int input = sc.nextInt();
        if (input >= 0) {
            System.out.println("Eredmény: " + formatDuration(input));
            System.out.println(System.lineSeparator() + "\t**the program ran successfully**");
        } else
            System.out.println("Negatív számot adtál meg, ezért a kért műveletet nem lett elvégezve.");
    }

    public static String formatDuration(int seconds) {
        //validates
        if (seconds == 0)
            return "now";
        if (seconds < 60)
            return String.valueOf(seconds) + " second";

        //variables
        int oneYear = 60 * 60 * 24 * 365;
        int oneDay = 60 * 60 * 24;
        int oneHour = 60 * 60;
        int oneMinute = 60;

        int years = 0, days = 0, hours = 0, minutes = 0;


        /**calculations*/
        //years
        years = timeCounter(oneYear, seconds);
        seconds = secondCounter(seconds, years, oneYear);
        //days
        days = timeCounter(oneDay, seconds);
        seconds = secondCounter(seconds, days, oneDay);
        //hours
        hours = timeCounter(oneHour, seconds);
        seconds = secondCounter(seconds, hours, oneHour);
        //minutes
        minutes = timeCounter(oneMinute, seconds);
        seconds = secondCounter(seconds, minutes, oneMinute);

        //print the result
        boolean prev = false;
        String result = "";
        boolean lastElement = false;

        //years
        if (days + hours + minutes + seconds == 0)    //last element check
            lastElement = true;
        result += years != 0 ? printer("year", years, prev, lastElement) : "";
        if (lastElement)
            return result;


        //days
        if (hours + minutes + seconds == 0)        //last element check
            lastElement = true;
        if (!result.equals(""))              //first element check
            prev = true;
        result += days != 0 ? printer("day", days, prev, lastElement) : "";
        if (lastElement)
            return result;

        //hours
        if (minutes + seconds == 0)
            lastElement = true;
        if (!result.equals("") && !prev)
            prev = true;
        result += hours != 0 ? printer("hour", hours, prev, lastElement) : "";
        if (lastElement)
            return result;

        //minutes
        if (seconds == 0)
            lastElement = true;
        if (!result.equals("") && !prev)
            prev = true;
        result += minutes != 0 ? printer("minute", minutes, prev, lastElement) : "";
        if (lastElement)
            return result;

        //seconds
        lastElement = true;
        if (!result.equals("") && !prev)
            prev = true;
        if (seconds != 0)
            result += printer("second", seconds, prev, lastElement);

        return result;
    }


    private static int timeCounter(int timeFormat, int remainingSecond) {
        int times = 0;

        if (remainingSecond >= timeFormat) {
            times = remainingSecond / timeFormat;
        }
        return times;
    }


    private static int secondCounter(int totalSeconds, int times, int timeFormat) {
        if (times != 0)
            return totalSeconds -= times * timeFormat;

        return totalSeconds;
    }


    private static String printer(String text, int times, boolean prev, boolean last) {


        //if times is more than 1
        if (times > 1) {

            //is this the only element?
            if (last && !prev)
                return times+" "+text+"s";
            //do we have previous element/is this the last element?
            if (last)
                return " and " + times + " " + text + "s";
            else if (prev)
                return ", " + times + " " + text + "s";
            else
                return times + " " + text + "s";

            //if times is only 1
        } else {
            //is this the only element?
            if (last && !prev)
                return times+" "+text;
            //do we have previous element?
            if (last)
                return times != 0 ? " and " + times + " " + text : "";
            else if (prev)
            return times != 0 ? ", " + times + " " + text : "";
            else
            return times != 0 ? times + " " + text : "";
        }
    }
}

