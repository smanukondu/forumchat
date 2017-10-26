/*
 * Licensed to the WIZ under one or more contributor license agreements. 
 * The WIZ licenses this file to You under the WIZ License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *     http://www.wiz.com/licenses/LICENSE-1.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.  For additional information regarding 
 * copyright in this work, please see the NOTICE file in the top level 
 * directory of this distribution.
 */

package com.wiz.jspforum.web.basic.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.wiz.jspforum.common.constant.WebAttributeConstant;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;

public class SecurityFilter implements Filter {

	private final static boolean DEBUG = false;

	private Hashtable<String, List<String>> accessableUrls = new Hashtable<String, List<String>>();

	public SecurityFilter() {
		super();
	}

	private boolean matchUrlPartForServletPathOrNot(String type, String servletPath, boolean equal) {
		List<String> urls = accessableUrls.get(type);
		if (urls != null) {
			for (String url : urls) {
				if (equal) {
					if (servletPath.equals(url)) {
						return true;
					}
				} else {
					if (servletPath.startsWith(url)) {
						return true;
					}
				}
			}
			return false;
		}
		return false;
	}

	private boolean equalsUrlForServletPath(String userType, String entityName, String permitName, String actionName) {
		List<String> urlsUserType = accessableUrls.get(userType);
		if (urlsUserType != null) {
			for (String url : urlsUserType) {
				if (url.equals("/" + permitName)) {
					List<String> urlsAction = accessableUrls.get("/" + entityName + "/" + permitName);
					if (urlsAction != null) {
						for (String url1 : urlsAction) {
							if (url1.equals("/" + actionName)) {
								return true;
							}
						}
						return false;
					} 
					return false;
				}
			}
		}
		return false;
	}

	private String getContextNameFromServletPath(String servletpath, int type) {
		int slashCount = StringUtils.countMatches(servletpath, "/");
		if (slashCount == 3) {
			servletpath = servletpath.substring(1);
			String[] splits = servletpath.split("/");
			if (splits.length == 3) {
				return splits[type];
			}
			return null;
		}
		return null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession(true);
		UserProfile user = (UserProfile)session.getAttribute(WebAttributeConstant.USER_CONFIG);
		String contextPath = httpRequest.getContextPath();
		String servletPath = httpRequest.getServletPath();
		String queryString = httpRequest.getQueryString();
		if (DEBUG) {
			System.out.print("contextPath := [" + contextPath + "], servletPath := [" + servletPath + "], queryString := [" + queryString + "] ");
		}
		if (matchUrlPartForServletPathOrNot("except", servletPath, false)) {
			if (DEBUG) {
				System.out.print("validated by 'except' branch \r\n");
			}
			chain.doFilter(request, response);
			return;
		}
		String entityName = getContextNameFromServletPath(servletPath, 0);
		String permitName = getContextNameFromServletPath(servletPath, 1);
		String actionName = getContextNameFromServletPath(servletPath, 2);
		if (DEBUG) {
			System.out.print("entityName := [" + entityName + "], permitName := [" + permitName + "], actionName := [" + actionName + "] ");
		}
		if (entityName == null || permitName == null || actionName == null) {
			httpResponse.sendRedirect(contextPath + "/error404.jsp");
			return;
		}
		if (matchUrlPartForServletPathOrNot("default", servletPath, true)) {
			if (DEBUG) {
				System.out.print("validated by 'default' branch \r\n");
			}
			chain.doFilter(request, response);
			return;
		}
		if (user != null) {
			if (equalsUrlForServletPath(user.getUserType(), entityName, permitName, actionName)) {
				if (DEBUG) {
					System.out.print("validated by 'usertype' branch \r\n");
				}
				chain.doFilter(request, response);
				return;
			}
			if (DEBUG) {
				System.out.print("validated by 'usertype' branch but go to ERROR:403 \r\n");
			}
			httpResponse.sendRedirect(contextPath + "/error403.jsp");
			return;
		} else {
			if (matchUrlPartForServletPathOrNot("open", servletPath, true)) {
				if (DEBUG) {
					System.out.print("validated by 'open' branch but go to REDIRECT \r\n");
				}
				session.setAttribute("redirect", servletPath + "?" + queryString);
				httpResponse.sendRedirect(contextPath + "/user/default/loginform");
				return;
			}
			if (DEBUG) {
				System.out.print("passed by 'open' branch but go to ERROR:404 \r\n");
			}
			httpResponse.sendRedirect(contextPath + "/error404.jsp");
			return;
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig filterConfig) {
		String propsfile = filterConfig.getInitParameter("config-props");
		ResourceBundle rb = ResourceBundle.getBundle(propsfile);
		Enumeration<String> enumTypes = rb.getKeys();
		while (enumTypes.hasMoreElements()) {
			String type = enumTypes.nextElement();
			String value = rb.getString(type);
			String[] urlRawList = value.split(",");
			List<String> list = new ArrayList<String>();
			for (String url : urlRawList) {
				list.add(url.trim());
			}
			accessableUrls.put(type, list);
		}
		System.out.println(accessableUrls);
	}
}
