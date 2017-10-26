
package com.wiz.jspforum.web.basic.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wiz.jspforum.common.constant.ContextHelper;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.search.SearchFacade;

public class ApplicationContextListener implements ServletContextListener {

	public ApplicationContextListener() {

	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "--> ServletContext (" + sc.getServletContextName() + ") has been initialized ...");
		SearchFacade.init();
		ContextHelper.APP_CONTEXT_NAME = sc.getContextPath();
		System.out.println("java.io.tmpdir = " + System.getProperty("java.io.tmpdir"));
	}

	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "--> ServletContext (" + sc.getServletContextName() + ") has been destroyed ...");
	}
}
