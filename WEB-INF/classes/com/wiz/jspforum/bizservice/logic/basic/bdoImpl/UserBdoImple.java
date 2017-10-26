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

package com.wiz.jspforum.bizservice.logic.basic.bdoImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wiz.jspforum.bizservice.logic.basic.bdo.AbstractBdoSupport;
import com.wiz.jspforum.bizservice.logic.basic.bdo.UserBdo;
import com.wiz.jspforum.persistence.basic.data.dao.UserDao;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;
import com.wiz.jspforum.web.basic.bean.form.UserProfileForm;
import com.wiz.jspforum.web.basic.bean.form.UserRegisterForm;

/**
 * The Implement Class of UserBdo
 */
@Service("userBdo")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserBdoImple extends AbstractBdoSupport implements UserBdo {

	private UserDao userDao = null;

	public UserBdoImple() {
        
    }

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserProfile getUserDetailsByUsername(String username) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserDetailsByUsername(username)");
		toLog(CommonLog.DEBUG, getClass().getName(), "username = " + username);
		UserProfile userProfile = userDao.getUserProfileRecordByUserName(username);
		return userProfile;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public boolean updateUserLoginStatus(UserProfile user, int newStatus) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateUserLoginStatus(user,newStatus)");
		toLog(CommonLog.DEBUG, getClass().getName(), "username = " + user.getUserName() + ", newStatus = " + newStatus);
		user.setUserLoginStatus(newStatus);
		boolean result = userDao.updateUserProfileRecordForLoginStatus(user);
		return result;
	}

	@Override
	public UserProfile getUserDetailsByUserId(int userId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserDetailsByUserId(userId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + userId);
		UserProfile userProfile = userDao.getUserProfileRecordByUserId(userId);
		return userProfile;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public boolean addNewUser(UserRegisterForm userRegisterForm) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.addNewUser(userRegisterForm)");
		UserProfile user = new UserProfile();
		user.setUserName(userRegisterForm.getUserName());
//		String md5_password = userDao.selectEncryptedPasswordFromDatabase(userRegisterForm.getPassword());
		user.setUserPassword(userRegisterForm.getUserPassword());
		user.setUserAlias(userRegisterForm.getUserAlias());
		user.setUserEmail(userRegisterForm.getUserEmail());
		user.setUserType(UserProfile.SYS_USER_ROLE_TYPE_COMMON);
		user.setUserLevel(1);
		user.setUserSignature(userRegisterForm.getUserSignature());
		user.setUserAvatarPic(userRegisterForm.getUserAvatarPic());
		user.setUserRegisterDate(new Date());
		user.setUserLoginStatus(UserProfile.FLAG_OF_USER_LOGIN_STATUS_INACTIVE);
		toLog(CommonLog.DEBUG, getClass().getName(), "the registered userProfile = [" + user.getUserName() + "]");
		boolean result = userDao.insertUserProfileRecord(user);
		return result;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public boolean changeUserProfileDetail(UserProfile user, UserProfileForm userProfileForm) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.changeUserProfileDetail(user,userProfileForm)");
		user.setUserId(userProfileForm.getId());
		user.setUserName(userProfileForm.getUsername());
//		String md5_password = userDao.selectEncryptedPasswordFromDatabase(userProfileForm.getPassword());
		String md5_password = userProfileForm.getPassword();
		user.setUserPassword(md5_password);
		user.setUserAlias(userProfileForm.getAlias());
		user.setUserEmail(userProfileForm.getEmail());
		user.setUserType(UserProfile.SYS_USER_ROLE_TYPE_COMMON);
		user.setUserLevel(1);
		user.setUserSignature(userProfileForm.getSignature());
		user.setUserAvatarPic(userProfileForm.getAvatarPIC());
		user.setUserRegisterDate(new Date());
		user.setUserLoginStatus(UserProfile.FLAG_OF_USER_LOGIN_STATUS_INACTIVE);
		toLog(CommonLog.DEBUG, getClass().getName(), "the updated userProfile = [" + user.getUserName() + "]");
		boolean result = userDao.updateUserProfileRecord(user);
		return result;
	}

	@Override
	public String fetchEncryptedPassword(String password) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.fetchEncryptedPassword(password)");
		toLog(CommonLog.DEBUG, getClass().getName(), "password = " + password);
		String encryptedPassword = userDao.selectEncryptedPasswordFromDatabase(password);
		toLog(CommonLog.DEBUG, getClass().getName(), "encryptedPassword = " + encryptedPassword);
		return encryptedPassword;
	}

	@Override
	public UserProfile loadFriendUserProfileDetailByUserId(int friendUserId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.loadFriendUserProfileDetailByUserId(friendUserId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "friendUserId = " + friendUserId);
		UserProfile friendProfile = userDao.getUserProfileRecordByUserId(friendUserId);
		return friendProfile;
	}

	@Override
	public void setSessionLogger(SimpleLog logger) {
		super.logger = logger;
		setSessionLoggerToDaos(logger);
	}

	@Override
	public void setSessionLoggerToDaos(SimpleLog logger) {
		userDao.setUserLogInstance(logger);
	}
}
