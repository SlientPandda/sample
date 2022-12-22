package com.wuhao.aop.dto;/**
 *
 */

import com.alibaba.fastjson.JSON;
import com.wuhao.aop.annotation.RecordOperate;
import com.wuhao.aop.service.Convert;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 *@ClassName OperateAspect
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/9 19:45
 *@Version 1.0
 **/
@Component
@Aspect
@Slf4j
public class OperateAspect {
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));

    /**
     * 定义切入点
     * 横切逻辑
     * 织入
     */
    @Pointcut("@annotation(com.wuhao.aop.annotation.RecordOperate)")
    public void pointcut() {
        log.info("log日志切入点....");
    }

    /**
     *
     * @param proceedingJoinPoint 切入点
     * @return
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        threadPoolExecutor.execute(() -> {
            //通过反射拿到方法签名
            try {
                MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
                RecordOperate annotation = signature.getMethod().getAnnotation(RecordOperate.class);

                Class<? extends Convert> convert = annotation.convert();
                Convert logConvert = convert.newInstance();
                OperateLogDto operateLogDto = logConvert.convert(proceedingJoinPoint.getArgs()[0]);

                operateLogDto.setDesc(annotation.desc());
                operateLogDto.setResult(result.toString());

                log.info("insert operateLog " + JSON.toJSONString(operateLogDto));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return result;
    }
}
