package com.my.pratice ;


public class Recursive {

    public static int recursiveArraySum(int[] data, int n){
        if( n==0 ) return 0;
        else
            return recursiveArraySum(data, n-1) + data[n-1];
    }

    public static void testRecursive(){
        int[] inputArray = {1, 3, 5, 4, 7};
        int result = recursiveArraySum(inputArray, 3);  // 9  --> 1+3+5
        System.out.println(result);
        result = recursiveArraySum(inputArray, 4);   // 13  --> 1+3+5+4
        System.out.println(result);
    }
}
