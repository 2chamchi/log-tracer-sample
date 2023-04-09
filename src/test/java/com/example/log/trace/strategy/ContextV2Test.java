package com.example.log.trace.strategy;

import com.example.log.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
   void strategyV1() {
        ContextV2 v2 = new ContextV2();
        v2.execute(new StrategyLogic1());
        v2.execute(new StrategyLogic2());
        v2.execute(() -> log.info("biz logic 3 call") );
   }
}
