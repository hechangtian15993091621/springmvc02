package cn.buaa;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/5/8
 **/
public class Main {

    public static int thirdMax(int[] nums) {
        Object[] n = Arrays.stream(nums).boxed().toArray();
        Arrays.sort(n,(a,b) -> {
            if((Integer)a>(Integer) b){
                return -1;
            }else if ((Integer)a == (Integer) b){
                return 0;
            }else{
                return 1;
            }
        });
        System.out.println(Arrays.toString(n));
        for (int i = 1, diff = 1; i < n.length; ++i) {
            if ((int)n[i] != (int) n[i - 1] && ++diff == 3) { // 此时 nums[i] 就是第三大的数
                return (int)n[i];
            }
        }
        return (int)n[0];
    }

    public static void main(String[] args) {
        String[] s = " 1".split(" ");
        for (String s1 : s) {
            System.out.println(s1);
        }
        System.out.println("1"+s[0]+"2");
//        Map<String, String> map = System.getenv();
//        for(Iterator<String> itr = map.keySet().iterator(); itr.hasNext();){
//            String key = itr.next();
//            System.out.println(key + "=" + map.get(key));
//
//
//        }
//        new B().f();

//        new MyThread().start();
//
//        System.out.println("main");

//        MyThread.produce();
//        T t = new T();
//        new Thread(new TThread(t), "thread-1").start();
//        new Thread(new TThread(t), "thread-2").start();


//        try {
//            String hostAddress = InetAddress.getLocalHost().getHostAddress();
//            System.out.println(hostAddress);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }

//        AA a = () -> 0;
//        System.out.println(a.a());
//        TreeSet treeSet = new TreeSet();
//        treeSet.add(1);
//        treeSet.add(5);
//        treeSet.add(3);
//        System.out.println(treeSet.ceiling(3));
//        System.out.println(treeSet.higher(3));

//        System.out.println(-1%16);

//        HashMap hashMap = new HashMap();
//        int j = 0;
//        for (int i = 0; i < 8; i++) {
//            hashMap.put(j, i);
//            j += 16;
//        }
//        System.out.println(hashMap.size());


//        Method hash = HashMap.class.getDeclaredMethod("hash", Object.class);
//        Object invoke = null;
//        Object invoke1 = null;
//        Object invoke2 = null;
//        try {
//            hash.setAccessible(true);
//            invoke = hash.invoke(null, 0);
//            invoke1 = hash.invoke(null, 16);
//            invoke2 = hash.invoke(null, 32);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        System.out.println((Integer) invoke&15);
//        System.out.println((Integer) invoke1&15);
//        System.out.println((Integer) invoke2 &15);


//        int[] arr = new int[0];
//        Integer i = 1;
//        System.out.println(i.getClass());

//        try {
//            Class<?>[] classes = HashMap.class.getDeclaredClasses();
//            for (Class c : classes) {
//                if (c.getName().equals("java.util.HashMap$Node")) {
//                    System.out.println(c);
////                    Method hash = c.getDeclaredMethod("hash",Object.class);
//
//                    Method[] declaredMethods = c.getDeclaredMethods();
//                    for (Method m : declaredMethods) {
//                        System.out.println(m);
//                    }
////                    System.out.println(hash);
////                    Object res = hash.invoke(5);
////                    System.out.println(res);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        ***************反射*********
//        F.Z z = new F().new Z();
//        Method m = F.Z.class.getDeclaredMethod("m");
//        m.setAccessible(true);
//        try {
//            m.invoke(z);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

//        Class k = K.class;
    }
}


class K {
    static {
        System.out.println("我被构建了");
    }
}

class F {
    class Z {
        private void m() {
            System.out.println(111);
        }
    }
}

interface AA {
    int a();
}

class T {
    public void f() {
        synchronized (this) {
            System.out.println(Thread.currentThread() + "222");
        }
    }
}

class TThread implements Runnable {

    private T t;

    public TThread(T t) {
        this.t = t;
    }

    @Override
    public void run() {
        while (true) {
            t.f();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

interface A {
    default public void f() {
        System.out.println(555);
    }
}

class B implements A {
}

class MyThread extends Thread {

    public void start() {
        System.out.println(344);
        super.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(111);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Object obj = new Object();

    private static Object anotherObj = new Object();

    public static void produce() {
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
