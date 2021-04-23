package Codility;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kérem adja meg a konvertálandó másodpercet: ");
        int input = sc.nextInt();
        System.out.printf("\tEredmény: %s", formatDuration(input));
    }

    public static String formatDuration(int seconds) {
        //validates for exceptions
        if (seconds == 0)
            return "now";
        else if (seconds < 0)
            throw new IllegalArgumentException("seconds cant be zero");
        else if (seconds < 60)
            return seconds + " second";

        //time variables initialised (one unit)
        final Map<String, Integer> map=new LinkedHashMap<String, Integer>(){{
            put("year", 31536000);
            put("day", 86400);
            put("hour", 3600);
            put("minute", 60);
            put("second", 1);
        }};

        ArrayList<String> words = new ArrayList<>();

        //load the words list
        for (Map.Entry<String, Integer> time : map.entrySet()) {
            if (seconds/time.getValue() > 1) words.add((seconds/time.getValue()) + " " + time.getKey() + "s");
            else if (seconds/time.getValue() > 0) words.add((seconds/time.getValue()) + " " + time.getKey());
            seconds = seconds % time.getValue();
        }

        //create the result string from the elements
        StringBuilder result = new StringBuilder(words.get(0));
        for (int i=1; i<words.size(); i++) {
            result.append((words.size() - i == 1) ? " and " + words.get(i) : ", " + words.get(i));
        }

        return result.toString();
    }
}

