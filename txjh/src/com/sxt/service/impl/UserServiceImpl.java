package com.sxt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sxt.dao.UserDao;
import com.sxt.service.IUserService;
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService{
	@Resource
	private UserDao userDao;
	

}
