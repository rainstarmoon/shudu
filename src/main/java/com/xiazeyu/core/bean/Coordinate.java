package com.xiazeyu.core.bean;

import com.xiazeyu.core.constants.Type;
import lombok.Data;

@Data
public class Coordinate {

    private Type matrixType;

    private int matrixIndex;

    private Type itemType;

    private int itemIndex;

}
