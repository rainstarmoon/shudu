package com.xiazeyu.shudu.core.aggregate;

import com.xiazeyu.shudu.core.constants.Type;
import com.xiazeyu.shudu.core.utils.CommonUtil;
import lombok.Data;

import java.util.Set;
import java.util.TreeSet;

/**
 * 矩阵
 * 1-9个数字组成
 */
@Data
public class Matrix {

    private int[] upItems;

    private int[] middleItems;

    private int[] downItems;

    public Matrix() {
        clear();
    }

    public String formatEigenvalue() {
        char[] chars = new char[9];
        for (int i = 0; i < 3; i++) {
            chars[i] = CommonUtil.intToChar(upItems[i]);
            chars[i + 3] = CommonUtil.intToChar(middleItems[i]);
            chars[i + 6] = CommonUtil.intToChar(downItems[i]);
        }
        return new String(chars);
    }

    public void parseEigenvalue(String value) {
        char[] chars = value.toCharArray();
        for (int i = 0; i < 3; i++) {
            upItems[i] = CommonUtil.charToInt(chars[i]);
            middleItems[i] = CommonUtil.charToInt(chars[i + 3]);
            downItems[i] = CommonUtil.charToInt(chars[i + 6]);
        }
    }

    public void clear() {
        this.upItems = new int[3];
        this.middleItems = new int[3];
        this.downItems = new int[3];
    }

    public Matrix copy() {
        Matrix matrix = new Matrix();
        System.arraycopy(upItems, 0, matrix.upItems, 0, upItems.length);
        System.arraycopy(middleItems, 0, matrix.middleItems, 0, middleItems.length);
        System.arraycopy(downItems, 0, matrix.downItems, 0, downItems.length);
        return matrix;
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
            numbers.add(middleItem);
        }
        int downItem = downItems[index];
        if (downItem > 0) {
            numbers.add(downItem);
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
        builder.append("|");
        return builder.toString();
    }

}
