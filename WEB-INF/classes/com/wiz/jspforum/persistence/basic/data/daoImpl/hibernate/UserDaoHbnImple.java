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

package com.wiz.jspforum.persistence.basic.data.daoImpl.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wiz.jspforum.persistence.basic.data.dao.UserDao;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.persistence.basic.data.helper.DaoSupportForHibernate;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The Hibernate Implement Class of UserDao
 */
@Repository("userDao")
public class UserDaoHbnImple extends DaoSupportForHibernate implements UserDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public UserProfile getUserProfileRecordByUserName(String username) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserProfileRecordByUserName(username)");
		toLog(CommonLog.DEBUG, getClass().getName(), "username = " + username);
		String hql = "from UserProfile up where upper(up.userName) = :username";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getUserProfileRecordByUserName' = " + hql);
		List<UserProfile> list = (List<UserProfile>)getHibernateTemplate().findByNamedParam(hql, "username", username.toUpperCase());
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}

	@Override
	public boolean insertUserProfileRecord(UserProfile userDetail) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertUserProfileRecord(userDetail)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userDetail = " + userDetail);
		getHibernateTemplate().save(userDetail);
		return true;
	}

	@Override
	public UserProfile getUserProfileRecordByUserId(int userId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserProfileRecordByUserId(userId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + userId);
		return (UserProfile)getHibernateTemplate().get(UserProfile.class, userId);
	}

	@Override
	public boolean updateUserProfileRecord(UserProfile userDetail) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateUserProfileRecord(userDetail)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userDetail = " + userDetail);
		getHibernateTemplate().update(userDetail);
		return true;
	}
	
	@Override
	public String selectEncryptedPasswordFromDatabase(String password) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.selectEncryptedPasswordFromDatabase(password)");
		toLog(CommonLog.DEBUG, getClass().getName(), "password = " + password);
		return (String)(getHibernateTemplate().find("select password('" + password + "') from dual").get(0));
	}
	
	@Override
	public boolean updateUserProfileRecordForLoginStatus(UserProfile userDetail) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateLoginStatusOfUserProfileRecord(userDetail)");
		toLog(CommonLog.DEBUG, getClass().getName(), "loginStatus = " + userDetail.getUserLoginStatus());
		getHibernateTemplate().update(userDetail);
		return true;
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
