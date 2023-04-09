package com.example.log.trace.strategy;

import com.example.log.trace.strategy.code.strategy.ContextV1;
import com.example.log.trace.strategy.code.strategy.Strategy;
import com.example.log.trace.strategy.code.strategy.StrategyLogic1;
import com.example.log.trace.strategy.code.strategy.StrategyLogic2;
import com.example.log.trace.template.code.AbstractTemplate;
import com.example.log.trace.template.code.SubClassLogic1;
import com.example.log.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        // biz logic
        log.info("biz logic 1 execute");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();
        // biz logic
        log.info("biz logic 2 execute");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
   void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
   }

   @Test
   void strategyV2(){
        Strategy logic1 = new Strategy() {
            @Override
            public void call() {
                log.info("logic 1 execute");
            }
        };
        ContextV1 v1 = new ContextV1(logic1);
        v1.execute();

        Strategy logic2 = new Strategy() {
            @Override
            public void call() {
                log.info("logic 2 execute");
            }
        };
        ContextV1 v2 = new ContextV1(logic2);
        v2.execute();
   }

   @Test
   void strategyV3(){
        ContextV1 v1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("logic 1 start");
            }
        });
        v1.execute();

        ContextV1 v2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("logic 2 start");
            }
        });
        v2.execute();
   }

   @Test
   void strategyV4(){
        ContextV1 v1 = new ContextV1(() -> log.info("lambda logic 1 start"));
        v1.execute();

        ContextV1 v2 = new ContextV1(() -> log.info("lambda logic 2 start"));
        v2.execute();
   }
}
