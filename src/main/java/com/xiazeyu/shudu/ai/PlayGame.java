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

    static int time = 1;

    public static void analystPlay(Chessboard gameChessboard) {
        Analyst analyst = new Analyst();
        List<NumberBean> numberBeans = analyst.remainingNumberBean(gameChessboard);
        log.info("begin: {}", numberBeans);
        boolean change = false;
        int time = 3;
        while (true) {
            change = fill(gameChessboard, numberBeans);
            numberBeans = analyst.remainingNumberBean(numberBeans);
            if (gameChessboard.isOver()) {
                log.info("计算成功");
                break;
            }
            if (!change) {
                time--;
            }
            if (time == 0) {
                log.info("无法计算");
                break;
            }
        }
        log.info("end: {}", numberBeans);
    }

    public static boolean fill(Chessboard gameChessboard, List<NumberBean> numberBeans) {
        boolean change = false;
        for (NumberBean numberBean : numberBeans) {
            while (true) {
                if (fill(gameChessboard, numberBean)) {
                    change = true;
                } else {
                    break;
                }
            }
        }
        return change;
    }

    public static boolean fill(Chessboard gameChessboard, NumberBean numberBean) {
        boolean change = false;
        for (int matrix = 0; matrix < 9; matrix++) {
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
                    String oldPrint = gameChessboard.print();
                    gameChessboard.setValue(lastCoordinate, numberBean.getNo());
                    String newPrint = gameChessboard.print();
                    log.info("第{}次计算: {}", time++, printDiff(oldPrint, newPrint, numberBean.getNo()));
                    numberBean.setSize(numberBean.getSize() - 1);
                    change = true;
                }
            }
        }
        return change;
    }

    private static String printDiff(String oldPrint, String newPrint, int no) {
        StringBuilder builder = new StringBuilder();
        String[] oldSplit = oldPrint.split("\n");
        String[] newSplit = newPrint.split("\n");
        for (int i = 0; i < oldSplit.length; i++) {
            if (oldSplit[i].equals(newSplit[i])) {
                builder.append(newSplit[i]).append("\n");
            } else {
                builder.append(newSplit[i].replace(no + " ", no + "*")).append("\n");
            }
        }
        return builder.toString();
    }

}
