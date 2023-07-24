package io.graversen.minecraft.rcon.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameRulesTest {

    @Test
    void getGameRuleName() {
        assertEquals("mobGriefing", GameRules.MOB_GRIEFING.getGameRuleName());
    }
}
