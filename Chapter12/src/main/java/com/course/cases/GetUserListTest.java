package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserListTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取用户信息列表接口测试")
    public void getUserList() throws IOException {

        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserList = session.selectOne("getUserListCase",1);
        System.out.println(getUserList.toString());
        System.out.println(TestConfig.getUserListUrl);

    }
}
