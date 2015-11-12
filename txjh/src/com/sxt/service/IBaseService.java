package com.sxt.service;

import java.io.Serializable;
import java.util.List;

public interface IBaseService  {
    public Serializable addObject(Object obj);
    public Object get(Class<?> c,Serializable s);
    public List<Object>  getList(Object obj);
    public void saveOrUpdate(Object obj);
    
    public void deleteObject(Class<?> c,Serializable s);
    public void updateObject(Object obj);
    public List<Object> getResultsByHql(String hql, Object[] paramValues);
    public Object getObjectByHql(String hql, Object[] paramValues);
    public int executeUpdate(final String hql, final Object[] paramValues);
    
    public List<Object> getResultsBySql(String sql, Object[] paramValues);
    public Object getObjectBySql(String sql, Object[] paramValues);
    public int executeUpdateSql(final String sql, final Object[] paramValues);
    public List<?> getAllList(Class<?> c);
}
