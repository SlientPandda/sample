package com.wuhao.tips.either;/**
 *
 */

import lombok.Data;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *@ClassName Either
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/26 10:35
 *@Version 1.0
 **/
@Data
public class Either<L, R> {
    /**
     * 代表异常情况
     */
    private L left;


    /**
     * 代表正常情况
     */
    private R right;


    public Boolean isLeft() {
        return left != null;
    }

    public Boolean isRight() {
        return right != null;
    }

    /**
     * 工厂方法-左值
     * 静态泛型方法需要在static修饰符后面加一个<T>
     * @param exception
     * @param <L>
     * @param <R>
     * @return
     */
    public static <L, R> Either<L, R> left(L exception) {
        Either<L, R> e = new Either<>();
        e.left = exception;
        return e;
    }

    /**
     * 工厂方法-右值
     * @param value
     * @param <L>
     * @param <R>
     * @return
     */
    public static <L, R> Either<L, R> right(R value) {
        Either<L, R> e = new Either<>();
        e.right = value;
        return e;
    }

    public <T> Either<L, T> map(Function<R, T> function) {
        if (isLeft()) {
            return left(left);
        } else {
            return right(function.apply(right));
        }
    }

    public static <L,R> Either<L, List<R>> sequence(List<Either<L,R>> eitherList, BinaryOperator<L> accumulator){
        if(eitherList.stream().allMatch(Either::isRight)){
//            将所有数据存入
           return right(eitherList.stream().map(Either::getRight).collect(Collectors.toList()));
        }
        else{
//            结合需求，确定是取第一个异常还是全部异常
//            取第一个异常
//            return left(eitherList.stream().filter(Either::isLeft).findFirst().orElseThrow().getLeft());
//            取全部异常
            return left(eitherList.stream().filter(Either::isLeft).map(Either::getLeft).reduce(accumulator).orElseThrow(()-> new RuntimeException()));
        }
    }


}
