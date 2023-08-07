package com.xiazeyu.shudu.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

/**
 * 难度类型
 */
@Getter
@AllArgsConstructor
public enum Level {

    VERY_SIMPLE(1, 26),

    SIMPLE(27, 35),

    NORMAL(36, 44),

    HARD(45, 53),

    VERY_HARD(54, 81);

    private final int emptySizeMin;
    private final int emptySizeMax;
    private static final Random random = new Random();

    public int getEmptySize() {
        int emptySize = emptySizeMin + random.nextInt(emptySizeMax - emptySizeMin + 1);
        if (emptySize >= CommonConstant.chessboardAllRange) {
            return CommonConstant.chessboardAllRange - 1;
        }
        return emptySize;
    }

}
