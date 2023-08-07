package com.xiazeyu.core.service;

import com.xiazeyu.core.bean.Chessboard;
import com.xiazeyu.core.bean.Seed;
import com.xiazeyu.core.exception.InitException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Game {

    public static void init() {
        Seed seed = new Seed();
        Chessboard chessboard = new Chessboard();
        try {
            seed.init(chessboard);
            log.info(chessboard.print());
        } catch (InitException e) {
            log.error(e.getMessage());
        }
    }

}
