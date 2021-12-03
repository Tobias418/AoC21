package savexmas;

import java.util.ArrayList;
import java.util.List;

public class Day3 extends AoCDay {

    public static void main(String[] args) {
        new Day3();
    }

    @Override
    public long part1(List<String> input) {
        int length = input.get(0).length();
        long gamma = 0, epsilon = 0;
        for (int i = 0; i < length; i++) {
            gamma <<= 1;
            epsilon <<= 1;
            if (count(input, i, '1') >= input.size() / 2)
                gamma++;
            else
                epsilon++;
        }
        return gamma * epsilon;
    }

    // when duplicating good code you just get more good code
    @Override
    public long part2(List<String> input) {
        int length = input.get(0).length();

        // OXYGEN GENERATOR
        String startString = "";
        List<String> oxygenList = new ArrayList<>(input);
        long oxygenRating = 0;
        for (int i = 0; i < length; i++) {
            startString += count(oxygenList, i, '1') >= count(oxygenList, i, '0') ? "1" : "0";
            String finalStart = startString;
            oxygenList.removeIf(s -> !s.startsWith(finalStart));

            if (oxygenList.size() == 1) {
                oxygenRating = Long.parseUnsignedLong(oxygenList.get(0), 2);
                break;
            }
        }

        // CO2 SCRUBBER
        startString = "";
        List<String> co2List = new ArrayList<>(input);
        long co2Rating = 0;
        for (int i = 0; i < length; i++) {
            startString += count(co2List, i, '0') <= count(co2List, i, '1') ? "0" : "1";
            String finalStart = startString;
            co2List.removeIf(s -> !s.startsWith(finalStart));

            if (co2List.size() == 1) {
                co2Rating = Long.parseUnsignedLong(co2List.get(0), 2);
                break;
            }
        }

        return oxygenRating * co2Rating;
    }

    private long count(List<String> input, int pos, char c) {
        return input.stream().map(s -> s.charAt(pos)).filter(character -> character == c).count();
    }

}
