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

package com.wiz.jspforum.persistence.basic.data.daoImpl.myBatis;

import com.wiz.jspforum.persistence.basic.data.dao.UserDao;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.persistence.basic.data.helper.DaoSupportForBatis;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The MyBatis Implement Class of UserDao
 */
public class UserDaoBatisImple extends DaoSupportForBatis implements UserDao {
	
	public UserDaoBatisImple() {
		
	}
	
	@Override
	public UserProfile getUserProfileRecordByUserName(String username) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserProfileRecordByUserName(username)");
		toLog(CommonLog.DEBUG, getClass().getName(), "username = " + username);
		return (UserProfile)getSqlSession().selectOne("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.UserMapper.getUserProfileRecordByUserName", username);
	}

	@Override
	public boolean insertUserProfileRecord(UserProfile userDetail) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertUserProfileRecord(userDetail)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userDetail = " + userDetail);
		int result = getSqlSession().insert("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.UserMapper.insertUserProfileRecord", userDetail);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of insertUserProfileRecord = [" + result + "]");
		return (result > -1) ? true : false;
	}

	@Override
	public UserProfile getUserProfileRecordByUserId(int userId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserProfileRecordByUserId(userId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + userId);
		return (UserProfile)getSqlSession().selectOne("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.UserMapper.getUserProfileRecordByUserId", userId);
	}

	@Override
	public boolean updateUserProfileRecord(UserProfile userDetail) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateUserProfileRecord(userDetail)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userDetail = " + userDetail);
		int result = getSqlSession().update("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.UserMapper.updateUserProfileRecord", userDetail);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of updateUserProfileRecord = [" + result + "]");
		return (result > -1) ? true : false;
	}
	
	@Override
	public String selectEncryptedPasswordFromDatabase(String password) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.selectEncryptedPasswordFromDatabase(password)");
		toLog(CommonLog.DEBUG, getClass().getName(), "password = " + password);
		return (String)getSqlSession().selectOne("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.UserMapper.selectEncryptedPasswordFromBatis", password);
	}
	
	@Override
	public boolean updateUserProfileRecordForLoginStatus(UserProfile userDetail) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateUserProfileRecordForLoginStatus(userDetail)");
		toLog(CommonLog.DEBUG, getClass().getName(), "loginStatus = " + userDetail.getUserLoginStatus());
		int result = getSqlSession().update("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.UserMapper.updateLoginStatusOfUserProfileRecord", userDetail);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of updateLoginStatusOfUserProfileRecord == " + result);
		return (result > -1) ? true : false;
	}

	@Override
	public void setUserLogInstance(SimpleLog logger) {
		super.logger = logger;
	}

	@Override
	public SimpleLog getUserLogInstance() {
		return super.logger;
	}
}
