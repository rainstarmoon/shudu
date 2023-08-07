package com.xiazeyu.game;

import com.xiazeyu.core.aggregate.Chessboard;
import com.xiazeyu.core.aggregate.Seed;
import com.xiazeyu.core.constants.Level;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Game {

    public static void init() throws Exception {
        Seed seed = new Seed();
        Chessboard chessboard = new Chessboard();
        seed.init(chessboard);
        log.info(chessboard.print());
        Chessboard realChessboard = seed.initEmpty(chessboard, Level.SIMPLE);
        log.info(realChessboard.print());
    }

}
