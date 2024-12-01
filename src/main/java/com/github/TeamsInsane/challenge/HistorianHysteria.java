package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistorianHysteria implements AdventDay {
    private List<Integer> leftSideList;
    private List<Integer> rightSideList;

    private void setListData() {
        leftSideList = new ArrayList<>();
        rightSideList = new ArrayList<>();

        for (String value : readLines("input1.txt")) {
            String[] values = value.split(" {3}");

            leftSideList.add(Integer.parseInt(values[0]));
            rightSideList.add(Integer.parseInt(values[1]));
        }
    }

    @PartLoader
    public void solvePart1() {
        setListData();

        leftSideList.sort(Integer::compareTo);
        rightSideList.sort(Integer::compareTo);

        int differenceResult = 0;
        for (int i = 0; i < leftSideList.size(); i++) {
            differenceResult += Math.abs(leftSideList.get(i) - rightSideList.get(i));
        }

        System.out.println("Day 1 part 1 solution: " + differenceResult);
    }

    @PartLoader(part = 2)
    public void solvePart2() {
        setListData();

        int differenceResult = 0;
        for (int firstValue : leftSideList) {
            int count = 0;
            for (int secondValue : rightSideList) {
                if (firstValue == secondValue) {
                    count++;
                }
            }

            differenceResult += firstValue * count;
        }

        System.out.printf("Day 1 part 2 solution: " + differenceResult);
    }

    @Override
    public int day() {
        return 1;
    }
}