package com.xiazeyu.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 难度类型
 */
@Getter
@AllArgsConstructor
public enum Level {
    Very_simple(1, 26),
    simple(27, 35),
    normal(36, 44),
    hard(45, 53),
    Very_hard(54, 81);

    private final int emptySizeMin;
    private final int emptySizeMax;
}
