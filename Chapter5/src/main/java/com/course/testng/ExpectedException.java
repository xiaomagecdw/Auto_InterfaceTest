package com.course.testng;

import org.testng.annotations.Test;

public class ExpectedException {

    /**
     * 什么情况下会用到异常测试？
     * 在测试期望结果是一个异常的时候
     * 列如：测试传入某个不正确的参数，程序抛出异常
     * 也就是叙述的语言语气结果就是这个异常
     */

    //演示一个测试失败的异常测试
//    @Test(expectedExceptions = RuntimeException.class)
//    public void runTimeExceptionFailed(){
//        System.out.println("这是一个失败的异常测试");
//        throw new RuntimeException();
//    }

    //演示一个测试成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();
    }
}
