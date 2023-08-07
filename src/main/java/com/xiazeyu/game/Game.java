package com.xiazeyu.game;

import com.xiazeyu.core.aggregate.Chessboard;
import com.xiazeyu.core.aggregate.Seed;
import com.xiazeyu.core.constants.Level;
import com.xiazeyu.example.GameLibrary;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Game {

    public static void init() throws Exception {
        Seed seed = new Seed();
        Chessboard chessboard = new Chessboard();
//        seed.init(chessboard);
        chessboard.parseEigenvalue(GameLibrary.get());
        log.info("成功棋盘: {}", chessboard.print());
        Chessboard realChessboard = seed.initEmpty(chessboard, Level.SIMPLE);
        log.info(realChessboard.print());
    }

}
