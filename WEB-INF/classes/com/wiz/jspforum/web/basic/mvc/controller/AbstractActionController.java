
package com.wiz.jspforum.web.basic.mvc.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.wiz.jspforum.common.constant.ActionResultConstant;
import com.wiz.jspforum.common.constant.WebAttributeConstant;
import com.wiz.jspforum.common.model.PagingListModel;
import com.wiz.jspforum.persistence.basic.data.dto.FriendJointRequest;
import com.wiz.jspforum.persistence.basic.data.dto.PostComment;
import com.wiz.jspforum.persistence.basic.data.dto.UserMessage;
import com.wiz.jspforum.persistence.basic.data.dto.UserPost;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.util.log.SimpleLog;
import com.wiz.jspforum.web.basic.bean.presentation.FriendJointRequestListModel;
import com.wiz.jspforum.web.basic.bean.presentation.MessageListHistoryModel;
import com.wiz.jspforum.web.basic.bean.presentation.MessageListModel;
import com.wiz.jspforum.web.basic.bean.presentation.MyFriendListModel;
import com.wiz.jspforum.web.basic.bean.presentation.PostCommentListModel;
import com.wiz.jspforum.web.basic.bean.presentation.PostListModel;

public abstract class AbstractActionController {

	protected final static String MSG_ERR_PASSWORD_NOT_MATCH                      = "errors.user.username_password.notmatch";
	protected final static String MSG_ERR_USERNAME_NOT_EXISTED                    = "errors.user.username.notexisted";
	protected final static String MSG_ERR_FRIENDJOINTREQUEST_USERNAME_NOT_EXISTED = "errors.friendJointRequest.username.notExist";
	protected final static String MSG_ERR_FRIENDJOINTREQUEST_USERNAME_USERSELF    = "errors.friendJointRequest.username.userself";
	protected final static String MSG_ERR_FRIENDJOINTREQUEST_REQUEST_SENT         = "errors.friendJointRequest.request.sent";
	protected final static String MSG_ERR_FRIENDJOINTREQUEST_FRIEND_ALREADY       = "errors.friendJointRequest.friend.already";
	protected final static String MSG_INF_FRIENDJOINTREQUEST_SENTOUT_SUCCESS      = "messages.friendJointRequest.sentOut.success";

	private MessageSource messageSource = null;

	protected HttpSession session = null;
	protected UserProfile user    = null;
	protected SimpleLog   logger  = null;

	public abstract void injectSessionLoggerToBdos(HttpServletRequest request);

	@ModelAttribute
	public void injectHttpSession(HttpServletRequest request) {
		session = request.getSession(true);
	}

	@ModelAttribute
	public void injectUserProfileFromSession(HttpServletRequest request) {
		user = (UserProfile)request.getSession(true).getAttribute(WebAttributeConstant.USER_CONFIG);
	}

	@ModelAttribute
	public void injectLoggerFromSession(HttpServletRequest request) {
		logger = (SimpleLog)request.getSession(true).getAttribute(WebAttributeConstant.SYS_LOG);
	}

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	protected String getResourceMessage(String key, Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}

	protected String getResourceMessage(String key, Object[] args, Locale locale) {
		return messageSource.getMessage(key, args, locale);
	}

	protected void removeAttributeFromSession(String attributeName) {
		session.removeAttribute(attributeName);
	}

	protected void setAttributeFromSession(String attributeName, Object value) {
		session.setAttribute(attributeName, value);
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Model model, Exception ex) {
		model.addAttribute(WebAttributeConstant.ERROR_DETAIL, ex.toString());
		return ActionResultConstant.SYSTEM_INTERNAL_ERROR_PAGE;
	}

	@SuppressWarnings("unchecked")
	protected <E> void forwardListPresentBeanToPage(List<E> dataList, String pbCategory) {
		PagingListModel<E> lpb = null;
		// remove the variable from session firstly ...
		session.removeAttribute(pbCategory);
		if (pbCategory.equals(WebAttributeConstant.INBOX_MESSAGE_PRESENT_BEAN)) {
			lpb = (PagingListModel<E>) new MessageListModel((List<UserMessage>) dataList);
		} else if (pbCategory.equals(WebAttributeConstant.OUTBOX_MESSAGE_PRESENT_BEAN)) {
			lpb = (PagingListModel<E>) new MessageListModel((List<UserMessage>) dataList);
		} else if (pbCategory.equals(WebAttributeConstant.POST_PRESENT_BEAN) || pbCategory.equals(WebAttributeConstant.SEARCH_FOR_POST_PRESENT_BEAN)) {
			lpb = (PagingListModel<E>) new PostListModel((List<UserPost>) dataList);
		} else if (pbCategory.equals(WebAttributeConstant.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN)) {
			lpb = (PagingListModel<E>) new FriendJointRequestListModel((List<FriendJointRequest>) dataList);
		} else if (pbCategory.equals(WebAttributeConstant.MY_FRIEND_LIST_PRESENT_BEAN)) {
			lpb = (PagingListModel<E>) new MyFriendListModel((List<UserProfile>) dataList);
		}
		session.setAttribute(pbCategory, lpb);
	}

	protected void forwardMessageHistoryPresentBeanToPage(List<UserMessage> dataList, UserProfile friend) {
		MessageListHistoryModel mhpb = new MessageListHistoryModel(friend, dataList);
		session.setAttribute(WebAttributeConstant.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN, mhpb);
	}

	protected void forwardPostCommentsListPresentBeanToPage(UserPost post) {
		PostCommentListModel<PostComment> pclpb = new PostCommentListModel<PostComment>(post);
		session.setAttribute(WebAttributeConstant.POST_COMMENT_PRESENT_BEAN, pclpb);
	}
}
