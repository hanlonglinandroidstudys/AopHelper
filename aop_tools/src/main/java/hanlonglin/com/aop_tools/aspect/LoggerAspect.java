package hanlonglin.com.aop_tools.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

import hanlonglin.com.aop_tools.annotation.Logger;


@Aspect
public class LoggerAspect {

    /**
     * 日志切面
     * 需要拿到注解的参数（日志级别） 通过添加annotation（logger） 括号里的参数需要和方法中参数 logger名字一致
     *
     * @param joinPoint
     * @return
     */
    @Around("execution(@hanlonglin.com.aop_tools.annotation.Logger * *(..))")
    public Object doLogger(ProceedingJoinPoint joinPoint) {

        long start = System.currentTimeMillis();
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long spend = end - start;

        //获得方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Logger logger = method.getAnnotation(Logger.class);

        Log.e("TAG", "进入日志：" + joinPoint.getSignature().getName());
        int level = logger.value();

        //获取参数
        Class[] parameterTypes = signature.getParameterTypes();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();


        StringBuilder paramBuilder=new StringBuilder();
        for(int i=0;i<parameterNames.length;i++){
            paramBuilder.append(parameterTypes[i].getSimpleName());
            paramBuilder.append(" ");
            paramBuilder.append(parameterNames[i]);
            paramBuilder.append(" = ");
            paramBuilder.append(args[i].toString());
            paramBuilder.append(" , ");
        }
        String paramStr=paramBuilder.toString();

        StringBuilder content = new StringBuilder("\n");
        //组装格式
        content.append("╔════════════════════════════════════════════════════════════\n");
        content.append("║当前线程："+Thread.currentThread().getName()).append("\n");
        content.append("╟────────────────────────────────────────────────────────────").append("\n");
        content.append("║函数："+signature.getDeclaringTypeName()+"."+method.getName()).append("\n");
        content.append("╟────────────────────────────────────────────────────────────").append("\n");
        content.append("║参数："+paramStr).append("\n");
        content.append("╟────────────────────────────────────────────────────────────").append("\n");
        content.append("║返回："+method.getReturnType().getSimpleName()).append(" ").append(object.toString()).append("\n");
        content.append("╟────────────────────────────────────────────────────────────").append("\n");
        content.append("║耗时："+spend).append(" ms").append("\n");
        content.append("╚════════════════════════════════════════════════════════════\n");

        String msg = content.toString();
        switch (level) {
            case Log.ERROR:
                Log.e("TAG\n", msg);
                break;
            case Log.DEBUG:
                Log.d("TAG\n", msg);
                break;
            case Log.INFO:
                Log.i("TAG\n", msg);
                break;
        }

        return object;
    }

}
