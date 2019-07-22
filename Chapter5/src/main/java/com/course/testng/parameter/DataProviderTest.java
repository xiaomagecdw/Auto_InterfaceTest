package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name, int age){
        System.out.println("name = "+name+","+ "age = "+age);
    }

    @DataProvider(name = "data")
    public Object[][] providerData(){
        Object[][] o = new Object[][]{
                {"zhangsan",20},
                {"lisi",30},
                {"wangwu",40}
        };
        return o;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name, int age){
        System.out.println("test111方法name = "+name+","+ "age = "+age);

    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age){
        System.out.println("test222方法name = "+name+","+ "age = "+age);

    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] result = null;

        if(method.getName().equals("test1")){
            result = new Object[][]{
                    {"zhangsan",20},
                    {"wangwu",25},
                    {"mazi",30}
            };
        }else if(method.getName().equals("test2")){
            result = new Object[][]{
                    {"lisi",35},
                    {"liqiang",40},
                    {"liuming",45}
            };

        }
        return result;
    }

}
