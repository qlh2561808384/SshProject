package com.longshao.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface BaseDao {
    Object save(Object var1);

    Session getSession();

    Object update(Object var1);

    Object saveOrUpdate(Object var1);

    void delete(Object var1);

    Object load(Class var1, Serializable var2);

    List loadAll(Class var1);

    List selectByProperty(String var1, String var2, String var3);

    List selectByHql(String var1);

    List selectByHql(String var1, List var2);

    List selectByHql(String var1, int var2, int var3);

    List selectByHql(String var1, List var2, int var3, int var4);

    Long getCountByHQL(String var1, List var2);

    Integer getIntCountByHQL(String var1, List var2);

    Long getCountBySQL(String var1, List var2);

    Integer getIntCountBySQL(String var1, List var2);

    List selectBySql(String var1);

    List selectBySql(String var1, Object[] var2);

    List selectBySql(String var1, Object[] var2, int var3, int var4);

    List selectMapsBySQL(String var1);

    List selectMapsBySQL(String var1, List var2);

    List selectMapsBySQL(String var1, List var2, int var3, int var4);

    void executeBySql(String var1);

    void executeBySql(String var1, List var2);

    void flush();

    Map loadMapBySql(String var1, List var2);

    Object loadByHql(String var1, List var2);

    Connection getConnection();

    <T> List<T> getListfromSql(String var1, Class<T> var2);

    <T> List<T> getListfromSql(Connection var1, String var2, Class<T> var3);

    Session getNewSession();

    void clearObject(Object var1);

    void refresh(Object var1);
}
