package com.xiazeyu.core.aggregate;

import com.xiazeyu.core.bean.Coordinate;
import com.xiazeyu.core.bind.Ruler;
import com.xiazeyu.core.constants.CommonConstant;
import com.xiazeyu.core.constants.Level;
import com.xiazeyu.core.constants.Type;
import com.xiazeyu.core.exception.InitException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 种子
 */
@Slf4j
@Data
public class Seed {

    private Random random = new Random();

    /**
     * 初始化
     *
     * @param chessboard
     * @throws InitException
     */
    public void init(Chessboard chessboard) throws InitException {
        initChessboard(chessboard, CommonConstant.retryTimes);
    }

    /**
     * 初始化棋盘
     *
     * @param chessboard
     * @param times
     * @throws InitException
     */
    private void initChessboard(Chessboard chessboard, int times) throws InitException {
        if (times == 0) {
            throw new InitException("数独生成失败");
        }
        try {
            for (int matrix = 0; matrix < 9; matrix++) {
                initMatrix(chessboard, matrix, CommonConstant.retryTimes);
            }
        } catch (InitException e) {
            log.error(e.getMessage());
            chessboard.clear();
            initChessboard(chessboard, times - 1);
        }
    }

    /**
     * 初始化矩阵
     *
     * @param chessboard
     * @param matrix
     * @param times
     * @throws InitException
     */
    private void initMatrix(Chessboard chessboard, int matrix, int times) throws InitException {
        if (times == 0) {
            throw new InitException("数独重新生成");
        }
        try {
            for (int item = 0; item < 9; item++) {
                Coordinate coordinate = calcCoordinate(matrix, item);
                List<Integer> allNumbers = allNumbers();
                while (true) {
                    int size = allNumbers.size();
                    if (size == 0) {
                        // log.error("错误棋盘: {}", chessboard.print());
                        throw new InitException("元素生成失败, 矩阵重新生成, matrix: " + matrix + ", item: " + item);
                    }
                    int noIndex = random.nextInt(size);
                    int no = allNumbers.remove(noIndex);
                    if (Ruler.checkAll(chessboard, coordinate, no)) {
                        // 未触发了行列块约束
                        chessboard.setValue(coordinate, no);
                        break;
                    }
                }
            }
        } catch (InitException e) {
            log.error(e.getMessage());
            Coordinate coordinate = calcCoordinate(matrix, 0);
            chessboard.getMatrixByType(coordinate.getMatrixType())[coordinate.getMatrixIndex()].clear();
            initMatrix(chessboard, matrix, times - 1);
        }
    }

    /**
     * 计算坐标
     *
     * @param matrix
     * @param item
     * @return
     */
    private Coordinate calcCoordinate(int matrix, int item) {
        Coordinate coordinate = new Coordinate();
        switch (matrix / 3) {
            case 0:
                coordinate.setMatrixType(Type.UP);
                break;
            case 1:
                coordinate.setMatrixType(Type.MIDDLE);
                break;
            case 2:
                coordinate.setMatrixType(Type.DOWN);
                break;
        }
        coordinate.setMatrixIndex(matrix % 3);
        switch (item / 3) {
            case 0:
                coordinate.setItemType(Type.UP);
                break;
            case 1:
                coordinate.setItemType(Type.MIDDLE);
                break;
            case 2:
                coordinate.setItemType(Type.DOWN);
                break;
        }
        coordinate.setItemIndex(item % 3);
        return coordinate;
    }

    /**
     * 根据难度初始化空白
     *
     * @param chessboard
     * @param level
     * @return
     */
    public Chessboard initEmpty(Chessboard chessboard, Level level) {
        Chessboard realChessboard = chessboard.copy();
        int emptySize = level.getEmptySize();
        int matrixEmptySize = emptySize / 9;
        int otherEmptySize = emptySize % 9;
        if (matrixEmptySize > 0) {
            for (int matrix = 0; matrix < 9; matrix++) {
                List<Integer> allNumbers = allNumbers();
                for (int emptyIndex = 0; emptyIndex < matrixEmptySize; emptyIndex++) {
                    int itemIndex = random.nextInt(allNumbers.size());
                    int item = allNumbers.remove(itemIndex) - 1;
                    Coordinate coordinate = calcCoordinate(matrix, item);
                    realChessboard.setValue(coordinate, 0);
                }
            }
        }
        while (otherEmptySize > 0) {
            int matrix = random.nextInt(9);
            int item = random.nextInt(9);
            Coordinate coordinate = calcCoordinate(matrix, item);
            int value = realChessboard.getValue(coordinate);
            if (value > 0) {
                realChessboard.setValue(coordinate, 0);
                otherEmptySize--;
            }
        }
        return realChessboard;
    }

    /**
     * 所有数字
     *
     * @return
     */
    private List<Integer> allNumbers() {
        List<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        return numbers;
    }

}
