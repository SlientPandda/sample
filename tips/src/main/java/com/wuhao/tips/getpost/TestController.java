package com.wuhao.tips.getpost;/**
 *
 */

import org.springframework.web.bind.annotation.*;

/**
 *@ClassName TestController
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/30 15:02
 *@Version 1.0
 **/
@RestController
@RequestMapping("/getpost")
public class TestController {

    //get请求接收参数的三种方式
    //query不加注解
    @GetMapping("/api/test1")
    public String get1(String id){
        return id;
    }

   //query加@RequestParam注解
    @GetMapping("/api/test2")
    public String get2(@RequestParam String id){
        return id;
    }

    //path加@PathVariable注解
    @GetMapping("/api/test3/{id}")
    public String get3(@PathVariable String id){
        return id;
    }

    //query、param、x-www-form-urlencoded不加注解
    @PostMapping("/api/test4")
    public String post1(String id){
        return id;
    }

    //query、param、x-www-form-urlencoded加@RequestParam注解
    @PostMapping("/api/test5")
    public String post2(@RequestParam String id){
        return id;
    }

    //path加@PathVariable注解
    @PostMapping("/api/test6/{id}")
    public String post3(@PathVariable String id){
        return id;
    }


    //@RequestBody+需要拿封装对象去接收
    @PostMapping("/api/test7")
    public String post4(@RequestBody String id){
        return id;
    }

}
