package com.learn.stateMachine.airconFSM;

/**
 * @author gonghe.hogan
 */

public class Aircon1 {

    State1 state = State1.OFF;
    public void power(){
        state.power(this);
    }

    public void cool(){
        state.cool(this);
    }
}
