package com.xiazeyu.shudu.game.core.bean;

import com.xiazeyu.shudu.game.core.constants.Type;
import lombok.Data;

/**
 * 坐标
 */
@Data
public class Coordinate {

    private Type matrixType;

    private int matrixIndex;

    private Type itemType;

    private int itemIndex;

    /**
     * 计算坐标
     *
     * @param matrix
     * @param item
     * @return
     */
    public static Coordinate calcCoordinate(int matrix, int item) {
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

}
