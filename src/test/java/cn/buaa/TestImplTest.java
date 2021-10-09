package cn.buaa;

import org.junit.Test;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.junit.Assert.*;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/5/9
 **/
public class TestImplTest {

    @Test
    public void a() {
        TestImpl test = new TestImpl();
        test.a();
        test.b();
        test.b();
    }

    @Test
    public void b() {
        TestImpl test = new TestImpl();
        test.b();
    }

    @Test
    public void c() {
        TestImpl test = new TestImpl();
        test.c();
    }
}
