package com.xiazeyu.shudu.core.aggregate;

import com.xiazeyu.shudu.core.bean.Coordinate;
import com.xiazeyu.shudu.core.constants.Type;
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

    public String formatEigenvalue() {
        StringBuilder builder = new StringBuilder();
        for (Matrix matrix : upMatrixs) {
            builder.append(matrix.formatEigenvalue());
        }
        for (Matrix matrix : middleMatrixs) {
            builder.append(matrix.formatEigenvalue());
        }
        for (Matrix matrix : downMatrixs) {
            builder.append(matrix.formatEigenvalue());
        }
        return builder.toString();
    }

    public void parseEigenvalue(String value) {
        for (int i = 0; i < 3; i++) {
            upMatrixs[i].parseEigenvalue(value.substring(i * 9, (i + 1) * 9));
            middleMatrixs[i].parseEigenvalue(value.substring(i * 9 + 27, (i + 1) * 9 + 27));
            downMatrixs[i].parseEigenvalue(value.substring(i * 9 + 54, (i + 1) * 9 + 54));
        }
    }

    public void clear() {
        this.upMatrixs = new Matrix[]{new Matrix(), new Matrix(), new Matrix()};
        this.middleMatrixs = new Matrix[]{new Matrix(), new Matrix(), new Matrix()};
        this.downMatrixs = new Matrix[]{new Matrix(), new Matrix(), new Matrix()};
    }

    public Chessboard copy() {
        Chessboard chessboard = new Chessboard();
        for (int i = 0; i < upMatrixs.length; i++) {
            chessboard.upMatrixs[i] = upMatrixs[i].copy();
        }
        for (int i = 0; i < middleMatrixs.length; i++) {
            chessboard.middleMatrixs[i] = middleMatrixs[i].copy();
        }
        for (int i = 0; i < downMatrixs.length; i++) {
            chessboard.downMatrixs[i] = downMatrixs[i].copy();
        }
        return chessboard;
    }

    public void setValue(Coordinate coordinate, int no) {
        getMatrixByType(coordinate.getMatrixType())[coordinate.getMatrixIndex()].getItemByType(coordinate.getItemType())[coordinate.getItemIndex()] = no;
    }

    public int getValue(Coordinate coordinate) {
        return getMatrixByType(coordinate.getMatrixType())[coordinate.getMatrixIndex()].getItemByType(coordinate.getItemType())[coordinate.getItemIndex()];
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
        builder.append("------------------------------");
        builder.append("\n");
        builder.append(printRow(Type.MIDDLE, Type.UP));
        builder.append("\n");
        builder.append(printRow(Type.MIDDLE, Type.MIDDLE));
        builder.append("\n");
        builder.append(printRow(Type.MIDDLE, Type.DOWN));
        builder.append("\n");
        builder.append("------------------------------");
        builder.append("\n");
        builder.append(printRow(Type.DOWN, Type.UP));
        builder.append("\n");
        builder.append(printRow(Type.DOWN, Type.MIDDLE));
        builder.append("\n");
        builder.append(printRow(Type.DOWN, Type.DOWN));
        builder.append("\n");
        builder.append("特征值为: ");
        builder.append(formatEigenvalue());
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
