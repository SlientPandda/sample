package com.wuhao.lock.demo;/**
 *
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 *@ClassName CompleteFutureService
 *@Description TODO
 *@Author wuhao51
 *@Date 2023/7/20 17:38
 *@Version 1.0
 **/
@Slf4j
@Service
public class CompleteFutureService {

    @Resource(name = "myThreadPool")
    private ThreadPoolExecutor threadPool;

//    public CompleteFutureService() {
//        threadPool = new ThreadPoolExecutor(64,
//                64,
//                1L,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(Integer.MAX_VALUE),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.CallerRunsPolicy());
//    }


    public void main() throws Exception {
        int i = 0;
        List<String> s = new ArrayList<>();
        while (i < 10) {
            s.add("" + i++);
        }
        System.out.println(s);
        Random random = new Random();
        List<String> a = new ArrayList<>();

        CompletableFuture<List<String>>[] completableFutures = s.stream().map(ss ->
                CompletableFuture.supplyAsync(() -> {
                    System.out.println( Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(random.nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    if(random.nextInt(10) > 5) {
//                        return null;
//                    }
                    return new ArrayList<>(Arrays.asList(ss + "###"));
                }, threadPool)
        ).toArray(CompletableFuture[]::new);


        //CompletableFuture 的 get() 方法和 join() 方法都可以用于获取异步任务的结果，它们之间的主要区别在于异常处理和返回类型上。
        //get()方法会阻塞当前线程直到所有任务都完成，并且可能抛出ExecutionException异常。在使用get()方法时，需要逐个处理每个CompletableFuture对象的结果。
        CompletableFuture.allOf(completableFutures).join();

        for (CompletableFuture<List<String>> completableFuture : completableFutures) {
            System.out.println(completableFuture.get());
        }

        List<String> collect = Arrays.stream(completableFutures).flatMap(completableFuture ->
        {
            try {
                return completableFuture.get().stream();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        System.out.println(collect);

        Arrays.stream(completableFutures).forEach(cf -> {
            try {
                cf.get().clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        for (CompletableFuture<List<String>> completableFuture : completableFutures) {
            System.out.println(completableFuture.get());
        }

        System.out.println(collect);
    }


    void asyncTask(){
        List<String> imageList = new ArrayList<>()

        //成功数、失败数
        AtomicInteger successNum = new AtomicInteger();
        AtomicInteger failNum = new AtomicInteger();

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (String image : imageList) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
//                    downloadImage(image); 下载图片
                    successNum.incrementAndGet();
                } catch (Exception e) {
                    log.error("图片保存失败");
                    failNum.incrementAndGet();
                }
            }, threadPool);
            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
}
