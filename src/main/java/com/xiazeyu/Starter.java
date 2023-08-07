package com.xiazeyu;

import com.xiazeyu.game.Game;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统入口
 */
@Slf4j
public class Starter {

    public static void main(String[] args) {
        try {
            Game.init();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
