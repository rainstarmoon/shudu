package com.xiazeyu.core.bean;

import com.xiazeyu.core.constants.Type;
import lombok.Data;

import java.util.Set;
import java.util.TreeSet;

/**
 * 矩阵
 */
@Data
public class Matrix {

    private int[] upItems;

    private int[] middleItems;

    private int[] downItems;

    public Matrix() {
        clear();
    }

    public void clear() {
        this.upItems = new int[3];
        this.middleItems = new int[3];
        this.downItems = new int[3];
    }

    public Set<Integer> getAllNumber() {
        Set<Integer> numbers = new TreeSet<>();
        numbers.addAll(getRowAllNumber(Type.UP));
        numbers.addAll(getRowAllNumber(Type.MIDDLE));
        numbers.addAll(getRowAllNumber(Type.MIDDLE));
        return numbers;
    }

    public Set<Integer> getRowAllNumber(Type type) {
        Set<Integer> numbers = new TreeSet<>();
        switch (type) {
            case UP:
                for (int item : upItems) {
                    if (item > 0) {
                        numbers.add(item);
                    }
                }
                break;
            case MIDDLE:
                for (int item : middleItems) {
                    if (item > 0) {
                        numbers.add(item);
                    }
                }
                break;
            case DOWN:
                for (int item : downItems) {
                    if (item > 0) {
                        numbers.add(item);
                    }
                }
                break;
        }
        return numbers;
    }

    public Set<Integer> getColumnAllNumber(int index) {
        Set<Integer> numbers = new TreeSet<>();
        int upItem = upItems[index];
        if (upItem > 0) {
            numbers.add(upItem);
        }
        int middleItem = middleItems[index];
        if (middleItem > 0) {
            numbers.add(upItem);
        }
        int downItem = downItems[index];
        if (downItem > 0) {
            numbers.add(upItem);
        }
        return numbers;
    }

    public int[] getItemByType(Type type) {
        switch (type) {
            case UP:
                return upItems;
            case MIDDLE:
                return middleItems;
            case DOWN:
                return downItems;
        }
        return null;
    }

    public String printRow(Type type) {
        StringBuilder builder = new StringBuilder();
        switch (type) {
            case UP:
                for (int item : upItems) {
                    builder.append(item).append(" ");
                }
                break;
            case MIDDLE:
                for (int item : middleItems) {
                    builder.append(item).append(" ");
                }
                break;
            case DOWN:
                for (int item : downItems) {
                    builder.append(item).append(" ");
                }
                break;
        }
        return builder.toString();
    }

}
