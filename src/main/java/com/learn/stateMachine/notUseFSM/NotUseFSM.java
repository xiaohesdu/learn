package com.learn.stateMachine.notUseFSM;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class NotUseFSM {
    private static int OFF = 0;
    private static int FAN_ONLY = 1;
    private static int COOL = 2;

    private int state = OFF;

    public int getCurrentState(){
        return state;
    }

//     按电源键
    public void  power(){
        if (state == OFF){
            state = FAN_ONLY;
            log.info("start fan.");
        }else if (state == FAN_ONLY){
            state = OFF;
            log.info("stop fan.");
        }else {
            state = OFF;
            log.info("stop cool.");
        }
    }

//    按制冷键
    public void cool(){
        if (state == OFF){
            log.info("do nothing.");
        }else if (state == FAN_ONLY){
            state = COOL;
            log.info("start cool.");
        }else {
            state = FAN_ONLY;
            log.info("stop cool.");
        }
    }
    public static void main(String[] args) {
        NotUseFSM notUseFSM = new NotUseFSM();
        final int currentState = notUseFSM.getCurrentState();
        log.info("currentState : " + currentState );

        notUseFSM.cool();
        notUseFSM.power();
        notUseFSM.cool();
        notUseFSM.cool();
        notUseFSM.power();
        notUseFSM.power();

    }
}
