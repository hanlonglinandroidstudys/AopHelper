package hanlonglin.com.aop_tools.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

import hanlonglin.com.aop_tools.annotation.UserLogin;

@Aspect
public class UserLoginAspect {

    @Around("execution(@hanlonglin.com.aop_tools.annotation.UserLogin void *(..))")
    public void doUserLogin(ProceedingJoinPoint joinPoint){

        //==========先验证登录==========
        //1.获取注解中的userName和password
        MethodSignature signature=(MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        UserLogin annotation = method.getAnnotation(UserLogin.class);
        String password = annotation.password();
        String userName = annotation.userName();
        Log.e("UserLoginAspect", "得到userName:"+userName+",password:"+password);

        //2.验证用户名密码是否正确
        if(!"hanlonglin".equals(userName)
                ||!"123".equals(password)) {
            //验证失败
            Log.e("UserLoginAspect","登录验证失败");
            return;
        }
        Log.e("UserLoginAspect","登录验证成功");

        //==========执行原方法==========
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        //==========执行后续操作==========
        Log.e("UserLoginAspect","登录后续操作...");
    }
}
