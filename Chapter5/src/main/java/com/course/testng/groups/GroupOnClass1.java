package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "people")
public class GroupOnClass1 {
    public void people1(){
        System.out.println("GroupOnClass111中的people1运行");
    }

    public void prople2(){
        System.out.println("GroupOnClass111中的people2运行");
    }
}
