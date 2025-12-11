package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;

public class PrintingDepartment implements AdventDay {
    @PartLoader
    public void solvePart1() {
        int solutionCount = 0;
        char[][] input = readAsCharArray("input4.txt");

        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
                if (input[row][col] != '@') {
                    continue;
                }

                int count = -1;

                for (int rowAdjacent = Math.max(row - 1, 0); rowAdjacent <= Math.min(row + 1, input.length - 1); rowAdjacent++) {
                    for (int colAdjacent = Math.max(col - 1, 0); colAdjacent <= Math.min(col + 1, input[row].length - 1); colAdjacent++) {
                        if (input[rowAdjacent][colAdjacent] == '@') {
                            count++;
                        }
                    }
                }

                if (count < 4) {
                    solutionCount++;
                }
            }
        }

        System.out.println("Day 4 part 1 solution: " + solutionCount);
    }

    @PartLoader
    public void solvePart2() {
        int solutionCount = 0;
        char[][] input = readAsCharArray("input4.txt");

        int iterationCount;
        do {
            iterationCount = 0;

            for (int row = 0; row < input.length; row++) {
                for (int col = 0; col < input[row].length; col++) {
                    if (input[row][col] != '@') {
                        continue;
                    }

                    int count = -1;

                    for (int rowAdjacent = Math.max(row - 1, 0); rowAdjacent <= Math.min(row + 1, input.length - 1); rowAdjacent++) {
                        for (int colAdjacent = Math.max(col - 1, 0); colAdjacent <= Math.min(col + 1, input[row].length - 1); colAdjacent++) {
                            if (input[rowAdjacent][colAdjacent] == '@') {
                                count++;
                            }
                        }
                    }

                    if (count < 4) {
                        iterationCount++;
                        input[row][col] = 'x';
                    }
                }
            }


            solutionCount += iterationCount;
        } while (iterationCount > 0);

        System.out.println("Day 4 part 2 solution: " + solutionCount);
    }


    @Override
    public int day() {
        return 4;
    }
}