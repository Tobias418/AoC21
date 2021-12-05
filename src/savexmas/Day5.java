package savexmas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 extends AoCDay {

    public static void main(String[] args) {
        new Day5();
    }

    @Override
    public long part1(List<String> input) {
        List<Line> lines = input.stream().map(s -> s.split("(,)|( -> )")).map(tokens -> new Line(new Point(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])), new Point(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])))).collect(Collectors.toList());
        int width = lines.stream().map(line -> Math.max(line.a.x, line.b.x)).max(Integer::compare).get() + 1;
        int height = lines.stream().map(line -> Math.max(line.a.y, line.b.y)).max(Integer::compare).get() + 1;

        int[][] map = new int[width][height];

        for (Line line : lines) {
            markLine(map, line, false);
        }

        return Arrays.stream(map).mapToLong(ints -> Arrays.stream(ints).filter(value -> value >= 2).count()).sum();
    }

    @Override
    public long part2(List<String> input) {
        List<Line> lines = input.stream().map(s -> s.split("(,)|( -> )")).map(tokens -> new Line(new Point(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])), new Point(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])))).collect(Collectors.toList());
        int width = lines.stream().map(line -> Math.max(line.a.x, line.b.x)).max(Integer::compare).get() + 1;
        int height = lines.stream().map(line -> Math.max(line.a.y, line.b.y)).max(Integer::compare).get() + 1;

        int[][] map = new int[width][height];

        for (Line line : lines) {
            markLine(map, line, true);
        }

        return Arrays.stream(map).mapToLong(ints -> Arrays.stream(ints).filter(value -> value >= 2).count()).sum();
    }

    private static void markLine(int[][] map, Line line, boolean diagonals) {
        int xInc = Integer.compare(line.b.x - line.a.x, 0);
        int yInc = Integer.compare(line.b.y - line.a.y, 0);

        if (!diagonals && (xInc != 0) == (yInc != 0))
            return;

        int max = Math.max((line.b.x - line.a.x) * xInc, (line.b.y - line.a.y) * yInc);
        for (int i = 0; i <= max; i++)
            map[line.a.x + i * xInc][line.a.y + i * yInc]++;
    }

    private static class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Line {
        public Point a, b;

        public Line(Point a, Point b) {
            this.a = a;
            this.b = b;
        }
    }

}
