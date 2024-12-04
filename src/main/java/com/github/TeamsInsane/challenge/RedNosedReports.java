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
            List<Integer> arrayOfData = new java.util.ArrayList<>(Arrays
                    .stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList());


            if (isSafe(arrayOfData)) {
                safeCount++;
            }
        }

        System.out.println("Part 2 solution: " + safeCount);
    }

    @PartLoader
    public void solvePart2() {
        int safeCount = 0;

        for (String line : readLines("input2.txt")) {
            List<Integer> arrayOfData = new java.util.ArrayList<>(Arrays
                    .stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList());


            if (isSafe(arrayOfData)) {
                safeCount++;
            } else {
                for (int removeIndex = 0; removeIndex < arrayOfData.size(); removeIndex++) {
                    int value = arrayOfData.get(removeIndex);
                    arrayOfData.remove(removeIndex);

                    if (isSafe(arrayOfData)) {
                        safeCount++;
                        break;
                    } else {
                        arrayOfData.add(removeIndex, value);
                    }
                }
            }
        }

        System.out.println("Part 2 solution: " + safeCount);
    }

    public boolean isSafe(List<Integer> arrayOfData) {
        Direction direction = arrayOfData.get(0) > arrayOfData.get(1) ? Direction.DECREASING :
            arrayOfData.get(0) < arrayOfData.get(1) ? Direction.INCREASING : Direction.NONE;

        if (direction == Direction.NONE) {
            return false;
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
                return false;
            }
        }

        return true;
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