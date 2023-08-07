package com.xiazeyu.shudu.ai.core;

import com.xiazeyu.shudu.game.core.aggregate.Chessboard;
import com.xiazeyu.shudu.game.core.utils.CommonUtil;

import java.util.Map;
import java.util.TreeMap;

public class Analyst {

    public Map<Integer, Integer> remainingNumber(Chessboard gameChessboard) {
        String eigenvalue = gameChessboard.formatEigenvalue();
        Map<Integer, Integer> remainMap = new TreeMap<>();
        for (char c : eigenvalue.toCharArray()) {
            int no = CommonUtil.charToInt(c);
            int remainSize = remainMap.getOrDefault(no, 9);
            remainMap.put(no, remainSize - 1);
        }
        return remainMap;
    }


}
