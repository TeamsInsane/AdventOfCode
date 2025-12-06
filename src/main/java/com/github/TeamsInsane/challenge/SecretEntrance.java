package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;


public class SecretEntrance implements AdventDay {
    private final int MAX_VALUE = 99;
    private final int MIN_VALUE = 0;
    private final int START_VALUE = 50;

    @PartLoader
    public void solvePart1() {
        int startValue = START_VALUE;
        int solutionCount = 0;

        for (String lines : readLines("input1.txt")) {
            char direction = lines.charAt(0);
            int value = Integer.parseInt(lines.substring(1));

            if (direction == 'L') {
                value = -value;
            }

            startValue = Math.floorMod(startValue + value, MAX_VALUE + 1);

            if (startValue == MIN_VALUE) {
                solutionCount++;
            }
        }

        System.out.println("Solution for part 1: " + solutionCount);
    }

    @PartLoader
    public void solvePart2() {
        int startValue = START_VALUE;
        int solutionCount = 0;

        for (String lines : readLines("input1.txt")) {
            char direction = lines.charAt(0);
            int value = Integer.parseInt(lines.substring(1));

            int addValue = 1;

            if (direction == 'L') {
                addValue = -addValue;
            }

            for (int i = 0; i < value; i++) {
                if (startValue == MAX_VALUE && addValue > 0) {
                    startValue = MIN_VALUE;
                } else if (startValue == MIN_VALUE && addValue < 0) {
                    startValue = MAX_VALUE;
                } else {
                    startValue += addValue;
                }

                if (startValue == MIN_VALUE) {
                    solutionCount++;
                }
            }



        }

        System.out.println("Solution for part 2: " + solutionCount);
    }

    @Override
    public int day() {
        return 1;
    }
}
