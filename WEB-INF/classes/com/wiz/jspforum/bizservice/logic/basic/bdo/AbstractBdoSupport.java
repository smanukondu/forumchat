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

package com.wiz.jspforum.bizservice.logic.basic.bdo;

import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The abstract utility class of supporting the session logger object in BDO
 */
public abstract class AbstractBdoSupport {

	/**
	 * The object of logger which be attached in the HTTP session
	 */
	protected SimpleLog logger = null;

	/**
	 * To log the information in the level of BDO
	 * 
	 * @param logType the log type - INFOR, DEBUG, WARN ... 
	 * @param clazz the class name 
	 * @param message the message string 
	 */
	public void toLog(String logType, String clazz,  String message) {
		if (logger == null) {
			CommonLog.log(logType, clazz, message);
		} else {
			logger.log(logType, clazz, message);
		}
	}

	public abstract void setSessionLoggerToDaos(SimpleLog logger);
}
