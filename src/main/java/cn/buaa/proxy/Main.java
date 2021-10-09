package cn.buaa.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/10/9
 **/
public class Main {
	public static void main(String[] args) {
		UserService userService = new UserService();
		UserServiceInterface userServiceInterface = (UserServiceInterface) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{UserServiceInterface.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("before2");
				Object o = method.invoke(userService,args);
				System.out.println("after");
				return o;
			}
		});

		userServiceInterface.test();
	}
}
