package cn.buaa.play;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/5/25
 **/
public class Play {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(11);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i=0;i<10;i++){
            new Thread(new MyThread(cyclicBarrier,i+1,countDownLatch)).start();
        }
        try {
            countDownLatch.countDown();
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }


}

class MyThread implements Runnable {
    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;
    private int index;

    public MyThread(CyclicBarrier cyclicBarrier,int index,CountDownLatch countDownLatch) {
        this.cyclicBarrier = cyclicBarrier;
        this.index = index;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println("Thread"+Thread.currentThread()+"----"+index);
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
