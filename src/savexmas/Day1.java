package savexmas;

import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends AoCDay {

    public static void main(String[] args) {
        new Day1();
    }

    @Override
    public long part1(List<String> input) {
        long increaseCounter = 0;
        List<Long> depths = input.stream().map(Long::parseLong).collect(Collectors.toList());
        for (int i = 0; i < depths.size() - 1; i++) {
            increaseCounter += depths.get(i) < depths.get(i + 1) ? 1 : 0;
        }
        return increaseCounter;
    }

    @Override
    public long part2(List<String> input) {
        long increaseCounter = 0;
        List<Long> depths = input.stream().map(Long::parseLong).collect(Collectors.toList());
        for (int i = 0; i < depths.size() - 3; i++) {
            increaseCounter += depths.get(i) < depths.get(i + 3) ? 1 : 0; // credits to davids big brain
        }
        return increaseCounter;
    }

}
