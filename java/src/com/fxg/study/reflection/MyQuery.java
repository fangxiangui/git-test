package com.fxg.study.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

/**
 * @author barry
 * @date 2020-11-27 15:45
 */
public class MyQuery {

    /**
     * 查询结果返回对象
     * @param conn
     * @param sql
     * @param clazz
     * @return
     */
    public Object query(Connection conn,String sql,Class clazz,int id){
        PreparedStatement pst = null;
        ResultSet rs = null;
        Object obj = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            obj = clazz.newInstance();
            if (rs.next()){
                Field[] fileds = clazz.getDeclaredFields();
                ResultSetMetaData rsmd = rs.getMetaData();
                for (Field filed : fileds) {
                    int countColumn = rsmd.getColumnCount();
                    String name = filed.getName();
                    Object value = getFieldValue(rs, rsmd, filed, countColumn, name);
                    name = name.substring(0,1).toUpperCase()+name.substring(1);
                    String methodName = "set"+name;
                    Method method = clazz.getDeclaredMethod(methodName,filed.getType());
                    method.invoke(obj,value);
                }
            }
            JdbcTools.release(conn,pst,rs);
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 获得对应字段值
     * @param rs
     * @param rsmd
     * @param filed
     * @param countColumn
     * @param name
     * @return
     * @throws SQLException
     */
    private Object getFieldValue(ResultSet rs, ResultSetMetaData rsmd, Field filed, int countColumn, String name) throws SQLException {
        Object value = null;
        out: for (int i = 1; i<= countColumn; i++){
            if (name.equalsIgnoreCase(rsmd.getColumnName(i))){
                String type = filed.getType().getTypeName();
                switch (type){
                    case "int":
                        value = rs.getInt(name);
                        break out;
                    case "java.lang.String":
                        value = rs.getString(name);
                        break out;
                    case "double":
                        value = rs.getDouble(name);
                        break out;
                }
            }
        }
        return value;
    }
}
