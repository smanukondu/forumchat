
package com.wiz.jspforum.web.basic.mvc.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wiz.jspforum.bizservice.logic.basic.bdo.UserBdo;
import com.wiz.jspforum.common.constant.WebAttributeConstant;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;
import com.wiz.jspforum.web.basic.bean.form.LoginForm;
import com.wiz.jspforum.web.basic.bean.form.UserProfileForm;
import com.wiz.jspforum.web.basic.bean.form.UserRegisterForm;

@Controller
@RequestMapping("/user")
//@SessionAttributes("loginForm")
public class UserController extends AbstractActionController {

	private UserBdo userBdo = null;

	@Autowired
	public void setUserBdo(UserBdo userBdo) {
		this.userBdo = userBdo;
	}

	@Override
	@ModelAttribute
	public void injectSessionLoggerToBdos(HttpServletRequest request) {
		logger = (SimpleLog)request.getSession(true).getAttribute(WebAttributeConstant.SYS_LOG);
		userBdo.setSessionLogger(logger);
	}

	@ModelAttribute("loginForm")
	public LoginForm newLoginForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "UserController.newLoginForm() is called by SpringMVC for creating the instance of LoginForm ...");
		return new LoginForm();
	}

	@RequestMapping(value="/default/loginform", method=RequestMethod.GET)
	public void loginForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "UserController.loginForm() is called by SpringMVC for loading the view page of LoginForm ...");
	}

	@RequestMapping(value="/default/loginform", method=RequestMethod.POST)
	public String loginUserForGateway(@Valid LoginForm loginForm, 
//		@RequestParam(required=false, name="redirect") String redirect, 
		BindingResult result, 
		RedirectAttributes redirectAttrs, 
		Locale locale) {
		if (result.hasErrors()) {
			return null;
		}
		UserProfile user = userBdo.getUserDetailsByUsername(loginForm.getUserName());
		if (user != null && user.getUserName() != null) {
			String db_password = loginForm.getUserPassword();
			if (user.getUserPassword().equals(db_password)) {
				SimpleLog logger = new SimpleLog(user);
				setAttributeFromSession(WebAttributeConstant.USER_CONFIG, user);
				setAttributeFromSession(WebAttributeConstant.SYS_LOG, logger);
				// change the status of user login in to 'true' after the user signing on
				userBdo.updateUserLoginStatus(user, UserProfile.FLAG_OF_USER_LOGIN_STATUS_ACTIVE);
				String redirect = (String)session.getAttribute("redirect");
				if (redirect != null && !redirect.equals("")) {
					session.removeAttribute("redirect");
					return "redirect:" + redirect;
				}
				if (user.getUserType().equals(UserProfile.SYS_USER_ROLE_TYPE_COMMON)) {
					logger.log(CommonLog.DEBUG, getClass().getName(), "User [" + user.getUserName() + "] (Visitor) is signing up in the system, then redirect to LOAD_INBOX_MESSAGES");
					return "redirect:/message/common/loadinboxmessages";
				} else {
					// Other role logic for default ...
				}
			} else {
				// verify failed and redirect to the Message page (Password not matched).
				removeAttributeFromSession(WebAttributeConstant.USER_CONFIG);
				CommonLog.log(CommonLog.DEBUG, getClass().getName(), "User Login Authentication Failed - Password Not Matched (" + loginForm.getUserName() + " : " + loginForm.getUserPassword() + ")");
				redirectAttrs.addFlashAttribute("message", getResourceMessage(MSG_ERR_PASSWORD_NOT_MATCH, locale));
			}
		} else {
			// verify failed and redirect to the Message page (User not Existed).
			removeAttributeFromSession(WebAttributeConstant.USER_CONFIG);
			CommonLog.log(CommonLog.DEBUG, getClass().getName(), "User Login Authentication Failed - User Not Existed (" + loginForm.getUserName() + ")");
			redirectAttrs.addFlashAttribute("message", getResourceMessage(MSG_ERR_USERNAME_NOT_EXISTED, locale));
		}
		return "redirect:/user/default/loginform";
	}

	@RequestMapping(value="/common/logout", method=RequestMethod.GET)
	public String logoutForUser(HttpServletRequest request) {
		if (user != null && logger != null) {
			logger.log(CommonLog.DEBUG, getClass().getName(), "'" + user.getUserName() + "' is trying to sign off (in the LOGOUT_USER) ...");
			// change the status of user login in to 'false' after the user signing off
			userBdo.updateUserLoginStatus(user, UserProfile.FLAG_OF_USER_LOGIN_STATUS_INACTIVE);
			removeAttributeFromSession(WebAttributeConstant.USER_CONFIG);
			removeAttributeFromSession(WebAttributeConstant.SYS_LOG);
			logger.log(CommonLog.DEBUG, getClass().getName(), "User Logout Successful - (" + user.getUserName() + ")");
			// dispose the session in the web server (middle-ware)
			CommonLog.log(CommonLog.DEBUG, getClass().getName(), "User (" + user.getUserName() + ") on this current HTTP Session (" + session.getId() + ") will be destroyed");
			session.invalidate();
		}
		return "redirect:/user/default/loginform";
	}

	@ModelAttribute("userProfileForm")
	public UserProfileForm newUserProfileForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "UserController.newUserProfileForm() is called by SpringMVC for creating the instance of UserProfileForm ...");
		return new UserProfileForm();
	}

	@RequestMapping(value="/common/userprofileform", method=RequestMethod.GET)
	public void userProfileForm(Model model, HttpServletRequest request) {
		UserProfileForm userProfileForm = new UserProfileForm();
		userProfileForm.copyFrom(user);
		model.addAttribute("userProfileForm", userProfileForm);
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "UserController.newUserProfileForm() is called by SpringMVC for loading the view page of UserProfileForm ...");
	}

	@RequestMapping(value="/common/userprofileform", method=RequestMethod.POST)
	public String updateDetailsOfUserProfile(@Valid UserProfileForm userProfileForm, 
		BindingResult result, 
		HttpServletRequest request) {
		if (result.hasErrors()) {
			return null;
		}
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to update the current user profile");
		userBdo.changeUserProfileDetail(user, userProfileForm);
		logger.log(SimpleLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to update the current user profile");
		return "redirect:/user/common/logout";
	}

	@ModelAttribute("userRegisterForm")
	public UserRegisterForm newUserRegisterForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "UserController.newUserRegisterForm() is called by SpringMVC for creating the instance of UserRegisterForm ...");
		return new UserRegisterForm();
	}

	@RequestMapping(value="/default/userregisterform", method=RequestMethod.GET)
	public void userRegisterForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "UserController.newUserProfileForm() is called by SpringMVC for loading the view page of UserProfileForm ...");
	}
	
	@RequestMapping(value="/default/userregisterform", method=RequestMethod.POST)
	public String registerNewUser(@Valid UserRegisterForm userRegisterForm, 
		BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		userBdo.addNewUser(userRegisterForm);
		return "redirect:/user/default/loginform";
	}
}
