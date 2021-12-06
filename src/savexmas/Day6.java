package savexmas;

import java.util.*;
import java.util.stream.Collectors;

public class Day6 extends AoCDay {

    public static void main(String[] args) {
        new Day6();
    }

    @Override
    public long part1(List<String> input) {
        return getFishCountAfterSimulation(input, 80);
    }

    @Override
    public long part2(List<String> input) {
        return getFishCountAfterSimulation(input, 256);
    }

    private long getFishCountAfterSimulation(List<String> input, int days) {
        Map<Integer, Long> fish = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toMap(i -> i, i -> 1L, Long::sum));

        for (int i = 0; i < days; i++) {
            long newFish = fish.getOrDefault(0, 0L);
            for (int j = 0; j < 8; j++) {
                fish.put(j, fish.getOrDefault(j + 1, 0L));
            }
            fish.put(6, fish.getOrDefault(6, 0L) + newFish);
            fish.put(8, newFish);
        }
        return fish.values().stream().mapToLong(aLong -> aLong).sum();
    }

}
