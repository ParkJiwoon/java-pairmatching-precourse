package pairmatching.util;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String string() {
        String input = Console.readLine();
        System.out.println();
        return input;
    }

    public static String[] strings() {
        String[] inputs = Console.readLine().split(",");
        System.out.println();
        return inputs;
    }
}
