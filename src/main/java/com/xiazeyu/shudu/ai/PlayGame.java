package com.xiazeyu.shudu.ai;

import com.xiazeyu.shudu.ai.core.Analyst;
import com.xiazeyu.shudu.ai.core.bean.NumberBean;
import com.xiazeyu.shudu.game.core.aggregate.Chessboard;
import com.xiazeyu.shudu.game.core.aggregate.Matrix;
import com.xiazeyu.shudu.game.core.bean.Coordinate;
import com.xiazeyu.shudu.game.core.bind.Ruler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 玩游戏
 */
@Slf4j
public class PlayGame {

    public static void analystPlay(Chessboard gameChessboard) {
        Analyst analyst = new Analyst();
        List<NumberBean> numberBeans = analyst.remainingNumberBean(gameChessboard);
        log.info("{}", numberBeans);
    }

    public static void fill(Chessboard gameChessboard, List<NumberBean> numberBeans) {

    }

    public static boolean fill(Chessboard gameChessboard, NumberBean numberBean) {
        for (int matrix = 0; matrix < 8; matrix++) {
            Coordinate coordinate = Coordinate.calcCoordinate(matrix, 0);
            Matrix matrixBean = gameChessboard.getMatrixByType(coordinate.getMatrixType())[coordinate.getMatrixIndex()];
            if (!matrixBean.getAllNumber().contains(numberBean.getNo())) {
                int size = 0;
                Coordinate lastCoordinate = null;
                for (int item = 0; item < 9; item++) {
                    coordinate = Coordinate.calcCoordinate(matrix, item);
                    int value = gameChessboard.getValue(coordinate);
                    if (value == 0) {
                        if (Ruler.checkAll(gameChessboard, coordinate, numberBean.getNo())) {
                            lastCoordinate = coordinate;
                            size++;
                        }
                    }
                }
                if (size == 1) {
                    gameChessboard.setValue(lastCoordinate, numberBean.getNo());
                    numberBean.setSize(numberBean.getSize() - 1);
                    return true;
                }
            }
        }
        return false;
    }

}
