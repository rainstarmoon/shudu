package com.xiazeyu.core.aggregate;

import com.xiazeyu.core.bean.Coordinate;
import com.xiazeyu.core.constants.Type;
import lombok.Data;

import java.util.Set;
import java.util.TreeSet;

/**
 * 棋盘
 * 由1-9矩阵组成
 */
@Data
public class Chessboard {

    private Matrix[] upMatrixs;

    private Matrix[] middleMatrixs;

    private Matrix[] downMatrixs;

    public Chessboard() {
        clear();
    }

    public void clear() {
        this.upMatrixs = new Matrix[]{new Matrix(), new Matrix(), new Matrix()};
        this.middleMatrixs = new Matrix[]{new Matrix(), new Matrix(), new Matrix()};
        this.downMatrixs = new Matrix[]{new Matrix(), new Matrix(), new Matrix()};
    }

    public void setValue(Coordinate coordinate, int no) {
        getMatrixByType(coordinate.getMatrixType())[coordinate.getMatrixIndex()].getItemByType(coordinate.getItemType())[coordinate.getItemIndex()] = no;
    }

    public Matrix[] getMatrixByType(Type type) {
        switch (type) {
            case UP:
                return upMatrixs;
            case MIDDLE:
                return middleMatrixs;
            case DOWN:
                return downMatrixs;
        }
        return null;
    }

    public Set<Integer> getRowAllNumber(Type matrixType, Type itemType) {
        Set<Integer> numbers = new TreeSet<>();
        for (Matrix matrix : getMatrixByType(matrixType)) {
            numbers.addAll(matrix.getRowAllNumber(itemType));
        }
        return numbers;
    }

    public Set<Integer> getColumnAllNumber(int matrixIndex, int itemIndex) {
        Set<Integer> numbers = new TreeSet<>();
        numbers.addAll(upMatrixs[matrixIndex].getColumnAllNumber(itemIndex));
        numbers.addAll(middleMatrixs[matrixIndex].getColumnAllNumber(itemIndex));
        numbers.addAll(downMatrixs[matrixIndex].getColumnAllNumber(itemIndex));
        return numbers;
    }

    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append(printRow(Type.UP, Type.UP));
        builder.append("\n");
        builder.append(printRow(Type.UP, Type.MIDDLE));
        builder.append("\n");
        builder.append(printRow(Type.UP, Type.DOWN));
        builder.append("\n");
        builder.append(printRow(Type.MIDDLE, Type.UP));
        builder.append("\n");
        builder.append(printRow(Type.MIDDLE, Type.MIDDLE));
        builder.append("\n");
        builder.append(printRow(Type.MIDDLE, Type.DOWN));
        builder.append("\n");
        builder.append(printRow(Type.DOWN, Type.UP));
        builder.append("\n");
        builder.append(printRow(Type.DOWN, Type.MIDDLE));
        builder.append("\n");
        builder.append(printRow(Type.DOWN, Type.DOWN));
        builder.append("\n");
        return builder.toString();
    }

    public String printRow(Type matrixType, Type itemType) {
        StringBuilder builder = new StringBuilder();
        switch (matrixType) {
            case UP:
                for (Matrix matrix : upMatrixs) {
                    builder.append(matrix.printRow(itemType));
                }
                break;
            case MIDDLE:
                for (Matrix matrix : middleMatrixs) {
                    builder.append(matrix.printRow(itemType));
                }
                break;
            case DOWN:
                for (Matrix matrix : downMatrixs) {
                    builder.append(matrix.printRow(itemType));
                }
                break;
        }
        return builder.toString();
    }

}
