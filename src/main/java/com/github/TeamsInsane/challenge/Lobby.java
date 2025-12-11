package com.github.TeamsInsane.challenge;

import com.github.TeamsInsane.annotation.PartLoader;
import com.github.TeamsInsane.interfaces.AdventDay;

import java.util.Arrays;

public class Lobby implements AdventDay {
    @PartLoader
    public void solvePart1() {
        int solutionValue = 0;

        for (char bank[] : readAsCharArray("input3.txt")) {
            int firstBattery = -1;
            int secondBattery = -1;

            for (int batteryIndex = 0; batteryIndex < bank.length; batteryIndex++) {
                int batteryValue = Integer.parseInt(bank[batteryIndex] + "");
                if (batteryValue > firstBattery && batteryIndex != bank.length - 1) {
                    firstBattery = batteryValue;
                    secondBattery = -1;
                } else if (batteryValue > secondBattery) {
                    secondBattery = batteryValue;
                }
            }

            solutionValue += firstBattery * 10 + secondBattery;
        }

        System.out.println(solutionValue);
    }

    @PartLoader
    public void solvePart2() {
        long solutionValue = 0;

        for (char bank[] : readAsCharArray("input3.txt")) {
            int[] savedBatteries = new int[12];

            Arrays.fill(savedBatteries, -1);

            for (int batteryIndex = 0; batteryIndex < bank.length; batteryIndex++) {
                int batteryValue = Integer.parseInt(bank[batteryIndex] + "");
                for (int savedBatteryIndex = 0; savedBatteryIndex < savedBatteries.length; savedBatteryIndex++) {
                    if (batteryValue > savedBatteries[savedBatteryIndex] && (savedBatteries.length - savedBatteryIndex - 1) <= (bank.length - batteryIndex - 1)) {
                        savedBatteries[savedBatteryIndex] = batteryValue;

                        for (int i = savedBatteryIndex + 1; i < savedBatteries.length; i++) {
                            savedBatteries[i] = -1;
                        }

                        break;
                    }
                }
            }

            for (int i = 0; i < savedBatteries.length; i++) {
                solutionValue += (long) (savedBatteries[i] * Math.pow(10, savedBatteries.length - i - 1));
            }
        }

        System.out.println(solutionValue);
    }

    @Override
    public int day() {
        return 3;
    }
}
