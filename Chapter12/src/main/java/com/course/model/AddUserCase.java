package com.course.model;

import lombok.Data;

@Data
public class AddUserCase {

    private String userName;
    private String Password;
    private String sex;
    private String age;
    private String permission;
    private String isDeleted;
    private String expected;

}
