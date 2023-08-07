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

}
