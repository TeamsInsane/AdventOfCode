package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;


public class SecretEntrance implements AdventDay {
    private final int MAX_VALUE = 99;
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

            if (startValue == 0) {
                solutionCount++;
            }
        }

        System.out.println("Solution for part 1: " + solutionCount);
    }

    @Override
    public int day() {
        return 1;
    }
}
