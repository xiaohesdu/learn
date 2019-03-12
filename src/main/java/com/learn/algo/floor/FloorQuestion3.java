package com.learn.algo.floor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gonghe.hogan
 */

public class FloorQuestion3 {
//    100层，每次走1,2步

    public static List<List<Integer>> solution(int total){

        List<List<Integer>> result = new ArrayList<>();
        getFloorResult(result, total, new ArrayList<>(),1,0);
        getFloorResult(result, total, new ArrayList<>(),2,0);
        return result;
    }

    public static void getFloorResult(List<List<Integer>> result, int total, List<Integer> oneSolution, int step, int sum){
        sum = sum + step;
        oneSolution.add(step);
        if (sum == total){
            result.add(new ArrayList<>(oneSolution));
            return ;
        }
        if (sum > total){
            return;
        }
//        这里必须是新建一个list，否则不同solution的结果会混在一起
        getFloorResult(result, total, new ArrayList<>(oneSolution), 1, sum);
        getFloorResult(result, total, new ArrayList<>(oneSolution), 2, sum);
    }



    public static void main(String[] args) {
        final long begin = System.currentTimeMillis();
        final List<List<Integer>> solution = solution(40);
        System.out.println("耗时：" + (System.currentTimeMillis()-begin));
        System.out.println(solution.toString());
    }
}
