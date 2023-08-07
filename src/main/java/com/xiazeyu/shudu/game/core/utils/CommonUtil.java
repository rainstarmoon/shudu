package com.xiazeyu.shudu.game.core.utils;

import java.util.LinkedList;
import java.util.List;

public class CommonUtil {

    public static List<Integer> allNumbers() {
        List<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public static char intToChar(int i) {
        return (char) (i + 48);
    }

    public static int charToInt(char c) {
        return c - 48;
    }
}
