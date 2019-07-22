package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "student")
public class GroupOnClass2 {
    public void student1(){
        System.out.println("GroupOnClass222中的student1运行");
    }

    public void student2(){
        System.out.println("GroupOnClass222中的student2运行");
    }
}
