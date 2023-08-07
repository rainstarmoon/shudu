package com.xiazeyu.shudu.game;

import com.xiazeyu.shudu.game.core.aggregate.Chessboard;
import com.xiazeyu.shudu.game.core.aggregate.Seed;
import com.xiazeyu.shudu.game.core.constants.Level;
import lombok.extern.slf4j.Slf4j;

/**
 * 生成游戏
 */
@Slf4j
public class GenerateGame {

    public static void init() throws Exception {
        Seed seed = new Seed();
        Chessboard chessboard = new Chessboard();
//        seed.init(chessboard);
        chessboard.parseEigenvalue("892315764165427983437986521438259671679831542215674398146527983298314756753869142");
        log.info("原始棋盘: {}", chessboard.print());
        Chessboard gameChessboard = seed.initEmpty(chessboard, Level.SIMPLE);
        log.info("游戏棋盘: {}", gameChessboard.print());
    }

}
