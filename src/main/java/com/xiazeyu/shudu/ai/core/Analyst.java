package com.xiazeyu.shudu.ai.core;

import com.xiazeyu.shudu.ai.core.bean.NumberBean;
import com.xiazeyu.shudu.game.core.aggregate.Chessboard;
import com.xiazeyu.shudu.game.core.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Analyst {

    public List<NumberBean> remainingNumberBean(Chessboard gameChessboard) {
        String eigenvalue = gameChessboard.formatEigenvalue();
        Map<Integer, NumberBean> remainMap = new TreeMap<>();
        for (char c : eigenvalue.toCharArray()) {
            int no = CommonUtil.charToInt(c);
            if (no > 0) {
                NumberBean bean = remainMap.get(no);
                if (bean == null) {
                    bean = new NumberBean();
                    bean.setNo(no);
                    bean.setSize(9);
                }
                bean.setSize(bean.getSize() - 1);
                remainMap.put(no, bean);
            }
        }
        List<NumberBean> numberBeans = new ArrayList<>(remainMap.values());
        numberBeans.sort(null);
        return numberBeans;
    }

}
