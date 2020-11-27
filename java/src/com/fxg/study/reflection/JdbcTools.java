package com.fxg.study.reflection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author barry
 * @date 2020-11-27 15:34
 */
public class JdbcTools {
    private static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource("testc3p0");
    }

    /**
     * 获得连接
     * @return
     */
    public static Connection getConnection(){

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 资源释放
     * @param conn
     * @param st
     * @param rs
     */
    public static void release(Connection conn, Statement st, ResultSet rs){

        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
