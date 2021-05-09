package cn.buaa;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/5/8
 **/
public class TestImpl implements Test {

    private final MyDelegate myDelegate = new MyDelegate();

    public void a() {
        myDelegate.a();
    }

    public void b() {
        myDelegate.b();
    }

    public void c() {
    }
}
