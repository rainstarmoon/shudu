package com.xiazeyu.shudu.game.core.aggregate;

import com.xiazeyu.shudu.game.core.bean.Coordinate;
import com.xiazeyu.shudu.game.core.bind.Ruler;
import com.xiazeyu.shudu.game.core.constants.CommonConstant;
import com.xiazeyu.shudu.game.core.constants.Level;
import com.xiazeyu.shudu.game.core.exception.InitException;
import com.xiazeyu.shudu.game.core.utils.CommonUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

/**
 * 生成种子
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
                Coordinate coordinate = Coordinate.calcCoordinate(matrix, item);
                List<Integer> allNumbers = CommonUtil.allNumbers();
                while (true) {
                    int size = allNumbers.size();
                    if (size == 0) {
                        // log.error("错误棋盘: {}", chessboard.print());
                        throw new InitException("元素生成失败, 矩阵重新生成, matrix: " + matrix + ", item: " + item);
                    }
                    int noIndex = random.nextInt(size);
                    int no = allNumbers.remove(noIndex);
                    if (Ruler.checkAll(chessboard, coordinate, no)) {
                        // 未触发 行 列 块 约束
                        chessboard.setValue(coordinate, no);
                        break;
                    }
                }
            }
        } catch (InitException e) {
            log.error(e.getMessage());
            Coordinate coordinate = Coordinate.calcCoordinate(matrix, 0);
            chessboard.getMatrixByType(coordinate.getMatrixType())[coordinate.getMatrixIndex()].clear();
            initMatrix(chessboard, matrix, times - 1);
        }
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
                List<Integer> allNumbers = CommonUtil.allNumbers();
                for (int emptyIndex = 0; emptyIndex < matrixEmptySize; emptyIndex++) {
                    int itemIndex = random.nextInt(allNumbers.size());
                    int item = allNumbers.remove(itemIndex) - 1;
                    Coordinate coordinate = Coordinate.calcCoordinate(matrix, item);
                    realChessboard.setValue(coordinate, 0);
                }
            }
        }
        while (otherEmptySize > 0) {
            int matrix = random.nextInt(9);
            int item = random.nextInt(9);
            Coordinate coordinate = Coordinate.calcCoordinate(matrix, item);
            int value = realChessboard.getValue(coordinate);
            if (value > 0) {
                realChessboard.setValue(coordinate, 0);
                otherEmptySize--;
            }
        }
        return realChessboard;
    }
}
