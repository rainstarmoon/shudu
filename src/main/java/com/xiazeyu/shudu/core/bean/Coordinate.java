package com.xiazeyu.shudu.core.bean;

import com.xiazeyu.shudu.core.constants.Type;
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
