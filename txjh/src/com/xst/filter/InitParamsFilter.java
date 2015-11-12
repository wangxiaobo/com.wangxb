package com.xst.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.com.jdls.foundation.util.SysUtil;

import com.sxt.controller.UserController;
import com.xst.utils.ConstantsUtil;

public class InitParamsFilter implements Filter {
	
    private static final Logger log = Logger.getLogger(InitParamsFilter.class);
    
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
                                                                                    throws IOException,
                                                                                    ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String basePath = null;

        //设置全局绝对路径
        HttpSession session = request.getSession();
        if (null == session.getAttribute("basePath")) {
            basePath = request.getScheme() + "://" + request.getServerName() + ":"
                       + request.getServerPort() + request.getContextPath() + "/";
            session.setAttribute("basePath", basePath);
        }
        //是否已登录
        log.info(SysUtil.getSysDate24()+":"+request.getRequestURI());        
        Object obj= session.getAttribute(ConstantsUtil.LOGIN_USER_INFO);
        if(null == obj&&null ==request.getParameter("userId") ){
        	response.sendRedirect(session.getAttribute("basePath")+"login.html");//重定向到apage.jsp        }else{
        }else{
        	chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
