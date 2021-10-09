package cn.buaa.interceptors;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/5/11
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod)handler;
            String className = method.getBean().getClass().getName();//获得类名
            Parameter[] parameters = method.getMethod().getParameters();//获得方法参数
            String methodName = method.getMethod().getName();//获得方法名
        }

        String username = (String) request.getSession().getAttribute("username");
        if(StringUtils.isEmpty(username)){
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        return true;
    }
}
