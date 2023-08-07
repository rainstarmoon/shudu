package com.xiazeyu.shudu.ai;

import com.xiazeyu.shudu.ai.core.Analyst;
import com.xiazeyu.shudu.game.core.aggregate.Chessboard;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 玩游戏
 */
@Slf4j
public class PlayGame {

    public static void play(Chessboard gameChessboard) {
        Analyst analyst = new Analyst();
        Map<Integer, Integer> remainingNumber = analyst.remainingNumber(gameChessboard);


    }

}
