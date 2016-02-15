package NO3Thread.FutureCallableExecutor;

import java.util.concurrent.Callable;

/**
 * Usage: FIND MAX VALUE ON ARRAY
 * Description: SAME TO USAGE
 * Created by zhongyinghao on 2/15/16.
 */
public class FindMaxTask implements Callable<Integer> {

    private int[] data;
    private int start;
    private int end;

    FindMaxTask(int[] data, int start, int end){
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int max = Integer.MIN_VALUE;
        System.out.println("start current timestamp is: "+System.currentTimeMillis());
        for(int i = start; i < end; i++){
            if(data[i] > max){
                max = data[i];
            }
        }
        System.out.println("end current timestamp is: "+System.currentTimeMillis());
        return max;
    }
}
