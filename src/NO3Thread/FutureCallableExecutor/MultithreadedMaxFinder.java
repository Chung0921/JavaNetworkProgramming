package NO3Thread.FutureCallableExecutor;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Usage: CALL FIND MAX TASK
 * Description: SAME TO USUAGE
 * Created by zhongyinghao on 2/15/16.
 */
public class MultithreadedMaxFinder {

    static String now = "now is:" + System.currentTimeMillis();

    static{
        System.out.println(now);
        System.out.println("static block is" + System.currentTimeMillis());
    }

    public static int max(int[] data) throws ExecutionException, InterruptedException {
        if(data.length == 1){
            return data[0];
        }else if(data.length == 0){
            throw new IllegalArgumentException();
        }

        //将任务分解为两部分
        FindMaxTask task1 = new FindMaxTask( data,0,data.length/2 );
        FindMaxTask task2 = new FindMaxTask( data,data.length/2,data.length );

        //创建两个线程
        ExecutorService service = Executors.newFixedThreadPool( 2 );

        Future<Integer> future1 = service.submit( task1 );
        Future<Integer> future2 = service.submit( task2 );

        return Math.max(future1.get(),future2.get());
    }

    //main to call method
    public static void main( String[] args ) {
        int[] data = new int[100000000];
        int max = Integer.MIN_VALUE;

        for ( int i=0;i<100000000;i++ ){
            data[i] = i;
            if(data[i] > max){
                max = data[i];
                //System.out.println("max current:" + max);
            }
        }

        try {
           int result = max(  data );

            //MultithreadedMaxFinder maxFinder = new MultithreadedMaxFinder();

            System.out.println("result is:" + result);
        } catch ( ExecutionException | InterruptedException e ) {
            e.printStackTrace();
        }
    }
}
