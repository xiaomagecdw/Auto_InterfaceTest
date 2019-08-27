package com.course.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {

    private int id;
    private int userId;
    private String userName;
    private String sex;
    private String age;
    private String permission;
    private String isDeleted;
    private String expected;
}
