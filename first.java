import java.util.*;
import java.lang.*;

public class first{
    public static void main(String args[]){

    }
    long GetLongestStreak(int a, int b){
        long x = a^b;
        long y = a*b;
        long z = a+b;
        return max(findOnes(x),max(findOnes(y),findOnes(z)));
    }
    int findOnes(int x=n){
        int count = 0;
        while (n!=0)
        {
            n = (n & ( << 1));
            count++;
        }
        return count;
    }
    static int[] createTheArray(int n){
        int low = 1;
        int high = 100;
        int arr[] = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = (int)(Math.random()*(high-low+1) + low);
        }
        return arr;
    }
}