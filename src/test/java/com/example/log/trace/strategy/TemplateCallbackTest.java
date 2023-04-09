package com.example.log.trace.strategy;

import com.example.log.trace.strategy.code.strategy.ContextV2;
import com.example.log.trace.strategy.code.strategy.StrategyLogic1;
import com.example.log.trace.strategy.code.strategy.StrategyLogic2;
import com.example.log.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    @Test
   void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("biz logic 1 start"));
        template.execute(() -> log.info("biz logic 2 start"));
   }
}
