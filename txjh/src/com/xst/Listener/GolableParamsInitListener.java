package com.xst.Listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class GolableParamsInitListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        HttpSession session = arg0.getSession();
        ServletContext  servlet = session.getServletContext();
        servlet.getRealPath("/");
        //绝对路径如:/springmvc01/
        servlet.getServerInfo();
        session.setAttribute("basePath", servlet.getContextPath());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
    }

}
