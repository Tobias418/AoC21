package savexmas;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Day7 extends AoCDay {

    public static void main(String[] args) {
        new Day7();
    }

    @Override
    public long part1(List<String> input) {
        List<Integer> crabs = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        return crabs.stream().map(crab -> getFuelCostForPosition(crabs, crab, this::getCrabFuel)).min(Long::compare).get();
    }

    private long getCrabFuel(int crab, int pos) {
        return Math.abs(crab - pos);
    }

    private long getCrabFuelTheSequel(int crab, int pos) {
        long n = getCrabFuel(crab, pos);
        return (n * (n + 1)) / 2;
    }

    private long getFuelCostForPosition(List<Integer> crabs, int pos, BiFunction<Integer, Integer, Long> fuelFunction) {
        return crabs.stream().mapToLong(crab -> fuelFunction.apply(crab, pos)).sum();
    }

    @Override
    public long part2(List<String> input) {
        List<Integer> crabs = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        return crabs.stream().map(crab -> getFuelCostForPosition(crabs, crab, this::getCrabFuelTheSequel)).min(Long::compare).get();
    }

}
