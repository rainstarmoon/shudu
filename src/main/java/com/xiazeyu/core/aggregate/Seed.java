package com.xiazeyu.core.aggregate;

import com.xiazeyu.core.bean.Coordinate;
import com.xiazeyu.core.bind.Ruler;
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

    private Random random;

    public Seed() {
        this.random = new Random();
    }

    public void init(Chessboard chessboard) throws InitException {
        initChessboard(chessboard, 3);
    }

    private void initChessboard(Chessboard chessboard, int times) throws InitException {
        if (times == 0) {
            throw new InitException("数独生成失败");
        }
        try {
            for (int matrix = 0; matrix < 9; matrix++) {
                initMatrix(chessboard, matrix, 3);
            }
        } catch (InitException e) {
            log.error(e.getMessage());
            chessboard.clear();
            initChessboard(chessboard, times - 1);
        }
    }

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

    private List<Integer> allNumbers() {
        List<Integer> numbers = new LinkedList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);
        return numbers;
    }

}
