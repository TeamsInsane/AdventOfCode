package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver implements AdventDay {

    @PartLoader
    public void solvePart1() {
        Pattern pattern = Pattern.compile("(mul\\(\\d+,\\d+\\))");
        int mulResult = 0;

        for (String line : readLines("input3.txt")) {
            Matcher matcher = pattern.matcher(line);

            List<String> listMatches = new ArrayList<>();

            while(matcher.find()) {
                String match = matcher.group().replace("mul(", "");
                match = match.replace(")", "");

                listMatches.add(match);
            }

            for(String s : listMatches) {
                String[] split = s.split(",");

                mulResult += Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
            }
        }

        System.out.println("Solution part 1: " + mulResult);
    }

    @PartLoader
    public void solvePart2() {
        Pattern pattern = Pattern.compile("(mul\\(\\d+,\\d+\\))|(don't\\(\\))|(do\\(\\))");
        int mulResult = 0;
        boolean calculate = true;

        for (String line : readLines("input3.txt")) {
            Matcher matcher = pattern.matcher(line);

            List<String> listMatches = new ArrayList<>();
            while(matcher.find()) {
                String match = matcher.group();

                if (match.equals("don't()")) {
                    calculate = false;
                } else if (match.equals("do()")) {
                    calculate = true;
                } else {
                    if (calculate) {
                        match = match.replace("mul(", "");
                        match = match.replace(")", "");

                        listMatches.add(match);
                    }
                }
            }

            for(String s : listMatches) {
                String[] split = s.split(",");

                mulResult += Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
            }
        }

        System.out.println("Solution part 2: " + mulResult);
    }

    @Override
    public int day() {
        return 3;
    }
}
