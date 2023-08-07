package com.xiazeyu.shudu.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameLibrary {

    private static Set<String> library = new HashSet<>();

    static {
        library.add("892315764165427983437986521438259671679831542215674398146527983298314756753869142");
    }

    public static String get() {
        return new ArrayList<>(library).get(0);
    }

}
