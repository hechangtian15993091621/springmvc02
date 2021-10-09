package cn.buaa.lifesycle;

import com.sun.org.apache.xpath.internal.objects.XObject;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.Unsafe;

import java.beans.Transient;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/5/26
 **/
public class Main {
    private static int total = 0;
    static CountDownLatch countDownLatch = new CountDownLatch(10);
    static Object object = new Object();
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public Main(int i) {
        total = i;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("现在开始初始化容器");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test_spring.xml");
        System.out.println("容器初始化成功");
        Person p = applicationContext.getBean("person",Person.class);
        System.out.println(p);
//        System.out.println("------------------");
//        Person p1 = applicationContext.getBean("person1",Person.class);
//        System.out.println(p1);
        System.out.println("现在开始关闭容器！");
        applicationContext.close();

//        ReentrantLock reentrantLock = new ReentrantLock(true);
//        reentrantLock.lock();
//        reentrantLock.unlock();
//
//        Main m = new Main();
//        m.method();


//        LocalDateTime localDateTime = LocalDateTime.now();
//        for(int i=0;i<10;i++){
//            new Thread(()->{
//                for(int j=0;j<10000;j++){
//                   synchronized (object){
//                       total++;
//                   }
////                    atomicInteger.getAndIncrement();
//                }
//                countDownLatch.countDown();
//            }).start();
//        }
//        LocalDateTime localDateTime1 = LocalDateTime.now();
//        Duration duration = Duration.between(localDateTime,localDateTime1);
//        countDownLatch.await();
////        System.out.println(atomicInteger.get());
//        System.out.println(total);
//        System.out.println(duration.toMillis());


//        System.out.println(ClassLayout.parseInstance(user).toPrintable());

//        try {
//            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//            theUnsafe.setAccessible(true);
//            Unsafe unsafe = (Unsafe)theUnsafe.get(null);
//            long a = unsafe.objectFieldOffset(User.class.getDeclaredField("id"));
//
//            System.out.println(a);
//
//        } catch (Exception e){
//            new Error(e);
//        }


//        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(8);
//        ScheduledFuture<String> schedule = scheduledExecutorService.schedule(() -> {
//            System.out.println(111);
//            return "123";
//        }, 5000, TimeUnit.MILLISECONDS);


//        scheduledExecutorService.scheduleAtFixedRate(()->{
//            System.out.println("begin");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("over");
//            throw new RuntimeException("123");
//        },1000,2000,TimeUnit.MILLISECONDS);
//
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
//        Future<Integer> submit = threadPoolExecutor.submit(() -> {
//            Thread.sleep(4000);
//            int a = 1 / 0;
//            return 1;
//        });
//        System.out.println(111);
//        try {
//            System.out.println(submit.get());
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println(111);
//
//
//        threadPoolExecutor.shutdown();
//        Thread.sleep(4000);
//        System.out.println(444);
    }
}


