package com.longshao.dao.impl;

import com.longshao.dao.BaseDao;
import org.hibernate.*;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class BaseDaoImpl implements BaseDao {
    private SessionFactory sessionFactory;

    public BaseDaoImpl() {
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
//        return this.sessionFactory.getCurrentSession();
    }

    public Object save(Object object) {
        this.getSession().save(object);
        return object;
    }

    public Object update(Object object) {
        this.getSession().update(object);
        return object;
    }

    public Object saveOrUpdate(Object object) {
        this.getSession().saveOrUpdate(object);
        return object;
    }

    public void delete(Object object) {
        this.getSession().delete(object);
    }

    public Object load(Class clazz, Serializable id) {
        return this.getSession().get(clazz, id);
    }

    public Map loadMapBySql(String sql, List values) {
        List<Map> list = this.selectMapsBySQL(sql, values, 0, 1);
        return list.size() == 1 ? (Map)list.get(0) : null;
    }

    public Object loadByHql(String hql, List values) {
        List list = this.selectByHql(hql, values, 0, 1);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    public List loadAll(Class clazz) {
        String hql = "from " + clazz.getName();
        return this.selectByHql(hql);
    }

    public List selectByProperty(String clazz, String propertyName, String value) {
        String hql = "from " + clazz + " as model where model." + propertyName + "= ?";
        return this.selectByHql(hql);
    }

    public List selectByHql(String hql) {
        return this.selectByHql(hql, (List)null);
    }

    public List selectByHql(String hql, List values) {
        return this.selectByHql(hql, values, -1, -1);
    }

    public List selectByHql(String hql, int start, int end) {
        return this.selectByHql(hql, (List)null, start, end);
    }

    public List selectByHql(String hql, List values, int start, int limit) {
        List pvalues = values;
        Query query = this.getSession().createQuery(hql);
        if (values != null) {
            for(int i = 0; i < pvalues.size(); ++i) {
                if (pvalues.get(i) instanceof String) {
                    query.setString(i, (String)pvalues.get(i));
                } else if (pvalues.get(i) instanceof Date) {
                    query.setDate(i, (Date)pvalues.get(i));
                } else if (pvalues.get(i) instanceof Long) {
                    query.setLong(i, (Long)pvalues.get(i));
                } else if (pvalues.get(i) instanceof Integer) {
                    query.setInteger(i, (Integer)pvalues.get(i));
                } else if (pvalues.get(i) instanceof Object[]) {
                    query.setParameter(i, (Object[])((Object[])pvalues.get(i)));
                } else {
                    query.setString(i, (String)pvalues.get(i));
                }
            }
        }

        if (start != -1 && limit != -1) {
            query.setMaxResults(limit);
            query.setFirstResult(start);
        }

        List result = query.list();
        if (!Hibernate.isInitialized(result)) {
            Hibernate.initialize(result);
        }

        return result;
    }

    public Integer getIntCountByHQL(String hql, List values) {
        Query query = this.getSession().createQuery(hql);
        if (values != null) {
            for(int i = 0; i < values.size(); ++i) {
                query.setParameter(i, values.get(i));
            }
        }

        query.setMaxResults(1);
        Object c = query.uniqueResult();
        if (c == null) {
            return 0;
        } else if (c instanceof BigDecimal) {
            return ((BigDecimal)c).intValue();
        } else {
            return c instanceof BigInteger ? ((BigInteger)c).intValue() : (Integer)c;
        }
    }

    public Long getCountByHQL(String hql, List values) {
        Query query = this.getSession().createQuery(hql);
        if (values != null) {
            for(int i = 0; i < values.size(); ++i) {
                query.setParameter(i, values.get(i));
            }
        }

        query.setMaxResults(1);
        Object c = query.uniqueResult();
        if (c == null) {
            return 0L;
        } else {
            return c instanceof BigDecimal ? ((BigDecimal)c).longValue() : (Long)c;
        }
    }

    public Integer getIntCountBySQL(String sql, List values) {
        Query query = this.getSession().createSQLQuery(sql);
        if (values != null) {
            for(int i = 0; i < values.size(); ++i) {
                query.setParameter(i, values.get(i));
            }
        }

        query.setMaxResults(1);
        Object c = query.uniqueResult();
        if (c == null) {
            return 0;
        } else if (c instanceof BigDecimal) {
            return ((BigDecimal)c).intValue();
        } else {
            return c instanceof BigInteger ? ((BigInteger)c).intValue() : (Integer)c;
        }
    }

    public Long getCountBySQL(String sql, List values) {
        Query query = this.getSession().createSQLQuery(sql);
        if (values != null) {
            for(int i = 0; i < values.size(); ++i) {
                query.setParameter(i, values.get(i));
            }
        }

        query.setMaxResults(1);
        Object c = query.uniqueResult();
        if (c == null) {
            return 0L;
        } else if (c instanceof BigDecimal) {
            return ((BigDecimal)c).longValue();
        } else {
            return c instanceof BigInteger ? ((BigInteger)c).longValue() : (Long)c;
        }
    }

    public List selectBySql(String sql) {
        return this.selectBySql(sql, (Object[])null);
    }

    public List selectBySql(String sql, Object[] obj) {
        return this.selectBySql(sql, obj, -1, -1);
    }

    public List selectBySql(String sql, Object[] obj, int start, int limit) {
        Query query = this.getSession().createSQLQuery(sql);
        if (obj != null) {
            for(int i = 0; i < obj.length; ++i) {
                query.setParameter(i, obj[i]);
            }
        }

        if (start != -1 && limit != -1) {
            query.setMaxResults(limit);
            query.setFirstResult(start);
        }

        List result = query.list();
        if (!Hibernate.isInitialized(result)) {
            Hibernate.initialize(result);
        }

        return result;
    }

    public List selectMapsBySQL(String sql) {
        return this.selectMapsBySQL(sql, (List)null, -1, -1);
    }

    public List selectMapsBySQL(String sql, List params) {
        return this.selectMapsBySQL(sql, params, -1, -1);
    }

    public List selectMapsBySQL(String sql, List params, int start, int limit) {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        if (params != null) {
            for(int i = 0; i < params.size(); ++i) {
                query.setParameter(i, params.get(i));
            }
        }

        if (start != -1 && limit != -1) {
            query.setMaxResults(limit);
            query.setFirstResult(start);
        }

        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return list;
    }

    public void executeBySql(String sql) {
        this.getSession().createSQLQuery(sql).executeUpdate();
    }

    public void executeBySql(String sql, List params) {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        if (params != null) {
            for(int i = 0; i < params.size(); ++i) {
                query.setParameter(i, params.get(i));
            }
        }

        query.executeUpdate();
    }

    public void flush() {
        this.getSession().flush();
    }

    public Connection getConnection() {
        try {
            Connection c = SessionFactoryUtils.getDataSource(this.getSessionFactory()).getConnection();
            return c;
        } catch (DataAccessResourceFailureException var2) {
            var2.printStackTrace();
        } catch (HibernateException var3) {
            var3.printStackTrace();
        } catch (IllegalStateException var4) {
            var4.printStackTrace();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return null;
    }

    public <T> List<T> getListfromSql(String sql, Class<T> clazz) {
        return this.getListfromSql((Connection)null, sql, clazz);
    }

    public <T> List<T> getListfromSql(Connection conn, String sql, Class<T> clazz) {
        Connection c;
        if (conn == null) {
            c = this.getConnection();
        } else {
            c = conn;
        }

        try {
            List var5 = this.assemble(c.createStatement(1004, 1008).executeQuery(sql), clazz, this.mappingColumnsWithOutAnnotation(clazz));
            return var5;
        } catch (SQLException var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (conn == null) {
                    c.close();
                }
            } catch (SQLException var14) {
                var14.printStackTrace();
            }

        }

        return null;
    }

    private <T> List<T> assemble(ResultSet rs, Class<?> T, Map columnsMap) {
        ArrayList list = null;

        try {
            rs.beforeFirst();
            Map<String, String> columns = columnsMap;
            Object[][] structs = (Object[][])null;
            int columnCount = 0;
            Object o;
            if (rs.next()) {
                list = new ArrayList();
                o = T.newInstance();
                ResultSetMetaData md = rs.getMetaData();
                structs = new Object[md.getColumnCount()][2];

                for(int i = 1; i <= md.getColumnCount(); ++i) {
                    String columnName = md.getColumnLabel(i).toLowerCase();
                    String fieldName = (String)columns.get(columnName);
                    columns.remove(columnName);
                    if (fieldName != null) {
                        Field field = T.getDeclaredField(fieldName);
                        Class<?> FieldType = field.getType();
                        String setMethod = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method method = T.getDeclaredMethod(setMethod, FieldType);
                        if (rs.getObject(i) != null) {
                            method.invoke(o, rs.getObject(i));
                        }

                        structs[columnCount][0] = i;
                        structs[columnCount++][1] = method;
                    }
                }

                list.add(o);
            }

            while(rs.next()) {
                o = T.newInstance();

                for(int i = 0; i < columnCount; ++i) {
                    Method m = (Method)((Method)structs[i][1]);
                    Object value = rs.getObject((Integer)structs[i][0]);
                    if (value != null) {
                        m.invoke(o, value);
                    }
                }

                list.add(o);
            }
        } catch (Exception var25) {
            var25.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException var24) {
                var24.printStackTrace();
            }

        }

        return list;
    }

    private Map<String, String> mappingColumnsWithOutAnnotation(Class c) {
        Map<String, String> map = new HashMap();
        Field[] fields = c.getDeclaredFields();
        Field[] arr$ = fields;
        int len$ = fields.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Field f = arr$[i$];
            map.put(f.getName().toLowerCase(), f.getName());
        }

        return map;
    }

    public Session getNewSession() {
        return this.sessionFactory.openSession();
    }

    public void clearObject(Object obj) {
        this.getSession().evict(obj);
    }

    public void refresh(Object obj) {
        this.getSession().flush();
        this.getSession().refresh(obj);
    }
}
