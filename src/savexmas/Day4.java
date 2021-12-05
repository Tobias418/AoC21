package savexmas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends AoCDay {

    public static void main(String[] args) {
        new Day4();
    }

    @Override
    public long part1(List<String> input) {
        List<Integer> numbers = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<Board> boards = new ArrayList<>();
        for (int i = 2; i < input.size(); i += 6) {
            int[][] boardNumbers = new int[5][5];

            for (int j = 0; j < 5; j++) {
                boardNumbers[j] = parseLine(input, i + j);
            }
            boards.add(new Board(boardNumbers));
        }

        for (int number : numbers) {
            for (Board board : boards) {
                board.markNumber(number);
                if (board.hasWon()) {
                    return (long) board.getUnmarkedSum() * number;
                }
            }
        }

        return 0;
    }

    @Override
    public long part2(List<String> input) {
        List<Integer> numbers = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<Board> boards = new ArrayList<>();
        for (int i = 2; i < input.size(); i += 6) {
            int[][] boardNumbers = new int[5][5];

            for (int j = 0; j < 5; j++) {
                boardNumbers[j] = parseLine(input, i + j);
            }
            boards.add(new Board(boardNumbers));
        }

        Board lastWon = boards.get(0);
        int lastNumber = 0;
        for (int number : numbers) {
            for (Board board : boards) {
                if (!board.hasAlreadyWonM8){
                    board.markNumber(number);
                    if (board.hasWon()) {
                        lastWon = board;
                        lastNumber = number;
                    }
                }
            }
        }

        return (long) lastWon.getUnmarkedSum() * lastNumber;
    }

    private static int[] parseLine(List<String> input, int line) {
        String str = input.get(line);
        if (str.startsWith(" "))
            str = str.substring(1);
        String[] tokens = str.split("[ ]+", 0);
        return new int[]{
                Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]),
                Integer.parseInt(tokens[2]),
                Integer.parseInt(tokens[3]),
                Integer.parseInt(tokens[4])
        };
    }

    private static class Board {
        public int[][] numbers;
        public boolean[][] marks;
        public boolean hasAlreadyWonM8 = false;

        public Board(int[][] numbers) {
            this.numbers = numbers;
            this.marks = new boolean[5][5];
        }

        public void markNumber(int number) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (numbers[i][j] == number)
                        marks[i][j] = true;
                }
            }
        }

        public boolean hasWon() {
            for (int i = 0; i < 5; i++) {
                boolean allChecked = true;
                for (int j = 0; j < 5; j++) {
                    allChecked = allChecked && marks[i][j];
                }
                if (allChecked) {
                    hasAlreadyWonM8 = true;
                    return true;
                }
            }

            for (int j = 0; j < 5; j++) {
                boolean allChecked = true;
                for (int i = 0; i < 5; i++) {
                    allChecked = allChecked && marks[i][j];
                }
                if (allChecked) {
                    hasAlreadyWonM8 = true;
                    return true;
                }
            }

            return false;
        }

        public int getUnmarkedSum() {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!marks[i][j])
                        sum += numbers[i][j];
                }
            }
            return sum;
        }
    }

}
