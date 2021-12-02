package savexmas;

import java.util.List;

public class Day2 extends AoCDay {
    public static void main(String[] args) {
        new Day2();
    }

    @Override
    public long part1(List<String> input) {
        long x = 0, y = 0;

        for (String s : input) {
            String[] tokens = s.split(" ");
            x += tokens[0].equals("forward") ? Long.parseLong(tokens[1]) : 0;
            y += tokens[0].equals("down") ? Long.parseLong(tokens[1]) : 0;
            y -= tokens[0].equals("up") ? Long.parseLong(tokens[1]) : 0;
        }

        return x * y;
    }

    @Override
    public long part2(List<String> input) {
        long x = 0, y = 0, aim = 0;

        for (String s : input) {
            String[] tokens = s.split(" ");
            x += tokens[0].equals("forward") ? Long.parseLong(tokens[1]) : 0;
            y += tokens[0].equals("forward") ? Long.parseLong(tokens[1]) * aim : 0;
            aim += tokens[0].equals("down") ? Long.parseLong(tokens[1]) : 0;
            aim -= tokens[0].equals("up") ? Long.parseLong(tokens[1]) : 0;
        }

        return x * y;
    }
}
