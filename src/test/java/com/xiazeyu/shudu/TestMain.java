package com.xiazeyu.shudu;

import com.xiazeyu.shudu.ai.PlayGame;
import com.xiazeyu.shudu.game.GenerateGame;
import com.xiazeyu.shudu.game.core.aggregate.Chessboard;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMain {

    public static void main(String[] args) {
        try {
            Chessboard gameChessboard = GenerateGame.init();
            PlayGame.analystPlay(gameChessboard);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
