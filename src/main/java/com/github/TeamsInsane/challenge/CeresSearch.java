package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;

import java.util.HashMap;
import java.util.Map;

public class CeresSearch implements AdventDay {

    @PartLoader
    public void solvePart1() {
        char[][] puzzle = readAsCharArray("input4.txt");

        int solutionCount = 0;
        for (int indexY = 0; indexY < puzzle.length; indexY++) {
            for (int indexX = 0; indexX < puzzle[indexY].length; indexX++) {
                if (puzzle[indexY][indexX] == 'X') {
                    for (DirectionForXMAS directionForXMAS : DirectionForXMAS.values()) {
                        String word = getWord(puzzle, directionForXMAS, indexX, indexY);

                        if (word.equals("XMAS")) {
                            solutionCount++;
                        }
                    }
                }
            }
        }

        System.out.println("Result part 1: " + solutionCount);
    }

    public String getWord(char[][] puzzle, DirectionForXMAS directionForXMAS, int indexX, int indexY) {
        StringBuilder word = new StringBuilder(String.valueOf(puzzle[indexY][indexX]));

        try {
            for (int count = 1; count <= 3; count++) {
                switch (directionForXMAS) {
                    case UP -> indexY--;
                    case DOWN -> indexY++;
                    case LEFT -> indexX--;
                    case RIGHT -> indexX++;
                    case RIGHT_UP -> {
                        indexX++;
                        indexY--;
                    }
                    case RIGHT_DOWN -> {
                        indexX++;
                        indexY++;
                    }
                    case LEFT_UP -> {
                        indexX--;
                        indexY--;
                    }
                    case LEFT_DOWN -> {
                        indexX--;
                        indexY++;
                    }
                }

                word.append(puzzle[indexY][indexX]);
            }
        } catch (IndexOutOfBoundsException e) {
            return "";
        }

        return word.toString();
    }

    @PartLoader(part = 2)
    public void solvePart2() {
        char[][] puzzle = readAsCharArray("input4.txt");
        HashMap<Position, Integer> positions = new HashMap<>();

        for (int indexY = 0; indexY < puzzle.length; indexY++) {
            for (int indexX = 0; indexX < puzzle[indexY].length; indexX++) {
                for (DirectionForXMAS directionForXMAS : new DirectionForXMAS[]{DirectionForXMAS.LEFT_UP, DirectionForXMAS.LEFT_DOWN, DirectionForXMAS.RIGHT_UP, DirectionForXMAS.RIGHT_DOWN}) {
                    if (puzzle[indexY][indexX] == 'M') {
                        Position MASPosition = getPosition(puzzle, directionForXMAS, indexX, indexY);

                        if (MASPosition != null) {
                            positions.put(MASPosition, positions.getOrDefault(MASPosition, 0) + 1);
                        }

                    }
                }
            }
        }

        int solutionCount = 0;
        for (Map.Entry<Position, Integer> entry : positions.entrySet()) {
            if (entry.getValue() == 2) {
                solutionCount++;
            }
        }

        System.out.println("Result part 2: " + solutionCount);
    }

    public Position getPosition(char[][] puzzle, DirectionForXMAS directionForXMAS, int indexX, int indexY) {
        StringBuilder word = new StringBuilder(String.valueOf(puzzle[indexY][indexX]));
        Position savePotion = null;

        try {
            for (int count = 1; count <= 2; count++) {
                switch (directionForXMAS) {
                    case RIGHT_UP -> {
                        indexX++;
                        indexY--;
                    }
                    case RIGHT_DOWN -> {
                        indexX++;
                        indexY++;
                    }
                    case LEFT_UP -> {
                        indexX--;
                        indexY--;
                    }
                    case LEFT_DOWN -> {
                        indexX--;
                        indexY++;
                    }
                }

                char character = puzzle[indexY][indexX];
                if (character == 'A') {
                    savePotion = new Position(indexX, indexY);
                }

                word.append(puzzle[indexY][indexX]);
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        if (word.toString().equals("MAS")) {
            return savePotion;
        }

        return null;
    }

    @Override
    public int day() {
        return 4;
    }
}

enum DirectionForXMAS {
    LEFT,
    RIGHT,
    DOWN,
    UP,
    RIGHT_DOWN,
    RIGHT_UP,
    LEFT_DOWN,
    LEFT_UP
}

record Position (int indexX, int indexY) {}
