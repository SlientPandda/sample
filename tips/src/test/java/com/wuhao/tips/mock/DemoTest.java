package com.wuhao.tips.mock;

import javafx.beans.binding.When;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 为什么要使用mock
 * mock可以理解为创建一个虚假的对象，或者说模拟出一个对象，在测试环境中用来替换掉真实，以达到我们可以
 * 1、验证该对象的某些方法的调用情况，调用了多少次，参数是多少
 * 2、给这个对象的行为做一个定义，来指定返回结果或者指定特定的动作
 * <p>
 * mock方法
 * 它表示可以mock一个对象或者是接口
 * <p>
 * 验证和断言
 * 验证是校验待验证的对象是否发生过某些行为，mockito中验证的方法verify
 * 当使用mock对象时，如果不对其行为进行定义，则mock对象方法的返回值为返回类型的默认值。
 * <p>
 * 给mock对象打桩
 * 打桩的意思就是给mock对象规定一行的行为，使其按照我们的要求来执行具体的操作
 * 使用@mock注解的方法，不需要使用mock方法传入对象，但是需要搭配MockitoAnnotations.openMocks(testClass)使用
 *
 * @BeforeEach和@AfterEach注解 在每个@Test方法执行前后调用
 * <p>
 * Spy方法和@Spy注解
 * Spy方法与mock方法不同的是，
 * 1、被spy注解的方法会执行真实的方法，而mock对象不会
 * 2、spy方法的参数是对象实例，mock的参数是class
 */
class DemoTest {
    @Mock
    private Demo mockDemo;

    @Spy
    private SpyDemo spyDemo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.out.println("test begin!");
    }

    @Test
    void add() {
//        Demo mockDemo = Mockito.mock(Demo.class);
        System.out.println(mockDemo.add(1, 2));
//        verify验证方法调用次数
//        Mockito.verify(mockDemo, Mockito.times(2)).add(1, 2);
        //打桩
        Mockito.when(mockDemo.add(1, 2)).thenReturn(10);
        //断言
        Assertions.assertEquals(10, mockDemo.add(1, 2));
    }

    @Test
    void spyAdd() {
        Mockito.when(spyDemo.add(1, 2)).thenReturn(10);
        Assertions.assertEquals(3, spyDemo.add(1, 2));

        Mockito.when(spyDemo.add(1, 2)).thenThrow(new RuntimeException());
        spyDemo.add(1, 2);

        Mockito.when(spyDemo.add(1, 2)).thenCallRealMethod();
        Assertions.assertEquals(2, spyDemo.add(1, 2));

    }

    @Test
    void mock() {
        try {
            Mockito.when(mockDemo.add(1, 2)).thenCallRealMethod();
            Assertions.assertEquals(3, mockDemo.add(1, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AfterEach
    void after() {
        System.out.println("test end");
    }
}