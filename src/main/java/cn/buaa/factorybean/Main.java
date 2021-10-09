package cn.buaa.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/8/17
 **/
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
        Object bean1 = context.getBean("myFactoryBean");
//        Object bean1 = context.getBean(MyService.class);
        System.out.println("名字为myFactoryBean的bean是" + bean1.getClass());
        Object bean2 = context.getBean("&myFactoryBean");
//        Object bean2 = context.getBean(MyFactoryBean.class);
        System.out.println("名字为&myFactoryBean的bean是" + bean2.getClass());
    }
}
