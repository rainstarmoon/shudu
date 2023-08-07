package com.xiazeyu.shudu;

import com.xiazeyu.shudu.game.GenerateGame;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMain {

    public static void main(String[] args) {
        try {
            GenerateGame.init();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
