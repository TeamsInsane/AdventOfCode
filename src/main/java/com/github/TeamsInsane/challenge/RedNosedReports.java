package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;

import java.util.Arrays;
import java.util.List;

public class RedNosedReports implements AdventDay {

    @PartLoader
    public void solvePart1() {
        int safeCount = 0;

        for (String line : readLines("input2.txt")) {
            List<Integer> arrayOfData = Arrays
                    .stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();

            Direction direction = arrayOfData.get(0) > arrayOfData.get(1) ? Direction.DECREASING :
                    arrayOfData.get(0) < arrayOfData.get(1) ? Direction.INCREASING : Direction.NONE;

            if (direction == Direction.NONE) {
                continue;
            }

            boolean safe = true;
            for (int i = 0; i < arrayOfData.size() - 1; i++) {
                int firstElement = arrayOfData.get(i);
                int secondElement = arrayOfData.get(i + 1);

                switch (direction) {
                    case Direction.DECREASING -> {
                        if (firstElement < secondElement) {
                            safe = false;
                        } else {
                            safe = checkDifference(firstElement, secondElement);
                        }
                    }

                    case Direction.INCREASING -> {
                        if (secondElement < firstElement) {
                            safe = false;
                        } else {
                            safe = checkDifference(secondElement, firstElement);
                        }
                    }
                }

                if (!safe) {
                    break;
                }
            }

            if (safe) {
                safeCount++;
            }
        }

        System.out.println("Part 1 solution: " + safeCount);
    }

    private boolean checkDifference(int firstValue, int secondValue) {
        int difference = firstValue - secondValue;
        return !(difference < 1 || difference > 3);
    }

    @Override
    public int day() {
        return 2;
    }
}

enum Direction {
    INCREASING,
    DECREASING,
    NONE
}