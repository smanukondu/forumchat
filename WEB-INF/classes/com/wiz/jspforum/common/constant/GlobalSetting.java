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

package com.wiz.jspforum.common.constant;

/**
 * The macro in the web-app
 */
public class GlobalSetting {

	public final static boolean IS_TEST_APP_ENVIRONMENT = true;

	public final static int MAX_PAGE_LIST_ITEM = 15;

	public final static String DATASOURCE_JNDI_URI_FOR_JBOSS  = "java:jboss/jdbc/jspforum";
	public final static String DATASOURCE_JNDI_URI_FOR_TOMCAT = "java:/comp/env/jdbc/jspforum";
	public final static String DATASOURCE_JNDI_URI            = DATASOURCE_JNDI_URI_FOR_TOMCAT;
	
}
