package com.xiazeyu.core.bind;

import com.xiazeyu.core.bean.Chessboard;
import com.xiazeyu.core.bean.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ruler {

    ROW((chessboard, coordinate, no) -> !chessboard.getRowAllNumber(coordinate.getMatrixType(), coordinate.getItemType()).contains(no)),

    COLUMN((chessboard, coordinate, no) -> !chessboard.getColumnAllNumber(coordinate.getMatrixIndex(), coordinate.getItemIndex()).contains(no)),

    ITEM((chessboard, coordinate, no) -> !chessboard.getMatrixByType(coordinate.getMatrixType())[coordinate.getMatrixIndex()].getAllNumber().contains(no));

    private final Check check;

    public static boolean checkAll(Chessboard chessboard, Coordinate coordinate, int no) {
        for (Ruler value : Ruler.values()) {
            if (!value.getCheck().check(chessboard, coordinate, no)) {
                return false;
            }
        }
        return true;
    }

    @FunctionalInterface
    public interface Check {
        boolean check(Chessboard chessboard, Coordinate coordinate, int no);
    }

}
