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

package com.wiz.jspforum.util.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.wiz.jspforum.common.constant.GlobalSetting;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.util.date.DateSimpleFormat;

/**
 * The simple session log function for catalog the date and user in the server directory
 */
@SuppressWarnings("serial")
public class SimpleLog implements Serializable {
	
	private final static Logger logger = Logger.getLogger(SimpleLog.class);
	
	public final static String DEBUG = "DEBUG";
	public final static String INFOR = "INFOR";
	public final static String ERROR = "ERROR";
	public final static String ALERT = "ALERT";
	public final static String EXCEP = "EXCEP";
	
	private UserProfile user = null;
	
	public SimpleLog(UserProfile user) {
		this.user = user;
	}
	
	/**
	 * Locate the session log file in the server directory
	 * 
	 * @return the path of session log file
	 */
	private final String getUserCurrentLogFile() {
		String strUserCurrentLogFilePath = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		ResourceBundle rb = ResourceBundle.getBundle("forum_cfg");
		String logPath = rb.getString("WEBSITE_LOG_PATH");
		String strTodayLogFolder = logPath + "/" + formatter.format(new Date());
		File todayLogFolder = new File(strTodayLogFolder);
		if (!todayLogFolder.exists()) {
			todayLogFolder.mkdir();
		}
		strUserCurrentLogFilePath = strTodayLogFolder + "/" + user.getUserName().toUpperCase() + "_" + formatter.format(new Date()) + ".log";
		File userCurrentLogFile = new File(strUserCurrentLogFilePath);
		if (!userCurrentLogFile.exists()) {
			try {
				userCurrentLogFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return strUserCurrentLogFilePath;
			}
		}
		return strUserCurrentLogFilePath;
	}
	
	/**
	 * Write the log message to the log file
	 * 
	 * @param logType in the constant
	 * @param clazz the invoked class
	 * @param message the written message
	 */
	public final void log(String logType, String clazz, String message) {
		if (!GlobalSetting.IS_TEST_APP_ENVIRONMENT) {
			BufferedWriter bw = null;
			try {
				String logFile = getUserCurrentLogFile();
				bw = new BufferedWriter(new FileWriter(logFile, true));
				bw.write(logType + "    " + DateSimpleFormat.getSimpleDatetimeString(Calendar.getInstance().getTime()) + "    CLASS - " + clazz + " : " + message + "\r\n");
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException ioe2) {
						ioe2.printStackTrace();
					}
				}
			}
		} else {
			logger.info(logType + "    CLASS - " + clazz + " : " + message);
//			System.out.println(logType + "    " + SimpleDateExpression.getSimpleDatetimeString(Calendar.getInstance().getTime()) + "    CLASS - " + clazz + " : " + message);
		}
	}
}
