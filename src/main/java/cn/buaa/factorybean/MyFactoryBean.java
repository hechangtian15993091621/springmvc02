package cn.buaa.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/8/17
 **/
@Component
public class MyFactoryBean implements FactoryBean<MyService> {
    @Override
    public MyService getObject() throws Exception {
        return new MyService();
    }
    @Override
    public Class<?> getObjectType() {
        // FactoryBean所产生的Bean的类型
        return MyService.class;
    }
    @Override
    public boolean isSingleton() {
        // 设置返回的Bean是否为单例
        return true;
    }
}
