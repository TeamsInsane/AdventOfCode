package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;

import java.util.ArrayList;
import java.util.List;

class Range {
    long min;
    long max;

    public Range(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public boolean isInRange(long value) {
        return min <= value && value <= max;
    }
}

public class Cafeteria implements AdventDay {
    @PartLoader
    public void solvePart1() {
        int solutionCount = 0;
        List<Range> ranges = new ArrayList<>();
        boolean fillingRanges = true;

        for (String line : readLines("input5.txt")) {
            if (line.isEmpty()) {
                fillingRanges = false;
                continue;
            }

            if (fillingRanges) {
                fillRange(ranges, line);
                continue;
            }

            solutionCount += isFoodFresh(ranges, line) ? 1 : 0;
        }

        System.out.println("Day 5 part 1 solution: " + solutionCount);
    }

    @PartLoader
    public void solvePart2() {
        List<Range> ranges = new ArrayList<>();

        for (String line : readLines("input5.txt")) {
            if (line.isEmpty()) {
                break;
            }

            fillRange(ranges, line);
        }

        long solutionCount = numberOfFreshFood(ranges);

        System.out.println("Day 5 part 2 solution: " + solutionCount);
    }

    public long numberOfFreshFood(List<Range> ranges) {
        long sum = 0;

        for (Range range : ranges) {
            sum += range.max - range.min + 1;
        }

        return sum;
    }

    public void fillRange(List<Range> ranges, String line) {
        String[] splitRange = line.split("-");
        long min = Long.parseLong(splitRange[0]);
        long max = Long.parseLong(splitRange[1]);

        List<Range> rangesToRemove = new ArrayList<>();

        for (Range range : ranges) {
            boolean minInRange = range.isInRange(min);
            boolean maxInRange = range.isInRange(max);

            if (minInRange && maxInRange) {
                return;
            } else if (minInRange) {
                min = range.max + 1;
            } else if (maxInRange) {
                max = range.min - 1;
            } else if (range.min >= min && range.max <= max) {
                rangesToRemove.add(range);
            }
        }

        ranges.removeAll(rangesToRemove);

        Range newRange = new Range(min, max);
        ranges.add(newRange);
    }

    public boolean isFoodFresh(List<Range> ranges, String line) {
        long value = Long.parseLong(line);

        for (Range range : ranges) {
            if (range.isInRange(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int day() {
        return 5;
    }
}