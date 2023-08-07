package com.xiazeyu.shudu.ai.core.bean;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class NumberBean implements Comparable<NumberBean> {

    private int no;
    private int size;

    @Override
    public int compareTo(@NotNull NumberBean o) {
        return size - o.size;
    }
}
