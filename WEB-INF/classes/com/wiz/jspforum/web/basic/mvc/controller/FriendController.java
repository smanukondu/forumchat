
package com.wiz.jspforum.web.basic.mvc.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wiz.jspforum.bizservice.logic.basic.bdo.FriendBdo;
import com.wiz.jspforum.bizservice.logic.basic.bdo.MessageBdo;
import com.wiz.jspforum.bizservice.logic.basic.bdo.UserBdo;
import com.wiz.jspforum.common.constant.ActionResultConstant;
import com.wiz.jspforum.common.constant.WebAttributeConstant;
import com.wiz.jspforum.persistence.basic.data.dto.FriendJointRequest;
import com.wiz.jspforum.persistence.basic.data.dto.FriendLink;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;
import com.wiz.jspforum.web.basic.bean.form.FriendJointRequestForm;
import com.wiz.jspforum.web.basic.bean.form.FriendMessageForm;

@Controller
@RequestMapping("/friend")
public class FriendController extends AbstractActionController {

	private FriendBdo friendBdo = null;
	private UserBdo userBdo = null;
	private MessageBdo messageBdo = null;

	@Autowired
	public void setFriendBdo(FriendBdo friendBdo) {
		this.friendBdo = friendBdo;
	}

	@Autowired
	public void setUserBdo(UserBdo userBdo) {
		this.userBdo = userBdo;
	}

	@Autowired
	public void setMessageBdo(MessageBdo messageBdo) {
		this.messageBdo = messageBdo;
	}

	@Override
	public void injectSessionLoggerToBdos(HttpServletRequest request) {
		logger = (SimpleLog)request.getSession(true).getAttribute(WebAttributeConstant.SYS_LOG);
		friendBdo.setSessionLogger(logger);
		userBdo.setSessionLogger(logger);
	}

	@RequestMapping(value="/common/loadfriendjointrequestlist", method=RequestMethod.GET)
	public String loadFriendJointRequestList() {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to load friend joint request list for current user");
		List<FriendJointRequest> fjrList = friendBdo.getFriendJointRequestListforPendingStatusForUser(user);
		forwardListPresentBeanToPage(fjrList, WebAttributeConstant.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN);
		logger.log(SimpleLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to load friend joint request list for current user");
		return ActionResultConstant.LOADED_FRIEND_JOINT_REQUEST_LIST_PAGE;
	}

	@RequestMapping(value="/common/loadfriendlist", method=RequestMethod.GET)
	public String loadFriendList() {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to load user's friend list");
		List<FriendLink> friendList = friendBdo.getUserFriendList(user);
		forwardListPresentBeanToPage(friendList, WebAttributeConstant.MY_FRIEND_LIST_PRESENT_BEAN);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to load user's friend list");
		return ActionResultConstant.LOADED_ALL_USER_FRIEND_LIST_PAGE;
	}

	@ModelAttribute("friendJointRequestForm")
	public FriendJointRequestForm newFriendJointRequestForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "FriendController.newFriendJointRequestForm() is called by SpringMVC for creating the instance of FriendJointRequestForm ...");
		return new FriendJointRequestForm();
	}

	@RequestMapping(value="/common/friendjointrequestform", method=RequestMethod.GET)
	public void friendJointRequestForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "FriendController.friendJointRequestForm() is called by SpringMVC for loading the view page of FriendJointRequestForm ...");
	}

	@RequestMapping(value="/common/friendjointrequestform", method=RequestMethod.POST)
	public String sendFriendJointRequest(@Valid FriendJointRequestForm friendJointRequestForm, 
		BindingResult result, 
		RedirectAttributes redirectAttrs, 
		Locale locale) {
		if (result.hasErrors()) {
			return null;
		}
		SimpleLog logger = (SimpleLog)session.getAttribute(WebAttributeConstant.SYS_LOG);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to send the joint request to the friend");
		// check whether the requested friend UserName is existed in the system
		userBdo.setSessionLogger(logger);
		UserProfile friendUser = userBdo.getUserDetailsByUsername(friendJointRequestForm.getFriendUserName());
		logger.log(CommonLog.DEBUG, getClass().getName(), "The requested friend is [" + friendJointRequestForm.getFriendUserName() + "]");
		if (friendUser == null || friendUser.getUserId() == 0) {
			logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") can not send the friend request to the friend [" + friendJointRequestForm.getFriendUserName() + "] (The friend username not existed in the system)");
			Object[] args = {friendJointRequestForm.getFriendUserName()};
			redirectAttrs.addFlashAttribute("message", getResourceMessage(MSG_ERR_FRIENDJOINTREQUEST_USERNAME_NOT_EXISTED, args, locale));
			return "redirect:/friend/common/friendjointrequestform";
		}
		// check whether the requested friend UserName is equal to the user-self
		if (friendUser.getUserId() == user.getUserId()) {
			logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") can not send the friend request to the friend [" + friendJointRequestForm.getFriendUserName() + "] (The friend username is the user-self)");
			Object[] args = {friendJointRequestForm.getFriendUserName()};
			redirectAttrs.addFlashAttribute("message", getResourceMessage(MSG_ERR_FRIENDJOINTREQUEST_USERNAME_USERSELF, args, locale));
			return "redirect:/friend/common/friendjointrequestform";
		}
		// check whether the requested friend and the user has been linked in the system
		friendBdo.setSessionLogger(logger);
		if (friendBdo.isJointRequestSentToFriendForPending(user, friendUser)) {
			logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") have been sending the request to the friend [" + friendJointRequestForm.getFriendUserName() + "]");
			redirectAttrs.addFlashAttribute("message", getResourceMessage(MSG_ERR_FRIENDJOINTREQUEST_REQUEST_SENT, locale));
			return "redirect:/friend/common/friendjointrequestform";
		}
		// check whether the requested friend has been approved by the user
		if (friendBdo.isFriendRelationshipAlreadyExisted(user, friendUser)) {
			logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") has been the friend [" + friendJointRequestForm.getFriendUserName() + "] with the user.");
			redirectAttrs.addFlashAttribute("message", getResourceMessage(MSG_ERR_FRIENDJOINTREQUEST_FRIEND_ALREADY, locale));
			return "redirect:/friend/common/friendjointrequestform";
		}
		friendBdo.addNewFriendJointRequestForPending(user, friendUser, friendJointRequestForm.getRequestMessage());
		redirectAttrs.addFlashAttribute("message", getResourceMessage(MSG_INF_FRIENDJOINTREQUEST_SENTOUT_SUCCESS, locale));
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to send the request link to the friend [" + friendJointRequestForm.getFriendUserName() + "]");
		return "redirect:/friend/common/friendjointrequestform";
	}

	@RequestMapping(value="/common/acceptfriendjointrequest", method=RequestMethod.GET)
	public String acceptFriendJointRequest(@RequestParam(required=true, name="FRIEND_REQUEST_ID") int friendRequestId) {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to accept the friend request");
		friendBdo.setSessionLogger(logger);
		friendBdo.processLogicOfApprovingFriendJointRequest(friendRequestId);
		logger.log(SimpleLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to accept the friend request (" + friendRequestId + ")");
		return "redirect:/friend/common/loadfriendjointrequestlist";
	}

	@RequestMapping(value="/common/rejectfriendjointrequest", method=RequestMethod.GET)
	public String rejectFriendJointRequest(@RequestParam(required=true, name="FRIEND_REQUEST_ID") int friendRequestId) {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to reject the friend request");
		friendBdo.setSessionLogger(logger);
		friendBdo.processLogicOfRejectingFriendJointRequest(friendRequestId);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to reject the friend request (" + friendRequestId + ")");
		return "redirect:/friend/common/loadfriendjointrequestlist";
	}

	@RequestMapping(value="/common/cancelfriendlink", method=RequestMethod.GET)
	public String cancelFriendLink(@RequestParam(required=true, name="FRIEND_ID") int friendUserId) {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to cancel the friend link");
		friendBdo.setSessionLogger(logger);
		friendBdo.processLogicOfCancelingFriendRelationship(user, friendUserId);
		logger.log(SimpleLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to cancel the friend link (" + friendUserId + ")");
		return "redirect:/friend/common/loadfriendlist";
	}

	@ModelAttribute("friendMessageForm")
	public FriendMessageForm newFriendMessageForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "FriendController.newFriendMessageForm() is called by SpringMVC for creating the instance of FriendMessageForm ...");
		return new FriendMessageForm();
	}

	@RequestMapping(value="/common/friendmessageform", method=RequestMethod.GET)
	public void friendMessageForm(@RequestParam(required=true, name="FRIEND_USER_ID") int friendUserId) {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "FriendController.friendMessageForm() is called by SpringMVC for loading the view page of FriendMessageForm ...");
		UserProfile userFriend = userBdo.getUserDetailsByUserId(friendUserId);
		session.setAttribute(WebAttributeConstant.USER_FRIEND_DETAIL, userFriend);
	}

	@RequestMapping(value="/common/friendmessageform", method=RequestMethod.POST)
	public String sendMessageToFriend(@Valid FriendMessageForm friendMessageForm, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to reply the message to friend");
		// insert the new message into DB
		messageBdo.processLogicOfSendingMessageByFriend(friendMessageForm);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to reply the message to friend");
		return "redirect:/message/common/loadoutboxmessages";
	}
	
	@RequestMapping(value="/common/loadfriendprofiledetail", method=RequestMethod.GET)
	public String loadFriendProfileDetail(@RequestParam(required=true, name="FRIEND_USER_ID") int friendUserId, Model model) {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to load friend profile detail");
		friendBdo.setSessionLogger(logger);
		UserProfile friendProfile = userBdo.loadFriendUserProfileDetailByUserId(friendUserId);
		model.addAttribute(WebAttributeConstant.USER_FRIEND_DETAIL, friendProfile);
		logger.log(SimpleLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to load friend profile detail (" + friendUserId + ")");
		return ActionResultConstant.LOADED_FRIEND_PROFILE_DETAIL_PAGE;
	}
}
