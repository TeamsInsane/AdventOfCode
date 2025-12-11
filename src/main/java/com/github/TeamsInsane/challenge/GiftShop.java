package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;

import java.util.ArrayList;

public class GiftShop implements AdventDay {
    @PartLoader
    public void solvePart1() {
        long solutionCount = 0;

        for (String line : readLines("input2.txt")) {
            String[] splitLine = line.split(",");

            for (String rangeLine : splitLine) {
                String[] range = rangeLine.split("-");
                if (range[0].length() == range[1].length()) {
                    solutionCount += calculateValidID(range[0], range[1]);
                    continue;
                }

                while (range[0].length() < range[1].length()) {
                    String secondRange = "9".repeat(range[0].length());
                    solutionCount += calculateValidID(range[0], secondRange);

                    range[0] = "1" + "0".repeat(range[0].length());
                }

                solutionCount += calculateValidID(range[0], range[1]);
            }
        }

        System.out.println("Day 2 part 1 solution: " + solutionCount);
    }

    public long calculateValidID(String bottomRange, String topRange) {
        if (bottomRange.length() % 2 != 0) {
            return 0;
        }

        long firstHalfBottom = Long.parseLong(bottomRange.substring(0, bottomRange.length() / 2));
        long firstHalfTop = Long.parseLong(topRange.substring(0, bottomRange.length() / 2));

        long count = 0;

        long firstTestValue = Long.parseLong("" + firstHalfBottom + firstHalfBottom);
        long secondTestValue = Long.parseLong("" + firstHalfTop + firstHalfTop);

        if (firstTestValue >= Long.parseLong(bottomRange) && firstTestValue <= Long.parseLong(topRange)) {
            count += firstTestValue;
        }

        if (secondTestValue != firstTestValue && secondTestValue <= Long.parseLong(topRange)) {
            count += secondTestValue;
        }

        firstHalfBottom++;

        for (; firstHalfBottom < firstHalfTop; firstHalfBottom++) {
            count += Long.parseLong("" + firstHalfBottom + firstHalfBottom);
        }

        return count;
    }

    @PartLoader
    public void solvePart2() {
        long solutionCount = 0;

        for (String line : readLines("input2.txt")) {
            String[] splitLine = line.split(",");

            for (String rangeLine : splitLine) {
                String[] range = rangeLine.split("-");

                long bottomRange = Long.parseLong(range[0]);
                long topRange = Long.parseLong(range[1]);

                while (bottomRange < topRange) {
                    String value = String.valueOf(bottomRange);
                    ArrayList<Integer> lps = computeLPSArray(value);

                    int n = value.length();
                    int last = lps.get(n - 1);
                    int period = n - last;

                    if (n % period == 0 && n / period >= 2) {
                        solutionCount += bottomRange;
                    }

                    bottomRange++;
                }
            }
        }

        System.out.println("Day 2 part 2 solution: " + solutionCount);
    }

    public ArrayList<Integer> computeLPSArray(String pattern) {
        int n = pattern.length();
        ArrayList<Integer> lps = new ArrayList<>();

        for (int k = 0; k < n; k++) {
            lps.add(0);
        }

        int len = 0;
        int i = 1;

        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps.set(i, len);
                i++;
            } else {
                if (len != 0) {
                    len = lps.get(len - 1);
                } else {
                    lps.set(i, 0);
                    i++;
                }
            }
        }

        return lps;
    }

    @Override
    public int day() {
        return 2;
    }
}
