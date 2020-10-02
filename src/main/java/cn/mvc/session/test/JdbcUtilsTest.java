package cn.mvc.session.test;

import cn.mvc.session.utils.JdbcUtils;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUtilsTest {

    @org.junit.jupiter.api.Test
    void getConnection() {
        Connection conn =JdbcUtils.getConnection();
        System.out.println(conn);
    }
}