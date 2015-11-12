package com.sxt.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sxt.dao.BaseDao;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {
    @Resource
    private HibernateTemplate hibernateTemplate;
    
    
    @Override
	public Serializable addObject(Object u){
        return hibernateTemplate.save(u); 
    }
    
    public HibernateTemplate getHibernateTemplate() {

        return hibernateTemplate;
    }
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public Object get(Class<?> c, Serializable s) {
        return  hibernateTemplate.get(c, s);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getList(Object obj) {
        return hibernateTemplate.findByExample(obj);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<?> getAllList(Class<?> c) {
        return hibernateTemplate.loadAll(c);
    }
    
    @Override
	public void saveOrUpdate(Object obj) {
        hibernateTemplate.saveOrUpdate(obj);
    }

    @Override
    public void deleteObject(Class<?> c, Serializable s) {
        hibernateTemplate.delete(this.get(c, s));
    }

    /**
     * 执行更新和删除(批处理)
     * 
     * @param hql
     * @param paramValues
     * @return 受影响的行数
     */
    @Override
    public int executeUpdate(final String hql, final Object[] paramValues) {
        Object execute = hibernateTemplate.execute(
                new HibernateCallback<Object>() {
                    @Override
					public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query q = session.createQuery(hql);
                        setParamValues(q, paramValues);

                        return q.executeUpdate();// 执行批处理语句
                    }
                });
        return (Integer) execute;
    }

    /**
     * 设置hql语句参数
     */
    public void setParamValues(Query q, Object[] paramValues) { 
        if (paramValues != null && paramValues.length > 0) {
            for (int i = 0; i < paramValues.length; i++) {
                Object param = paramValues[i];
                if (param == null) {// util时间类型参数
                    q.setString(i, null);
                } else if (param instanceof Boolean) {// boolean类型参数
                    q.setBoolean(i, (Boolean) param);
                } else if (param instanceof Long) {// Long型参数
                    q.setLong(i, (Long) param);
                } else if (param instanceof Integer) {// 整数型参数
                    q.setInteger(i, (Integer) param);
                } 
                else if (param instanceof BigInteger) {// 整数型参数
                    q.setBigInteger(i, (BigInteger) param);
                } 
                else if (param instanceof Date) {// String类型
                    q.setDate(i, (Date) param);
                } else {
                    q.setString(i, paramValues[i].toString());
                }
            }
            
        }
    }
    
    @Override
    public Object getObjectByHql(String hql, Object[] paramValues) {
        List<Object> results = this.getResultsByHql(hql, paramValues);
        if (null != results && results.size() > 0) {
            return results.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getResultsByHql(String hql, Object[] paramValues) {
        List<Object> find = null;
        if (paramValues != null) {
            find =  hibernateTemplate.find(hql, paramValues);
        } else {
            find = hibernateTemplate.find(hql);
        }
        return find;
    }

    @Override
    public void updateObject(Object obj) {
        hibernateTemplate.update(obj);
    }

    @Override
    public int executeUpdateSql(final String sql, final Object[] paramValues) {
        Object execute = hibernateTemplate.execute(
            new HibernateCallback<Object>() {
                @Override
				public Object doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    Query q = session.createSQLQuery(sql);
                    setParamValues(q, paramValues);
                    return q.executeUpdate();// 执行批处理语句
                }
            });
    return (Integer) execute;
    }

    @Override
    public Object getObjectBySql(String sql, Object[] paramValues) {
        List<Object> results = this.getResultsBySql(sql, paramValues);
        if (null != results && results.size() > 0) {
            return results.get(0);
        }
        return null;
    }

    @Override
    public List<Object> getResultsBySql(final String sql, final Object[] paramValues) {
        List list = hibernateTemplate.executeFind(
            new HibernateCallback<Object>() {
                @Override
				public Object doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    Query q = session.createSQLQuery(sql);
                    setParamValues(q, paramValues);
                    return q.list();// 执行批处理语句
                }
            });
        return list;
    }
}
