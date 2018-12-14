package com.learn;

/**
 * @author gonghe.hogan
 */

 class findSecondMax {

     public static int findSecondMax(int[] data){
         int max;
         int second;
         if (data[0] > data[1]){
             max = data[0];
             second = data[1];
         }else {
             max = data[1];
             second = data[0];
         }



         for (int i : data){
             if (i > max){
                 second = max;
                 max = i;
             }
             if (i<max){
                 if (i > second){
                     second = i;
                 }
             }
         }

         return second;

     }

    public static void main(String[] args) {
        int[] dataArray = {11,34,78,90,2,7,100,80,100};
        final int secondMax = findSecondMax(dataArray);
        System.out.println(secondMax);
    }


}
