package com.sxt.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sxt.dao.BaseDao;
import com.sxt.service.IBaseService;
@Service
public class BaseServiceImpl implements IBaseService {
    @Resource
    public BaseDao baseDao;
    
    @Override
    public Object get(Class<?> c, Serializable s) {
        return baseDao.get(c, s);
    }

    @Override
    public List<Object> getList(Object obj) {
        return baseDao.getList(obj);
    }

    @Override
    public Serializable addObject(Object obj) {
        return baseDao.addObject(obj);
    }

    @Override
    public void saveOrUpdate(Object obj) {
        baseDao.saveOrUpdate(obj);
    }

    @Override
    public void deleteObject(Class<?> c, Serializable s) {
        baseDao.deleteObject(c, s);
    }

    @Override
    public int executeUpdate(String hql, Object[] paramValues) {
        return baseDao.executeUpdate(hql, paramValues);
    }

    @Override
    public int executeUpdateSql(String sql, Object[] paramValues) {
        return baseDao.executeUpdateSql(sql, paramValues);
    }

    @Override
    public Object getObjectByHql(String hql, Object[] paramValues) {
        return baseDao.getObjectByHql(hql, paramValues);
    }

    @Override
    public Object getObjectBySql(String sql, Object[] paramValues) {
        return baseDao.getObjectBySql(sql, paramValues);
    }

    @Override
    public List<Object> getResultsByHql(String hql, Object[] paramValues) {
        return baseDao.getResultsByHql(hql, paramValues);
    }

    @Override
    public List<Object> getResultsBySql(String sql, Object[] paramValues) {
        return baseDao.getResultsBySql(sql, paramValues);
    }

    @Override
    public void updateObject(Object obj) {
        baseDao.updateObject(obj);
    }
    
    @Override
    public List<?> getAllList(Class<?> c) {
    	return baseDao.getAllList(c);
    }

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

    
}
