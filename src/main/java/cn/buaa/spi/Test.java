package cn.buaa.spi;

import sun.misc.Service;

import java.util.Iterator;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/6/8
 **/
public class Test {
    public static void main(String[] args) {
//        Iterator<Car> providers = Service.providers(Car.class);
//        while (providers.hasNext()){
//            Car next = providers.next();
//            next.start();
//        }

        A a = new B()::h;
        a.f();
    }
}
class B{
    void h(){
        System.out.println(111);
    }
}
interface A{
    void f();
}
