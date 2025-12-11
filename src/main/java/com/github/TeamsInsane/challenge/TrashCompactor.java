package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;

import java.util.Arrays;
import java.util.List;

public class TrashCompactor implements AdventDay {
    @PartLoader
    public void solvePart1() {
        List<String> lines = readLines("input6.txt").stream().map(s -> s.replaceAll("\\s+", " ").trim()).toList();
        String[] operations = lines.getLast().split(" ");
        long[] numbers =  Arrays.stream(lines.getFirst().split(" ")).mapToLong(Long::parseLong).toArray();

        for (int lineIndex = 1; lineIndex < lines.size() - 1; lineIndex++) {
            int[] lineNumbers = Arrays.stream(lines.get(lineIndex).split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int numberIndex = 0; numberIndex < lineNumbers.length; numberIndex++) {

                switch (operations[numberIndex]) {
                    case "+":
                        numbers[numberIndex] += lineNumbers[numberIndex];
                        break;
                    case "*":
                        numbers[numberIndex] *= lineNumbers[numberIndex];
                        break;
                }
            }
        }

        long solutionCount = Arrays.stream(numbers).sum();
        System.out.println("Day 6 part 1 solution: " + solutionCount);
    }

    @Override
    public int day() {
        return 6;
    }
}
