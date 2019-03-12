package com.learn.stateMachine.airconFSM;

/**
 * @author gonghe.hogan
 */

public enum State1 {

    OFF {
        @Override
        void power(Aircon1 aircon1) {

        }

        @Override
        void cool(Aircon1 aircon1) {

        }
    },
    FANONLY {
        @Override
        void power (Aircon1 aircon1){

        }


        @Override
        
        void cool (Aircon1 aircon1){

        }

    };
//    状态机提前的接口
    abstract void power(Aircon1 aircon1);
    abstract void cool(Aircon1 aircon1);

//    状态机的各种动作action
    void entry(Aircon1 aircon1){
        System.out.println("-->" + aircon1.state.name());
    }

    void exit(Aircon1 aircon1){
        System.out.println(aircon1.state.name() + "-->");
    }

    void startCool(){
        System.out.println("start cool.");
    }

    void stopCool(){
        System.out.println("stop cool.");
    }

    void startFan(){
        System.out.println("start fan.");
    }

    void stopFan(){
        System.out.println("stop fan.");
    }
}
