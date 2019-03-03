package hanlonglin.com.aop_tools.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * 处理子线程切换的 切面
 */
@Aspect
public class AsyncAspect {

    /**
     * 限定注解格式
     * execution(@包名+类名 返回类型 方法名（参数）)
     * 例子：以下是在该注解中 所有返回值为void 任意参数的方法中起作用
     */
    @Around("execution(@hanlonglin.com.aop_tools.annotation.Async void *(..))")
    public void doAsycMethod(final ProceedingJoinPoint joinPoint) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe();
    }
}

