package cn.buaa.play;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/5/27
 **/
public class CloneDemo {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static CountDownLatch countDownLatch1 = new CountDownLatch(100);


    public static void main(String[] args) {
//        User user1 = new User("hct",12,new Address("上海市"));
//
//        try {
//            User user2 = (User) user1.clone();
//            System.out.println(user1);
//            System.out.println(user2);
////            user2.address.setSite("无锡");
//            user2.setName("121");
//            System.out.println(user1);
//            System.out.println(user2);
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }

//        Tuling[] arr = new Tuling[]{new Tuling(1,new Address("1")),new Tuling(2,new Address("2"))};
////        Tuling[] clone = arr.clone();
//        Object[] clone = Arrays.copyOf(arr,arr.length,Object[].class);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(clone));
//        ((Tuling)clone[0]).address.setSite("111");
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(clone));

        Object[] arr1 = new Tuling[]{new Tuling(3,new Address("3")),new Tuling(4,new Address("4"))};
        AtomicReferenceArray<Tuling> integerAtomicReferenceArray = new AtomicReferenceArray(arr1);

        integerAtomicReferenceArray.set(0,new Tuling(555,new Address("555")));
        try {
            Field array = AtomicReferenceArray.class.getDeclaredField("array");
            array.setAccessible(true);
            Object[] o = (Object[]) array.get(integerAtomicReferenceArray);
//            arr1 = o;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(integerAtomicReferenceArray.get(0));
        System.out.println(arr1[0]);
        User u = new User("123",1,new Address("12"));
        String a = "1";
        String b = new String("1");
        System.out.println(a.hashCode());
        System.out.println(System.identityHashCode(a));
        System.out.println(b.hashCode());
        System.out.println(System.identityHashCode(b));
        System.out.println(a == b);
        try {
            Object clone = u.clone();
            System.out.println(clone.hashCode());
            System.out.println(u.hashCode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


//        MyUser myUser = new MyUser(1);
//
//        IntUnaryOperator unaryOperator = s -> {
//          return 12;
//        };
//
//        AtomicIntegerFieldUpdater<MyUser> age = AtomicIntegerFieldUpdater.newUpdater(MyUser.class, "age");
//        int andUpdate = age.getAndUpdate(myUser,unaryOperator);
//        System.out.println(andUpdate);
//        for(int i=0;i<100;i++){
//            new Thread(()->{
//                for(int j=0;j<1000;j++){
//                    age.getAndIncrement(myUser);
//                }
//                countDownLatch1.countDown();
//            }).start();
//        }
//
//
////        MyUserUpdater myUserUpdater = new MyUserUpdater(countDownLatch, myUser,countDownLatch1);
////
////        for(int i=0;i<100;i++){
////            new Thread(myUserUpdater).start();
////        }
////
////        countDownLatch.countDown();
//        try {
//            countDownLatch1.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        System.out.println(myUser.getAge());
    }
}

class MyUser{
    public volatile int age;

    public MyUser(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge() {
        this.age = this.age+1;
    }
}

class MyUserUpdater implements Runnable{
    private CountDownLatch countDownLatch;
    private CountDownLatch countDownLatch1;
    private MyUser myUser;

    public MyUserUpdater(CountDownLatch countDownLatch, MyUser myUser,CountDownLatch countDownLatch1) {
        this.countDownLatch = countDownLatch;
        this.myUser = myUser;
        this.countDownLatch1 = countDownLatch1;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            for(int i=0;i<1000;i++){
                synchronized (this){
                    myUser.setAge();
                }
            }
            countDownLatch1.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class User implements Cloneable, Serializable {
    private String name;
    private Integer id;
    public Address address;

    public User(String name, Integer id,Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", address=" + address +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
class Address{
    @Override
    public String toString() {
        return "Address{" +
                "site='" + site + '\'' +
                '}';
    }

    private String site;

    public Address(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
class Tuling{
    private Integer seq;
    public Address address;

    @Override
    public String toString() {
        return "Tuling{" +
                "seq=" + seq +
                ", address=" + address +
                '}';
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Tuling(Integer seq, Address address) {
        this.seq = seq;
        this.address = address;
    }

    public Tuling(Integer seq) {
        this.seq = seq;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
